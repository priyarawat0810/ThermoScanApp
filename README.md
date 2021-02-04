**<h3 align="center"> T H E R M O S C A N </h3>**

<h5 align="center">Android application for thermal screening and maintaining its record!</h5>

----
<br>

#### Motivation:

Currently, we are fighting against an unseen virus which is spreading day by day. Due to which there is a need to escalate the screening and testing process. Though some countries have made vaccines for coronavirus, the mass production and distribution are still not completed. So, the battle is on and, we have to face it smartly.

As responsible citizens of this country, we wanted to contribute to tackling this situation more effectively. And this project is one such step towards it!
<br>
#### Problem:
Thermal screening is a preventive measure since fever is one of the most common symptoms of COVID-19. Although contactless IR thermometers are available, they are incapable of maintaining the record of patients.
<br>
#### Solution:
If we found a suspect of COVID-19 and we have done his/her thermal
screening using the proposed setup. Then, we will be able to store his/her picture along body temperature and GPS location which will help us keep track of him/her also, people around him/her could be alerted about the patients' health conditions.
<br>
#### Hardware
Arduino Nano is used as it has an in-built USB interface which is important to communicate with the android phone using the ThermoScan App.
Sensors used:
- [MLX90614](https://www.melexis.com/en/product/MLX90614/Digital-Plug-Play-Infrared-Thermometer-TO-Can)
- [TCRT5000](https://components101.com/sensors/tcrt5000-ir-sensor-pinout-datasheet)
<p align="center">
  <img src=https://user-images.githubusercontent.com/43718753/106825060-1ce25300-66aa-11eb-9b75-c3d95a8cd574.png width="285" height="285">
</p>

<br>

#### Features:
- Registration and mobile verification (OTP)
- Login using Firebase Authentication
- Sceen body temp. and save photos with GPS location
- Save record to Firebase Firestore 
- Record history with details
- Search record
- Update profile picture and details
- Forgot password
- Logout
<br>

#### User Interface:
<table><tr><td>
 <img src="https://user-images.githubusercontent.com/43718753/106834205-f973d400-66ba-11eb-8aa5-40d2606f27c6.jpg" align="top">
  Scan, Upload, Records and Account feature</td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106834271-1f00dd80-66bb-11eb-9f49-f1ad7f11138b.jpg" align="top"> 
  Scanning temp. and saving photo with GPS location</td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106834316-3213ad80-66bb-11eb-940c-3215d1ade011.jpg" align="top">
  Uploading records with additional detail</td></tr><tr><td>
  <img src="https://user-images.githubusercontent.com/43718753/106834362-4fe11280-66bb-11eb-89b7-3e29cc015b80.jpg" align="top">
  Record History</td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106834666-e1e91b00-66bb-11eb-8d0d-d727bd587719.jpg" align="top">
  Searching records<br></td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106835138-c7fc0800-66bc-11eb-91cb-5b14732b248d.jpg" align="top">
  Checking record<br></td></tr><tr><td>
  <img src="https://user-images.githubusercontent.com/43718753/106835174-dcd89b80-66bc-11eb-92f3-de8b1fd0080a.jpg" align="top">
  Account<br></td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106835385-46f14080-66bd-11eb-8ab5-6b9629e6f9f6.jpg" align="top">
  Selected picture updated<br></td><td>
  <img src="https://user-images.githubusercontent.com/43718753/106835301-1f01dd00-66bd-11eb-8b61-f99e7d47d0ca.jpg" align="top">
  Deleting profile picture<br></td></tr></table>
