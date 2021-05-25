#include <ESP8266WiFi.h>
#include "FirebaseESP8266.h"
#include <ArduinoJson.h>


#define LedPin 16

#define FIREBASE_HOST "hulasmarthome-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "kpHxhbv4GqP4GkpxvDdh4c3vbeEkTTcTrJr2UJC3"
#define WIFI_SSID "Van Tho 15"
#define WIFI_PASSWORD "vannhucu"

FirebaseData firebaseData;
String path = "/";
FirebaseJson json;
long long last = 0;
String Cua = "OFF", DenBep = "OFF", DenKhach = "OFF", DenNgu = "OFF", Quat = "OFF", Rem = "OFF",TuDong="OFF";
String KhiGa = "45", NhietDo = "28";
String StringData="";

void ReadDataToFirebase();
void WriteDataToFirebase(String NhietDo, String KhiGa);
void ReadUART();
void SendUART();
void setup() {
  pinMode(LedPin, OUTPUT);
  Serial.begin(9600);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ") ;
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
  if (!Firebase.beginStream(firebaseData, path))
  {
    Serial.println("REASON:+ " + firebaseData.errorReason());
    Serial.println();
  }
  last = millis();
}
void loop()
{
  ReadUART();
  WriteDataToFirebase( NhietDo,  KhiGa);
  ReadDataToFirebase();
  SendUART();
  if (Cua == "ON")
  {
    digitalWrite(16, 0);
  }
  else
  {
    digitalWrite(16, 1);
  }

}
void ReadDataToFirebase()
{
  if (Firebase.getString(firebaseData, path + "/Cua"))
  {
    Cua = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/DenBep"))
  {
    DenBep = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/DenKhach"))
  {
    DenKhach = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/DenNgu"))
  {
    DenNgu = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/Quat"))
  {
    Quat = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/Rem"))
  {
    Rem = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/DenNgu"))
  {
    DenNgu = firebaseData.stringData();
  }
  if (Firebase.getString(firebaseData, path + "/TuDong"))
  {
    TuDong = firebaseData.stringData();
  }
}
void WriteDataToFirebase(String NhietDo, String KhiGa)
{
  if (millis() - last > 500)
  {
    Firebase.setString(firebaseData, path + "/KhiGa", KhiGa);
    Firebase.setString(firebaseData, path + "/NhietDo", NhietDo);
//    NhietDo = random(15, 45);
//    KhiGa = random(30, 90);
    last = millis();
  }
  if(TuDong=="ON")
  {
    Firebase.setString(firebaseData, path + "/Quat", Quat);
    Firebase.setString(firebaseData, path + "/Cua", Cua);
    Firebase.setString(firebaseData, path + "/Rem", Rem);
    Firebase.setString(firebaseData, path + "/DenKhach", DenKhach);
    Firebase.setString(firebaseData, path + "/DenNgu", DenNgu);
    Firebase.setString(firebaseData, path + "/DenBep", DenBep);
  }
}
void ReadUART()
{
  if (Serial.available())
  {
    String data = Serial.readString();
    int A = data.indexOf("a");
    int B = data.indexOf("b");
    int C = data.indexOf("c");
    int D = data.indexOf("d");
    int E = data.indexOf("e");
    int G = data.indexOf("g");
    int H= data.indexOf("h");
    int I = data.indexOf("i");
    int K = data.indexOf("k");
    NhietDo = data.substring(A+1, B);
    KhiGa = data.substring(B+1, C);
    Quat = data.substring(C+1, D);
    Cua = data.substring(D+1, E);
    Rem = data.substring(E+1, G);
    DenKhach = data.substring(G+1, H);
    DenNgu = data.substring(H+1, I);
    DenBep = data.substring(I+1, K);
    //Serial.println(NhietDo + " " + KhiGa);
  }
}
void SendUART()
{
  StringData="a"+Quat+"b"+Cua+"c"+Rem+"d"+DenKhach+"e"+DenNgu+"g"+DenBep+"h"+TuDong+"i";
  Serial.println(StringData);
}
