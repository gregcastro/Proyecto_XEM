import requests
import json
import time
import socket
from multiprocessing import Queue
import os.path
import datetime
import uuid

"""
Lectura de Claymore
"""
host = 'localhost'
port = 3333
loop_seconds = 10
config_rig_dir = r"C:\Claymore"
web_server_address = 'http://10.0.1.143:8081'

gpu_info = {}


user_email = ''
rig_name = ''
claymore_version = ''

# Time Keeper
start_time = time.time()
tick_time = start_time
# 

# Check Local Ip Address
gpu_info["rig_lan_ip"] = socket.gethostbyname(socket.gethostname())

def inicializar_cliente_TCP():
    global address
    global client_socket
    address = ('192.168.0.254', 8888) #Define who you are talking to (must match arduino IP and port)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #Set Up the Socket
    client_socket.settimeout(2) #only wait 1 second for a resonse


def main():

	print(gpu_info["rig_lan_ip"])

	# check if 10 seconds have passed since last sent request
    # if time.time() - tick_time > seconds_between_requests:
        # Send request

	while True:

		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

		try:
			s.connect((host, port))
			print("connected")
			s.send('{"id":0,"jsonrpc":"2.0","method":"miner_getstat1"}'.encode("utf-8"))
			j = s.recv(2048)
			s.close()                                                                                                                                      
			resp = json.loads(j.decode("utf-8"))
			resp = resp['result']
			print('resp = ', resp)
			muchos()

			get_miner_data()

			# Fill variables that are not parsed from the console.
			gpu_info["rig_email"] = user_email
			gpu_info["rig_name"] = rig_name
			gpu_info["rig_claymore_version"] = resp[0].split(' - ')[0]

			# calculate elapsed time.
			# gpu_info["rig_time_up"] = "{}".format(datetime.timedelta(seconds=int(time.time() - start_time)))

			gpu_info['rig_uuid'] = read_miner_uuid()
			gpu_info["rig_time_up"] = resp[1]
			gpu_info['location_uuid'] = None

			array_pools = resp[7].split(';')
			array_mhs = resp[3].split(';')
			array_fan_temp = resp[6].split(';')

			array_fan = []
			array_temp = []

			N = len(array_fan_temp)

			for i in range(0, N):
				if (i%2 == 0):
					array_temp.append(array_fan_temp[i])
				else:
					array_fan.append(array_fan_temp[i])


			rig_gpu_info_eth = []
			aux = {}
			N = len(array_mhs)

			for i in range(0, N):
				aux['Mhs'] = array_mhs[i]
				aux['Temp'] = array_temp[i]
				aux['Fan'] = array_fan[i]
				aux['Model'] = None
				rig_gpu_info_eth.append(aux)

			gpu_info['rig_gpu_info_eth'] = rig_gpu_info_eth


			if (len(array_pools) == 1):
				gpu_info['rig_gpu_second_coin'] = None
				gpu_info['rig_gpu_info_second_coin'] = None
			else:
				second_coin = array_pools[1].split('.')[0]
				gpu_info['rig_gpu_second_coin'] = second_coin
				array_mhs = resp[5].split(';')
				rig_mhs_second_coin = []
				aux = {}
				for mhs in array_mhs:
					aux['Mhs'] = mhs
					rig_mhs_second_coin.append(aux)

				gpu_info['rig_gpu_info_second_coin'] = rig_mhs_second_coin


			print('\n########################\n')
			print('GPU_INFO = ', gpu_info)
			print('\n########################\n')

			# Llamada al Metodo POST con la informacion del RIG
			post_process()

		except TimeoutError:
			print("connection timeout")
		except ConnectionRefusedError:
			print("connection refused")
		except:
			print("exception")

		

		time.sleep(loop_seconds)
	


def read_miner_uuid():
	# If the rig_uuid exists then a use it. If isn't, I create it 
	if os.path.exists(config_rig_dir+r"\\rig_uuid.txt"):
		file = open(config_rig_dir+r"\\rig_uuid.txt", "r") 
		rig_uuid = file.read()
	else:
		while True:
			rig_uuid = str( uuid.uuid4() )
			r = requests.get(web_server_address+'/rig/'+rig_uuid, data={"gpu_info":str(gpu_info)})

			response = r.json()
			if 'rig_uuid' not in response:
				break

		# Create a File and Save the rig_uuid
		file = open(config_rig_dir+r"\\rig_uuid.txt","w") 
		file.write(rig_uuid) 
		file.close()

	return rig_uuid

def get_miner_data():
	global user_email
	global rig_name
	global claymore_version

 	# Prueba leer linea por linea gpu_info_config.txt
	if os.path.exists(config_rig_dir+r"\\gpu_config.txt"):
		file = open(config_rig_dir+r"\\gpu_config.txt", "r") 
		user_email = file.readline()

		user_email = user_email[user_email.find("=")+1: len(user_email)]
		user_email = user_email.replace(" ", "")
		user_email = user_email.replace("\n", "")

		rig_name = file.readline()
		rig_name = rig_name[rig_name.find("=")+1: len(rig_name)]
		rig_name = rig_name.replace(" ", "")
		rig_name = rig_name.replace("\n", "")

		claymore_version = file.readline()
		claymore_version = claymore_version[claymore_version.find("=")+1: len(claymore_version)]
		claymore_version = claymore_version.replace(" ", "")

	else:
		#Ver que se hace en este caso.. 
		print("The file doesn't exits")

def post_process():
	try:
		r = requests.post(web_server_address+'/rig', data=str({"gpu_info":str(gpu_info)}) )

		json_response = r.json()

		print('json_response = ', json_response)

	except:
		print('ERROR POST')
		pass


def muchos():
	print('\n##############################\n')


if __name__ == "__main__":
    main()