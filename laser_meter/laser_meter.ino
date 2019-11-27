#include <Servo.h>
#include <Wire.h>
#include <VL53L0X.h>

#define UART_SPEED 9600
#define TIME_FOR_STEP_MS 500
#define START_ANGLE_POSITION 30
#define STEP_ANGLE_POSITION 20
#define END_ANGLE_POSITION 120
#define AVERANGE_COUNT 5
#define ERROR_VALUE 8000

VL53L0X sensor;
Servo myservo;  // create servo object to control a servo
// twelve servo objects can be created on most boards

int pos = 0;    // variable to store the servo position

void setup() {
  pinMode(2, OUTPUT);
   Serial.begin(9600);
  Wire.begin();

  sensor.init();
  sensor.setTimeout(500);

 sensor.setMeasurementTimingBudget(200000);
  
  myservo.attach(3); 
  digitalWrite(2,HIGH);
}

void loop() { 
  for (pos = START_ANGLE_POSITION; pos <= END_ANGLE_POSITION; pos += STEP_ANGLE_POSITION) { 
    myservo.write(pos);              
    writeLongRange(pos);
    delay(TIME_FOR_STEP_MS);
  }
  for (pos = END_ANGLE_POSITION; pos >= START_ANGLE_POSITION; pos -= STEP_ANGLE_POSITION) { 
    myservo.write(pos);             
    writeLongRange(pos);
    delay(TIME_FOR_STEP_MS);                   
  }
}

void writeLongRange(unsigned int i){
  Serial.print(averageDistance(AVERANGE_COUNT));
  if (sensor.timeoutOccurred()) { Serial.print("8000"); }
  Serial.print("-");
  Serial.print(i);

  Serial.println();
}

long averageDistance(unsigned int count) {
  long result = 0;
  int i = 0;
  int temp = 0;
  do {
    temp = sensor.readRangeSingleMillimeters();
    if (temp < ERROR_VALUE) {
      result += sensor.readRangeSingleMillimeters();
      i++; 
    }
  } while (i <= count);
  return result/i; 
}
