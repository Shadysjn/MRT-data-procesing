/*
 * Created by ArduinoGetStarted.com
 *
 * This example code is in the public domain
 *
 * Tutorial page: https://arduinogetstarted.com/reference/library/arduino-file.print
 */

#include <SD.h>

#define PIN_SPI_CS 4

File file;

void setup() {
  Serial.begin(9600);
  
  if (!SD.begin(BUILTIN_SDCARD)) {
    Serial.println("SD CARD FAILED, OR NOT PRESENT!");
    while (1); // don't do anything more:
  }

  Serial.println("SD CARD INITIALIZED.");
  SD.remove("arduino.txt"); // delete the file if existed

  // create new file by opening file for writing
  file = SD.open("arduino.txt", FILE_WRITE | FILE_READ);
  int counter = 0; 
  double result = 0;
  if (file) {
    long sTime = millis();
  
    while (millis()-sTime < 17){
       int num = random(1,100000);
      // Serial.println("Hello there");
       file.write(num);
       //Serial.println(file.size());
       counter++;
    }
    result = counter+2;
    String hzzs = String(result) + " Hz";
    Serial.println(hzzs);
//    file.close();
  } else {
    Serial.print("SD Card: error on opening file arduino.txt");
  }
   if (file) { 
      while (file.available()) { //execute while file is available
      char letter = file.read(); //read next character from file
      Serial.print(letter); //display character
    }
   }
  if (file){
    Serial.println(file.size());  
  }
}

void loop() {
}
