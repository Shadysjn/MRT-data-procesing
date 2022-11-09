/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.

  This example code is in the public domain.
 */

 #include <SD.h>

// Pin 13 has an LED connected on most Arduino boards.
// Pin 11 has the LED on Teensy 2.0
// Pin 6  has the LED on Teensy++ 2.0
// Pin 13 has the LED on Teensy 3.0
// give it a name:

// the setup routine runs once when you press reset:
void setup() {
  // initialize the digital pin as an output.
 Serial.begin(9600);
 Serial.println("Hello there");
 Serial.println("Hello there");
 //setup for sd card 
 if (!SD.begin(BUILTIN_SDCARD)) {
    Serial.println(F("SD CARD FAILED, OR NOT PRESENT!"));
    while (1); // don't do anything more:
  }else {
    Serial.println(F("SD CARD FOUND")); 
  }
  
}
File file = SD.open("ardunio.txt",FILE_WRITE);
long tStart = millis();
long count = 0; 
double result = 0;
// the loop routine runs over and over again forever:
void loop() {
  if ((millis()-tStart) >= 10000){
      //do something 
         result = count/10000;
         String hzzs = String(result) + " Hz";
         Serial.println(hzzs);
         Serial.println(file.size());
         SD.remove("arduino.txt");
         while(true);
 
    }else {
        count++;
      //  file.write("val \n");
    }
    
}
