String NhietDo = "55", KhiGa = "55", StringData = "";
String Cua = "OFF", DenBep = "OFF", DenKhach = "OFF", DenNgu = "OFF", Quat = "OFF", Rem = "OFF";
long long last;
void setup()
{
  Serial.begin(9600);
  pinMode(13, OUTPUT);
  last = millis();
}

void loop()
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
    int H = data.indexOf("h");
    Quat = data.substring(A + 1, B);
    Cua = data.substring(B + 1, C);
    Rem = data.substring(C + 1, D);
    DenKhach = data.substring(D + 1, E);
    DenNgu = data.substring(E + 1, G);
    DenBep = data.substring(G + 1, H);

  }
  //  Serial.println("a"+Quat+"b"+Cua+"c"+Rem+"d"+DenKhach+"e"+DenNgu+"g"+DenBep+"h");

  if (millis() - last > 500)
  {
    StringData = "a" + NhietDo + "b" + KhiGa + "c";
    Serial.println(StringData);
    last = millis();
    NhietDo=random(10,30);
    KhiGa=random(15,60);
  }

  if (Quat == "ON")
  {
    digitalWrite(13, 1);
  }
  else
  {
    digitalWrite(13, 0);
  }

}
