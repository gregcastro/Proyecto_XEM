#!/usr/bin/python3
# -*- coding: utf-8 -*-

import argparse
import json
from subprocess import Popen, PIPE
import time
import datetime
import socket
import requests
import optparse
import uuid
import os.path
from multiprocessing import Queue


"""
Lectura de Claymore
"""
host = '10.0.1.94'
port = 3333
loop_seconds = 10

# Configuration variables
seconds_between_requests = 1
# web_server_address = 'http://192.168.0.102:8081/xem'
web_server_address = 'http://10.0.1.143:8081'
# payload informatcion
# user_email = "alfonsof@gmail.com"
# rig_name = "Min02"
# claymore_version = "9.5"
user_email = ""
rig_name = ""
claymore_version = ""


# Parse options from outside to retrieve Miner command
parser = optparse.OptionParser('usage: python process_printer '+ '-c <Miner Command>')
parser.add_option('-c', dest='command', type='str', help='Miner Command')
parser.add_option('-r', dest='rig_reset_today', type='str', help='Numbers of resets today')
parser.add_option('-t', dest='rig_claymore_reset_today', type='str', help='Numbers of Claymore resets today ?')
(options, args) = parser.parse_args()

# If applicable, fill dict values
if (options.rig_reset_today != None):
    gpu_info["rig_reset_today"] = options.rig_reset_today

    if (options.rig_claymore_reset_today != None):
        gpu_info["rig_claymore_reset_today"] = options.rig_claymore_reset_today

# Global dictionary
gpu_info = {
    "rig_email":"None",
    "rig_name":"None",
    "rig_uuid":"None",
    "rig_gpu_second_coin":"None",
    "rig_lan_ip":"None",
    "rig_claymore_version":"None",
    "rig_time_up":"None",
    "rig_reset_today":"None",
    "location_uuid":"None",
    "rig_claymore_reset_today":"None",
    "rig_gpu_info_eth": [
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"},
     {"Mhs":"None", "Temp":"None", "Fan":"None","Model":"None"}
        ],
    "rig_gpu_info_second_coin": [
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"},
    {"Mhs":"None"}
        ]
}

# gpu_info = {}
# Check Local Ip Address
gpu_info["rig_lan_ip"] = socket.gethostbyname(socket.gethostname())
# gpu_info["rig_time_up"] = '0'

# Time Keeper
start_time = time.time()
tick_time = start_time

############################################ PARSER ############################################
#
# Here in lies the parsing logic, each line that the mining program prints passes through here to be examined for usefull information.
def parse_line(line,dict_output):

    if 'ETH' in line:
        #Check if the GPU keyword is in the line.
        if 'GPU' in line:
            #CHeck if it's the line with no information
            if 'GPU ' not in line and 'GPU_' not in line:

                #Check which GPU we are talking about
                gpu_number = int(line[ line.find('GPU') + 3])

                # Find out what information is provided by th line
                if 'Mh/s' in line:
                    CpuSpeed = float(line[ line.find('GPU' + str(gpu_number)) + 4: line.find('Mh/s')].strip())
                    dict_output["rig_gpu_info_eth"][gpu_number]['Mhs'] = CpuSpeed

                    if 't=' in line:
                        Temp = int(line[ line.find('t=') + 2: line.find('C')])
                        dict_output["rig_gpu_info_eth"][gpu_number]['Temp'] = Temp

                        fan = int(line[ line.find('fan=') + 4: line.find('%')])
                        dict_output["rig_gpu_info_eth"][gpu_number]['Fan'] = fan

    if 'DCR' in line:
        # Update the status of the second coin
        dict_output["rig_gpu_second_coin"] = "Decred"

        # CHeck if we got GPU information
        if 'GPU' in line:
            #CHeck if it's the line with no information
            if 'GPU ' not in line and 'GPU_' not in line:

                #Check which GPU we are talking about
                gpu_number = int(line[ line.find('GPU') + 3])

                # Find out what information is provided by th line
                if 'Mh/s' in line:
                    CpuSpeed = float(line[ line.find('GPU' + str(gpu_number)) + 4: line.find('Mh/s')].strip())
                    dict_output["rig_gpu_info_second_coin"][gpu_number]['Mhs'] = CpuSpeed


    if 'GPU' in line and 'ETH' not in line and 'DCR' not in line:
        # Process the line that contains the temperature
        if 'GPU ' not in line and 'GPU_' not in line:

            if 't=' in line:
                #Check which GPU we are talking about
                gpu_number = int(line[ line.find('GPU') + 3])

                # Parse Temperature and fan values
                Temp = int(line[ line.find('t=') + 2: line.find('C')])
                dict_output["rig_gpu_info_eth"][gpu_number]['Temp'] = Temp

                fan = int(line[ line.find('fan=') + 4: line.find('%')])
                dict_output["rig_gpu_info_eth"][gpu_number]['Fan'] = fan


    # This is particularly to check which GPU es currently running:
    if "recognized as" in line:

        # print("line = ")
        print(line)
        #Check which GPU we are talking about
        gpu_number = int(line[ line.find('recognized as') - 2])
        # print("gpu_number = ")
        print(gpu_number)
        #Parse GPU Model
        model = line[ line.find('recognized as') + 14:]
        # print("model = ")
        print(model)
        dict_output["rig_gpu_info_eth"][gpu_number]['Model'] = model

