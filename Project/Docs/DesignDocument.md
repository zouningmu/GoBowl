# Design Document

This document is a desgin document for the use by Team 20 in the creation of the GoBowl android app. It will provide team members with technical details for building the application.

**Author**: Travis Wardlow (Team 20)

| Version | Description     |
| --------|:---------------:|
| V1      | Initial version |
| V2      | Alpha Version   |


## 1 Design Considerations

### 1.1 Assumptions

The assumptions of the GoBowl application are the following:

 1. The android device has the ability to read QR codes and credit cards through third party libraries.
 2. The user can only request one lane at a time.
 3.  There are an unlimited amount of lanes available and an unlimited    amount of guests can use a lane.
 4. The manager has no involvement in the checkout process.
 5. The prices for bowling are fixed and there are no specials other than the VIP.
 6.  If QR code reader gets error when scanning a customers card, assume that customer is not allowed to play
      and decrement the number of guests by 1. 
 7.	The rate is taken at the time of checkout. IE if you checkout at 4:30PM on a Monday it will be 20 dollars/hour.

### 1.2 Constraints


The constraints of the application tend to involve the UI portion. One constraint of the application is the size of the screen. Android devices come in different sizes from a small phone to a tablet. The application needs to be able to handle the small screen size. 

### 1.3 System Environment

The application must operate on an android device operating on at least API Level 15. The application must interact you the camera to be able to read the QR code for the application to read in the customer's information for different uses. Also the application will have to use the android hardware to process the credit card transactions. 

## 2 Architectural Design

This section of the document provides a high-level overview of the GoBowl Application. 

### 2.1 Component Diagram

![enter image description here](https://lh3.googleusercontent.com/-HCnASQEFKW4/V3SPbqbvgjI/AAAAAAAAAKc/0NorABK99Rkb-oQa6LqXOb---netpOfjACLcB/s0/ComponetDiagram.jpg "ComponetDiagram.jpg")

The GoBowl application is a simple application that is enclosed in on piece of software interacting with different hardware components on the Android device. The GoBowl UI will trigger different events to take place based on users input that will activate the middle layer that will process the user input and access the third party utilities if necessary.  

### 2.2 Deployment Diagram

The GoBowl application will be built in Android Studio. Also, Android Studio has the ability to run the application on a physical android device or an emulator. The assumption is that the Android device has the necessary third party libraries to read the QR code and process the credit card information. 

## 3 Low-Level Design



### 3.1 Class Diagram

![ClassDiagram](https://lh3.googleusercontent.com/gEvzj0hepbJ_QKdPXaE91u8RJBXFGfv0S0eWT047JfC5FDm7by60mlNJTZHWhxG6hDl3RqA=s0 "GoBowlTeam20.jpg")

   **Team 20 GoBowl Class Diagram**

### 3.2 Other Diagrams
![enter image description here](https://lh3.googleusercontent.com/-wi8e3QpWKAY/V3SIILmGBzI/AAAAAAAAAKA/mQdZ6kSNPqQ8yUZrtHIskDj3ZUpj7s-hgCLcB/s0/StateMachine.jpg "StateMachine.jpg")
State Diagram of the GoBowl Customer Screens 

## 4 User Interface Design
![enter image description here](https://lh3.googleusercontent.com/-bg0BsGCVE9Y/V3XFDVf-_6I/AAAAAAAAAK4/xovidtkG9KsDdC9z1iH5zfWNtt2PrPqFgCLcB/s0/Start.jpg "Start.jpg")
Start Screen Mockup for the GoBowl App

![enter image description here](https://lh3.googleusercontent.com/-Nz9ES_k3n58/V3R_EbwuGFI/AAAAAAAAAHI/3OvVMolwercLDbC5usC3d0Jxxtkk-qgmwCLcB/s0/WelcomeScreen.jpg "WelcomeScreen.jpg")
Mockup of the Customer Welcome Screen for the GoBowl App

![enter image description here](https://lh3.googleusercontent.com/-GXWT4i172iA/V3R_ToEvM0I/AAAAAAAAAHU/ICT9OtLHve8qT42Tbqjp2vLNnmB0kQLpwCLcB/s0/EnterGuest.jpg "EnterGuest.jpg")
Mockup of the Request Lane Guest Selector

![enter image description here](https://lh3.googleusercontent.com/-9s77tV79aoc/V3R_xg6I53I/AAAAAAAAAHg/uyHxDnSjG5cmzjzOdBn5zp6LuuTu_rncACLcB/s0/LaneScreen.png "LaneScreen.png")
Mockup of the Lane Assignment Screen

![enter image description here](https://lh3.googleusercontent.com/-fFtapgFTvz8/V3SAHh2_z3I/AAAAAAAAAH8/8VmTtxLGDJQ59ow8ATa2FpTGHvya15C-wCLcB/s0/Checkout.jpg "Checkout.jpg")
Mockup of the Checkout Screen

![enter image description here](https://lh3.googleusercontent.com/-JrtJLRcdMvM/V3SAVKJ-zjI/AAAAAAAAAIM/Tj28VI6BzvAxd3j3a0iaLaAXTO7vnildwCLcB/s0/SaveScores.jpg "SaveScores.jpg")
Mockup of the Save Scores Screen

![enter image description here](https://lh3.googleusercontent.com/-_rXtNjPNs90/V3SAcch_MEI/AAAAAAAAAIc/SzJjJwTSxFsvDv-hjP0MFhUoMKJggvKbQCLcB/s0/SplitBill.jpg "SplitBill.jpg")
Mockup of the Split Bill Screen

![enter image description here](https://lh3.googleusercontent.com/-OZcKQwWD-rc/V3XGO__4qNI/AAAAAAAAALQ/PlADR9XSUAISh5AYZ6H7UG2RSYWb35RTACLcB/s0/Start.jpg "Start.jpg")
Mockup of the GoBowl Start menu

![enter image description here](https://lh3.googleusercontent.com/-HhNbu4IT8hM/V3XGZvo6-YI/AAAAAAAAALY/viGPI4G2EWgQ12CtCEGVGxQ80lpzX5qcwCLcB/s0/manager.jpg "manager.jpg")
Mockup of the Manager menu
