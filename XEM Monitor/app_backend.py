import subprocess
import shutil
import os
import requests
import json
import socket
import time
from multiprocessing import Queue
from urllib.request import urlopen
from zipfile import ZipFile
from win32com.client import Dispatch
import win32com.shell.shell as shell
import win32event
from win32com.client import GetObject

target = ""
wDir = ""
ClaymoreReaderPath = ""
config_rig_dir = r"C:\Claymore"
windows_config = 'setx GPU_FORCE_64BIT_PTR 0\nsetx GPU_MAX_HEAP_SIZE 100\nsetx GPU_USE_SYNC_OBJECTS 1\nsetx GPU_MAX_ALLOC_PERCENT 100\nsetx GPU_SINGLE_ALLOC_PERCENT 100\ntimeout /t 20\n EthDcrMiner64.exe '
myIP = ''
data = {}
web_server = 'http://10.0.1.143:8081'

def web_get_request_JSON():
    global data

    # Find rig_uuid 
    if os.path.exists(config_rig_dir + r"\rig_uuid.txt"):
        file = open(config_rig_dir + r"\rig_uuid.txt", "r") 
        rig_uuid = file.read()
        r = requests.get(web_server + '/action/by_rig/' + rig_uuid)
        data = r.json()
        print('data = ', data)
    else:
        print('Error: No existe el path' + config_rig_dir + r"\rig_uuid.txt")

def obtener_myIP():
    global myIP
    hostname = socket.gethostname()
    myIP = socket.gethostbyname(hostname)
    print("Mi direccion IP es: ", myIP)

def inicializar_cliente_TCP():
    global address
    global client_socket
    address = ('192.168.0.254', 8888) #Define who you are talking to (must match arduino IP and port)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #Set Up the Socket
    client_socket.settimeout(2) #only wait 1 second for a response

def crear_acceso_directo_start_bat():
    path = os.path.join(config_rig_dir, "start.lnk")
    shell = Dispatch('WScript.Shell')
    shortcut = shell.CreateShortCut(path)
    shortcut.Targetpath = target
    shortcut.WorkingDirectory = wDir
    shortcut.IconLocation = target
    shortcut.save()

def iniciar_claymore():
    se_ret = shell.ShellExecuteEx(fMask=0x140, lpFile=config_rig_dir+r"\start.lnk", nShow=1)
    time.sleep(5) #Esperar a que inicie el claymore
    se_ret2 = shell.ShellExecuteEx(fMask=0x140, lpFile=ClaymoreReaderPath, nShow=1)

def reiniciar_claymore():
    os.system("TASKKILL /F /T /IM cmd.exe")
    # os.system("TASKKILL /IM cmd.exe /T /F")
    time.sleep(3)
    iniciar_claymore()

def request_reiniciar_claymore():
    if 'action' in data and data['action']['action_restart_claymore'] == "1":
        reiniciar_claymore()
        data['action']['action_restart_claymore'] = "0"

