#include <SPI.h> //Load the SPI Library
//#include <WiFi.h>
#include <ESP8266WiFi.h>
#include <WiFiUdp.h>
#include <Ethernet.h>

void printWifiStatus();
void seleccion_de_PC(char * packetBuffer);

#define D0 16
#define D1 5 // I2C Bus SCL (clock)
#define D2 4 // I2C Bus SDA (data)
#define D3 0
#define D4 2 // Same as "LED_BUILTIN", but inverted logic
#define D5 14 // SPI Bus SCK (clock)
#define D6 12 // SPI Bus MISO 
#define D7 13 // SPI Bus MOSI
#define D8 15 // SPI Bus SS (CS)
#define D9 3 // RX0 (Serial console)
#define D10 1 // TX0 (Serial console)

int inByte = 0;         // incoming serial byte
int MUXpin1 = D5;
int MUXpin2 = D6;
int MUXpin3 = D7;
int MUX1data = D3;
int MUX2data = D9;
int MUX3data = D10;
int MUX1INH = D0;
int MUX2INH = D1;
int MUX3INH = D2;

int reset_counter[24];

int status = WL_IDLE_STATUS;
const char ssid[] = "arduino"; //  your network SSID (name)
const char pass[] = "semeolvido";    // your network password (use for WPA, or use as key for WEP)
const unsigned int keyIndex = 0;            // your network key Index number (needed only for WEP)
// NETWORK: Static IP details...
IPAddress ip_static(172, 16, 10, 254);    
IPAddress ip_gateway(172,16,10,0); 
IPAddress ip_subnet(255,255,0,0); 
IPAddress ip_dns1(8,8,8,8);
IPAddress ip_dns2(8,8,4,4);

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

const unsigned int localPort = 8888;      // local port to listen on

const char host[] = "http://172.16.10.100";
const unsigned int httpPort = 8081;
WiFiClient client;

char packetBuffer[255]; //buffer to hold incoming packet
//char ReplyBuffer[] = (char)(reset_counter[(int)(packetBuffer-0x30)]+0x30);       // a string to send back

WiFiUDP Udp;

void setup() {
  pinMode(MUXpin1, OUTPUT);
  pinMode(MUXpin2, OUTPUT);
  pinMode(MUXpin3, OUTPUT);
  pinMode(MUX1data, OUTPUT);
  pinMode(MUX2data, OUTPUT);
  pinMode(MUX3data, OUTPUT);
  pinMode(MUX1INH, OUTPUT);
  pinMode(MUX2INH, OUTPUT);
  pinMode(MUX3INH, OUTPUT);

  pinMode(LED_BUILTIN, OUTPUT);

  //Initialize serial and wait for port to open:
  Serial.begin(9600);
  delay(10);
  Serial.println("Inicializando Arduino");
  
  // check for the presence of the shield:
  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("WiFi shield not present");
    // don't continue:
    while (true);
  }
  
  // static IP Setup Info Here...
  WiFi.config(ip_static, ip_gateway, ip_subnet, ip_dns1, ip_dns2); ;
  WiFi.mode(WIFI_STA);
  
  // attempt to connect to Wifi network:
  Serial.println();
  Serial.println();
  Serial.print("Attempting to connect to SSID: ");
  Serial.println(ssid);
  // connect to WPA/WPA2 network. Change this line if using open or WEP network:
  WiFi.begin(ssid, pass);
    
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("Connected to WiFi");
  printWifiStatus();
  // Connected to WiFi

  // start the Ethernet connection:
  Ethernet.begin(mac, ip_static);
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("Connected via Ethernet");

  // attempt to connect via UDP:
  Serial.println("\nStarting connection to server...");
  // if you get a connection, report back via serial:
  if(Udp.begin(localPort) == 1){
    Serial.println("UDP Connection successful");
  }
  else{
    Serial.println("UDP Connection failed");
  }
  // Connected via UDP
}

void loop() {
  digitalWrite(LED_BUILTIN, LOW);
  /*WIFI CLIENT*/
  Serial.print("connecting to ");
  Serial.println(String(host) + ':' + String(httpPort));
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  // We now create a URL for the request
  String url = "/xem/ResetRig";
  Serial.print("Requesting URL: ");
  Serial.println(url);
  
  // This will send the request to the server
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: close\r\n\r\n");
               
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
    seleccion_de_PC(line);
  }
  Serial.println();
  Serial.println("Closing connection");
  delay(10000);
  /*FINISH CLIENT*/
}

void seleccion_de_PC(String packetBuffer){
  /*SELECCION DE PC*/
  
    switch (packetBuffer.toInt()) {
    case 1:
      digitalWrite(LED_BUILTIN, HIGH);
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[0]++;
      break;
    case 2:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[1]++;
      break;
    case 3:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[2]++;
      break;
    case 4:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[3]++;
      break;
    case 5:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[4]++;
      break;
    case 6:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[5]++;
      break;
    case 7:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[6]++;
      break;
    case 8:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX1data, HIGH);
      digitalWrite(MUX1INH, LOW);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[7]++;
      break;
    case 9:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[8]++;
      break;
    case 10:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[9]++;
      break;
    case 11:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[10]++;
      break;
    case 12:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[11]++;
      break;
    case 13:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[12]++;
      break;
    case 14:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[13]++;
      break;
    case 15:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[14]++;
      break;
    case 16:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX2data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, LOW);
      digitalWrite(MUX3INH, HIGH);
      reset_counter[15]++;
      break;
    case 17:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[16]++;
      break;
    case 18:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[17]++;
      break;
    case 19:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[18]++;
      break;
    case 20:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, LOW);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[19]++;
      break;
    case 21:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[20]++;
      break;
    case 22:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, LOW);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[21]++;
      break;
    case 23:
      digitalWrite(MUXpin1, LOW);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[22]++;
      break;
    case 24:
      digitalWrite(MUXpin1, HIGH);
      digitalWrite(MUXpin2, HIGH);
      digitalWrite(MUXpin3, HIGH);
      digitalWrite(MUX3data, HIGH);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, LOW);
      reset_counter[23]++;
      break;
    default:
      digitalWrite(MUX1data, LOW);
      digitalWrite(MUX2data, LOW);
      digitalWrite(MUX3data, LOW);
      digitalWrite(MUX1INH, HIGH);
      digitalWrite(MUX2INH, HIGH);
      digitalWrite(MUX3INH, HIGH);
    delay(4000);
    }
 }
  /*FINISH SELECCION DE PC*/

void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your WiFi shield's IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}



