import http.client
import re
import serial
import time
import collections
 
cell_pattern = re.compile(r'<td>([^<>]+)</td>')
properties = ("hostname", "ip:port", "uptime", "")
 
NodeStats = collections.namedtuple("NodeStats", "hostname ip_address port uptime restarts_count gpu_temperature pool version comments etherenum")
EthereumStats = collections.namedtuple("EthereumStats", "hashing_speed accepted_shares rejected_shares invalid_shares")
 
def main():
    data = download_data("localhost", 3333)
    payload = "{} {}\n{}".format(data.uptime, data.gpu_temperature, data.etherenum.hashing_speed)
    update_arduino("COM5", 9600, payload.encode())
    print(data)
 
def download_data(host, port):
    conn = http.client.HTTPConnection(host, port, timeout=15)
    conn.request("GET", "")
    response = conn.getresponse()
    html_data = response.read().decode()
    return parse_response(html_data)
   
def parse_response(html_data):
    record = cell_pattern.findall(html_data)
    print(record)
    ip, port = record[1].split(":")
    uptime, restarts_count = record[2].split()
    hashing_speed, _ = record[3].split(",")
    counters = _.split()[0].split("/")
   
    return NodeStats(
        hostname=record[0],
        ip_address=ip,
        port=port,
        uptime=uptime,
        restarts_count=restarts_count,
        gpu_temperature=record[5],
        pool=record[6],
        version=record[7],
        comments=record[8] if len(record) >= 9 else "",
        etherenum=EthereumStats(
            hashing_speed=hashing_speed,
            accepted_shares=counters[0],
            rejected_shares=counters[1],
            invalid_shares=counters[2])
    )
 
def update_arduino(com_port, bitrate, data):
    s = serial.Serial(com_port, bitrate)
 
    try:
        time.sleep(1)
        s.dtr = 0
        time.sleep(1)
        s.write(data)
        s.flush()
    finally:
        s.close()
   
 
if __name__ == "__main__":
    main()