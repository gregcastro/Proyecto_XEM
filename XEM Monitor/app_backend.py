import subprocess
import shutil
import os
import requests
import json
import socket
import time
from multiprocessing import Queue
from io import BytesIO
from urllib.request import urlopen
from zipfile import ZipFile
from win32com.client import Dispatch
import win32com.shell.shell as shell
import win32event
from win32com.client import GetObject

# data JSON model (GET request)
# {
#   "action": {
#     "action_change_claymore_version": false,
#     "action_restart_claymore": false,
#     "action_change_start_bat": false,
#     "action_download_claymore_version": false,
#     "action_reset_rig": false
#   },
#   "properties": {
#     "version": "nombre de version",
#     "start_bat_data": "configuracion de claymore",
#   }
# }

target = r"C:\Users\Miner\Miners\Claymore\Claymore's Dual Ethereum+Decred_Siacoin_Lbry_Pascal AMD+NVIDIA GPU Miner v9.5 - Catalyst 15.12-17.x - CUDA 8.0_7.5_6.5\start.bat"
wDir = r"C:\Users\Miner\Miners\Claymore\Claymore's Dual Ethereum+Decred_Siacoin_Lbry_Pascal AMD+NVIDIA GPU Miner v9.5 - Catalyst 15.12-17.x - CUDA 8.0_7.5_6.5"
icon = r"C:\Users\Miner\Miners\Claymore\Claymore's Dual Ethereum+Decred_Siacoin_Lbry_Pascal AMD+NVIDIA GPU Miner v9.5 - Catalyst 15.12-17.x - CUDA 8.0_7.5_6.5\start.bat"
data = json.loads('{"accion": {"cambiar_version_claymore": false,"reiniciar_claymore": false,"cambiar_start_bat": false,"descargar_nueva_version_claymore": false,"resetear_RIG": false},"propiedades": {"version": "version1","start_bat_data": "-wd 1 -r 1 -epool stratum+tcp://eth-us.dwarfpool.com:8008 -ewal 0x67Eb849500f4bf5fd5ac7dD34E65Fa0f02Bd09d9/LETHI19/luisgermim@gmail.com -esm 0 -mode 0 -epsw x -allpools 1 -mport -3333 -dpool stratum+tcp://dcr.suprnova.cc:3252 -dwal luisgermim.LETHI19 -dpsw 1234 -tt -77 -ttdcr 80 -fanmin 80","IP_Reset": "192.168.0.190"}}')
windows_config = 'setx GPU_FORCE_64BIT_PTR 0\nsetx GPU_MAX_HEAP_SIZE 100\nsetx GPU_USE_SYNC_OBJECTS 1\nsetx GPU_MAX_ALLOC_PERCENT 100\nsetx GPU_SINGLE_ALLOC_PERCENT 100\ntimeout /t 20\nprocess_printer.exe -c "EthDcrMiner64.exe '
myIP = ''
web_server = 'http://192.168.0.100:8081'

def web_get_request_JSON():
    global data

    # Find rig_uuid 
    if os.path.exists(wDir + r"\rig_uuid.txt"):
        # print("si existe el path")
        file = open(wDir + r"\rig_uuid.txt", "r") 
        rig_uuid = file.read()
        # print(rig_uuid)

        r = requests.get(web_server + '/action/by_rig/' + rig_uuid)

        ##################
        print("Status Code : " + str(r.status_code))
        # print(r.headers['content-type'])
        # print(r.json())
        ##################

        #hacer r.content da TypeError: Can't convert 'bytes' obj to str implicitly
        # data = json.loads(r.content)

        #linea agregada por gcastro
        data = r.json()

        #Print de gcastro
        print("data = " + str(data) + "\n")

    else:
        print('Error: No existe el path' + wDir + r"\rig_uuid.txt")

def obtener_myIP():
    global myIP
    hostname = socket.gethostname()
    myIP = socket.gethostbyname(hostname)
    print("Mi direccion IP es: ")
    print(myIP)

def inicializar_cliente_TCP():
    global address
    global client_socket
    address = ('192.168.0.254', 8888) #Define who you are talking to (must match arduino IP and port)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) #Set Up the Socket
    client_socket.settimeout(2) #only wait 1 second for a resonse

def crear_acceso_directo_start_bat():
    path = os.path.join(r"C:\Users\Miner\Miners\Claymore", "start.lnk")
    shell = Dispatch('WScript.Shell')
    shortcut = shell.CreateShortCut(path)
    shortcut.Targetpath = target
    shortcut.WorkingDirectory = wDir
    shortcut.IconLocation = icon
    shortcut.save()