def inicializar_cliente_TCP():
    global address
    global client_socket
    address = ('192.168.0.254', 8888) #Define who you are talking to (must match arduino IP and port)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #Set Up the Socket
    client_socket.settimeout(2) #only wait 1 second for a resonse

def pedir_num_resets():
    global client_socket
    client_socket.sendto(bytes("0", "utf-8"), address)
    try:
        rec_data, addr = client_socket.recvfrom(2048) #Read response from arduino
        print('El numero de resets de este equipo es: ' + rec_data) #Print the response from Arduino
        gpu_info["rig_reset_today"] = rec_data
    except:
        pass

######################################################### MAIN ###################################################################
inicializar_cliente_TCP()
pedir_num_resets()
with Popen(r"winpty.exe -Xallow-non-tty -Xplain ./" + options.command, stdout=PIPE, bufsize=1, shell=True) as p:
    for line in p.stdout:
        # This is the main loop

        # Encode line as utf-8
        line = str(line,'utf-8')

        # Run the Parsing logic
        parse_line(line,gpu_info)

        # check if 10 seconds have passed since last sent request
        if time.time() - tick_time > seconds_between_requests:
            # Send request
            

            # Prueba leer linea por linea gpu_info_config.txt
            if os.path.exists("..\\gpu_config.txt"):
                file = open("..\\gpu_config.txt", "r") 
                rig_email = file.readline()
                # index = rig_email.find("=")
                rig_email = rig_email[rig_email.find("=")+1: len(rig_email)]
                rig_email = rig_email.replace(" ", "")
                rig_email = rig_email.replace("\n", "")

                rig_name = file.readline()
                rig_name = rig_name[rig_name.find("=")+1: len(rig_name)]
                rig_name = rig_name.replace(" ", "")
                rig_name = rig_name.replace("\n", "")

                claymore_version = file.readline()
                claymore_version = claymore_version[claymore_version.find("=")+1: len(claymore_version)]
                claymore_version = claymore_version.replace(" ", "")

                """
                print(rig_email)
                print(rig_name)
                print(claymore_version)
                """
            else:
                #Ver que se hace en este caso.. 
                print("The file doesn't exits")

            # Fill variables that are not parsed from the console.
            gpu_info["rig_email"] = rig_email
            gpu_info["rig_name"] = rig_name
            gpu_info["rig_claymore_version"] = claymore_version

            # calculate elapsed time.
            gpu_info["rig_time_up"] = "{}".format(datetime.timedelta(seconds=int(time.time() - start_time)))

            # If the rig_uuid exists then a use it. If isn't, I create it 
            if os.path.exists("..\\rig_uuid.txt"):
                file = open("..\\rig_uuid.txt", "r") 
                rig_uuid = file.read()
            else:
                print('WHILE...')
                while True:
                    rig_uuid = str( uuid.uuid4() )
                    r = requests.get(web_server_address+'/rig/'+rig_uuid, data={"gpu_info":str(gpu_info)})
                    
                    # print(r.headers['content-type'])
                    # print(r.status_code)
                    response = r.json()
                    if 'rig_uuid' not in response:
                        break
                
                # Create a File and Save the rig_uuid
                file = open("..\\rig_uuid.txt","w") 
                file.write(rig_uuid) 
                file.close() 

            gpu_info["rig_uuid"] = rig_uuid


            # print(gpu_info)

            # Send request to server
            try:
                r = requests.post(web_server_address+'/rig', data=str({"gpu_info":str(gpu_info)}) )
                
                json_response = r.json()

                print('json_response = ', json_response)
                
                if 'ERROR' in json_response:
                    while True:
                        rig_uuid = str( uuid.uuid4() )
                        print('rig_uuid = ', rig_uuid)
                        r = requests.get(web_server_address+'/rig/'+rig_uuid, data={"gpu_info":str(gpu_info)})
                        
                        response = r.json()
                        if 'rig_uuid' not in response:
                            break
                    print('crear el archivo')
                    
                    # Create a File and Save the rig_uuid
                    file = open("..\\rig_uuid.txt","w") 
                    file.write(rig_uuid) 
                    file.close() 

                    gpu_info["rig_uuid"] = rig_uuid

            except:
                print('ERROR POST')
                pass


            #print information to console
            # print(gpu_info)
            # print(line)
            tick_time = time.time()
