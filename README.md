
# Assignment 2 -CMPE 277 Smartphone - Cloud Connected App
## App Name : TextReco
### Prof. Kaikai Liu
Submission for - Manjiri Kadam

* Topic9 iOS/Android App: interact with Cloud AI services for any of the following features: speech recognition, image segmentation, object detection, gesture/keypoint recognition, recommendation, or natural language processing (chatbot). 
You need to have the user interface to send your data and display the results. You need to show multiple testing cases for your selected Cloud AI services.

####

### Technologies used :
* Android Studio as IDE
* Kotlin Language for programming
* Text recognition library by Google Cloud - Firebase ML API's (ML kit for Cloud- Cloud Vision API)

### Features:
* Clicking Image
* After taking image, my app will detect the text by clicking "Detect button"
* Output will be shown on text view. 

### Working :
* With ML kit one can recognize the text from the image, with the cloud based API's we can extract text from image of document. There are two type of Firebase ML kit available 1) On-device 2) Cloud. I chose to use Cloud API.
* This ML kit is the core of my application, here with the help of the camera and internet, can use prediction models by Google to extract the text.
* I followed the instructions from - https://firebase.google.com/docs/android/setup?authuser=0



### Resources:

https://firebase.google.com/docs/ml/android/recognize-text
https://firebase.google.com/docs/reference/android/com/google/firebase/ml/vision/text/FirebaseVisionTextRecognizer
https://console.firebase.google.com/u/0/project/recono-e2425/ml/apis
https://console.cloud.google.com/apis/api/vision.googleapis.com/overview?project=textreco-a69a9

### Use case:
* Can be used for scanning the documents.
* Capturing the credit card information
* Translation

### UI:
App contains two pages (for Assignment 2). One is splash page, which has a logo and welcome screen. Second is main activity, where we have two button, one to take picture other one to detect the text. Output will be shown on text box.

#### 1) Splash Page
<img src="Output/1.jpeg" width="350">

#### 2) Main Activity
<img src="Output/2.jpeg" width="350">


### Testing Cases:

Please see the outputs for different test scenarios:

<p align="center">
  <img src="Output/3.jpeg" width="350" title="Output 1">
  <img src="Output/4.jpeg" width="350" title="Output 2">
  <img src="Output/5.jpeg" width="350" title="Output 3">
</p>

### Video Link for Assignment 2 - [Video](https://youtu.be/NupNvB7zZq4)

### Application is work in progress. Developing it for Final project.

# Final Project :
# App Name: Recono
### Prof. Kaikai Liu
Submission for - Manjiri Kadam
### Objective of app-
#### This app will recognize text from images, and can detect the object on the images, not only that, it will also help us know more about the detected object / text by search feature (Kind of Google Lens). This app is extension of assignment 2

### Technologies used :
* Firebase ML kit for text recognition
* TensorFlow Lite for object detection
* Google search API

### Features:
* Click images, detect the text over image
* Search meaning/reference over internet
* Select images from gallery and try to detect the object using Tensorflow lite library.
* Search similar reference/meaning of object over internet.

### UI:
This application has 4 activities, one is Splash page, one is Welcome Activity, Object Detection Activity and Text Recognition Activity.
Please see the below screenshots for the UI:
<p align="center">
  1) Splash Page 
  <br />
  <img src="Output/13.jpeg" width="350" title="Splash Page">
  2) Welcome Page
  <br />
  <img src="Output/8.jpeg" width="350" title="Welcome Page">
  3) App Icon
  <br />
  <img src="Output/12.jpeg" width="350" title="Logo">
  4) Output of Object Detection
  <br />
  <img src="Output/9.jpeg" width="350" title="Object Detection">
  5) After clicking on search button
  <br />
  <img src="Output/11.jpeg" width="350" title="Search for Object">
  6) Text Detection
  <br />
  <img src="Output/10.jpeg" width="350" title="Text Detection">
  
</p>