def iniciar_claymore():
    se_ret = shell.ShellExecuteEx(fMask=0x140, lpFile=r"C:\Users\Miner\Miners\Claymore\start.lnk", nShow=1)
    time.sleep(5) #Esperar a que inicie el claymore

def reiniciar_claymore():
    os.system("TASKKILL /F /T /IM cmd.exe")
    time.sleep(3)
    iniciar_claymore()

def request_reiniciar_claymore():
    if data['action']['action_restart_claymore'] == "1":
        reiniciar_claymore()
        data['action']['action_restart_claymore'] = "0"

def cambiar_version_claymore():
    global target
    global wDir
    global icon
    if data['action']['action_change_claymore_version'] == "1":
        target = "C:\\Users\\Miner\\Miners\\Claymore\\" + data['propiedades']['version'] + "\\start.bat"
        wDir = "C:\\Users\\Miner\\Miners\\Claymore\\" + data['propiedades']['version']
        icon = "C:\\Users\\Miner\\Miners\\Claymore\\" + data['propiedades']['version'] + "\\start.bat"
        if os.path.exists(wDir):
            crear_acceso_directo_start_bat()
            reiniciar_claymore()
        data['action']['action_change_claymore_version'] = "0"

def cambiar_start_bat():
    if data['action']['action_change_start_bat'] == "1":
        if os.path.exists(wDir):
            with open(target,'w') as f:
           # with open(r"C:\Users\Miner\Miners\Claymore\start.txt",'w') as f:
                f.write(windows_config + data['properties']['start_bat_data'] + '"')
            f.closed
            crear_acceso_directo_start_bat()
            reiniciar_claymore()
            data['action']['action_change_start_bat'] = "0"

def descargar_nueva_version_claymore():
    # if data['accion']['descargar_nueva_version_claymore'] == True:
    if data['action']['action_download_claymore_version'] == "1":
        zipurl = web_server + "/data/Claymore.zip"
        with urlopen(zipurl) as zipresp:
            zipinfo = zipresp.infolist()
            filename = zipinfo[0].filename()
            dest = "C:\\Users\\Miner\\Miners\\Claymore\\" + filename
            if not os.path.exists(dest):
                with ZipFile(BytesIO(zipresp.read())) as zipfile:
                    zipfile.extractall(r"C:\Users\Miner\Miners\Claymore")
                zipfile.close()
                #src = r"C:\Users\Miner\Miners\Claymore\winpty_cygwin_process_printer"
                src = os.path.dirname(os.path.realpath(__file__)) + r"\winpty_cygwin_process_printer"
                src_files = os.listdir(src)
                for file_name in src_files:
                    full_file_name = os.path.join(src, file_name)
                    if os.path.is_file(full_file_name):
                        shutil.copy(full_file_name, dest)
                target = dest + "\\start.bat"
                wDir = dest
                icon = dest + "\\start.bat"
                crear_acceso_directo_start_bat()
                reiniciar_claymore()
        # data['accion']['descargar_nueva_version_claymore'] = False
        data['action']['action_download_claymore_version'] = "0"

#Borrar... este es reset_rig()
# def resetear_RIG():
#     global client_socket
#     if data['accion']['resetear_RIG'] == True and data['propiedades']['IP_Reset'] == myIP:
#         client_socket.sendto(bytes("1", "utf-8"), address)
#         print("Paquete enviado")
#         data['accion']['resetear_RIG'] = False

def reset_rig():
    global client_socket
    if data['action']['action_reset_rig'] == "1":
        client_socket.sendto(bytes("1", "utf-8"), address)
        print("Paquete enviado")
        data['action']['action_reset_rig'] = "0"

def setup_winpty_cygwin_process_printer():
    dest = "C:\\Users\\Miner\\Miners\\Claymore"
    dest_directories = os.listdir(dest)
    src = os.path.dirname(os.path.realpath(__file__)) + r"\winpty_cygwin_process_printer"
    src_files = os.listdir(src)
    for directory_name in dest_directories:
        full_directory_name = os.path.join(dest, directory_name)
        for file_name in src_files:
            full_file_name = os.path.join(src, file_name)
            if os.path.isfile(full_file_name) and os.path.isdir(full_directory_name):
                shutil.copy(full_file_name, full_directory_name)

def main():
    setup_winpty_cygwin_process_printer()
    obtener_myIP()
    inicializar_cliente_TCP()
    iniciar_claymore()

    while True:
        try:
            web_get_request_JSON()
            time.sleep(2)
        except:
            print('ERROR GET')
            time.sleep(2) # Reintentar en 1 segundo
            pass
        else:
            descargar_nueva_version_claymore()
            cambiar_start_bat()
            cambiar_version_claymore()
            request_reiniciar_claymore()
            reset_rig()
            # resetear_RIG()
main()