def cambiar_version_claymore():
    global target
    global wDir
    if 'action' in data and data['action']['action_change_claymore_version'] == "1":
        # Creo que aqui es donde deberia preguntar primero si el path nuevo existe o no.

        if os.path.exists(config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version']):
            target = config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\\start.bat"
            wDir = config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version']
            ClaymoreReaderPath = config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\\ClaymoreReader.exe"

            file = open(config_rig_dir + r"\dir_info.txt","w") 
            file.write(config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\\start.bat" + "\n") 
            file.write(config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\n") 
            file.write(config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\\start.bat" + "\n") 
            file.write(config_rig_dir + r"\Claymore v" + data['properties']['rig_claymore_version'] + "\\ClaymoreReader.exe" + "\n") 
            file.close() 


            if os.path.exists(wDir):
                crear_acceso_directo_start_bat()
                reiniciar_claymore()
            data['action']['action_change_claymore_version'] = "0"
        else:
            print("No existe el path")

def cambiar_start_bat():
    if 'action' in data and data['action']['action_change_start_bat'] == "1":
        if os.path.exists(wDir):
            # print("aqui estoy, voy a sobreescribir star_bat")
            # Abro el archivo .start.bat del claymore
            with open(target,'w') as f:
                # Escribo la nueva configuracion del startbat enviado desde el WS
                # f.write(windows_config + data['properties']['start_bat_data'] + '"')
                f.write(windows_config + data['properties']['start_bat_data'])
            f.closed
            # limpio los accesos directors para estar seguro
            crear_acceso_directo_start_bat()
            reiniciar_claymore()
            # seteo el action en ceero para que no vuelva a entrar en el ciclo de configuracion del start bat
            data['action']['action_change_start_bat'] = "0"
        else:
            print("El directorio no existe")

def descargar_nueva_version_claymore():
    if 'action' in data and data['action']['action_download_claymore_version'] == "1":
        zipurl = web_server + "/data/Claymore.zip"

        filename = "Claymore.zip"
        # dest = r"C:\\Users\\Miner\\Miners\\Claymore\\" + filename
        dest = config_rig_dir + r'\\' + filename

        # Getting the zip file in the server
        response = urlopen(zipurl)
        
        # Read the bytes of the zip file and create the zipfile in my pc
        zipcontent = response.read()
        with open(dest, 'wb') as f:
            f.write(zipcontent)
        f.close()

        # Extract the files 
        with ZipFile(dest) as zipfile:
            # zipfile.extractall(r"C:\\Users\\Miner\\Miners\\Claymore")
            zipfile.extractall(config_rig_dir)
        zipfile.close()
        
        # Save the claymoreReader directory
        # on all the new directories extracted 
        setup_claymore_reader()

        # # Change the new dir paths
        # target = dest + "\\start.bat"
        # wDir = dest

        #crear_acceso_directo_start_bat()
        #reiniciar_claymore()
        data['action']['action_download_claymore_version'] = "0"

def reset_rig():
    global client_socket
    if 'action' in data and data['action']['action_reset_rig'] == "1":
        client_socket.sendto(bytes("1", "utf-8"), address)
        print("Paquete enviado")
        data['action']['action_reset_rig'] = "0"

def setup_claymore_reader():
    # dest = r"C:\\Users\\Miner\\Miners\\Claymore"
    dest = config_rig_dir
    dest_directories = os.listdir(dest)

    src = os.path.dirname(os.path.realpath(__file__)) + r"\ClaymoreReader"
    src_files = os.listdir(src)
    for directory_name in dest_directories:
        full_directory_name = os.path.join(dest, directory_name)
        for file_name in src_files:
            full_file_name = os.path.join(src, file_name)

            if os.path.isfile(full_file_name) and os.path.isdir(full_directory_name):
                shutil.copy(full_file_name, full_directory_name)

def setup_dir():
    global target
    global wDir
    global ClaymoreReaderPath


    if os.path.exists(config_rig_dir + r"\dir_info.txt"):
        file = open(config_rig_dir + r"\dir_info.txt", "r") 
        lines = file.readlines()
        target = lines[0]
        wDir = lines[1]
        ClaymoreReaderPath = lines[2]

    file.close() 

def main():
    setup_claymore_reader()
    setup_dir()
    crear_acceso_directo_start_bat()
    obtener_myIP()
    inicializar_cliente_TCP()
    iniciar_claymore()

    while True:
        try:
            web_get_request_JSON()
            time.sleep(2)
        except:
            print('ERROR GET')
            time.sleep(2) # Reintentar en 2 segundos
            pass
        else:
            print("...\n\n\n")
            descargar_nueva_version_claymore()
            cambiar_start_bat()
            cambiar_version_claymore()
            request_reiniciar_claymore()
            # Revisar si esta accion hace falta por que creemos que la accion de reiniciar RIG es directamente desde el WS al Arduino
            reset_rig()
            # resetear_RIG()
main()