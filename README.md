# Restaurant-Review-App
Mobile App developed as per the requirements provided by the assignment for the MObile app development course.

Welcome to the README file for the "For Foodies By Foodies" (FFBF) mobile app repository. 
This app aims to provide a comprehensive foodie experience for Londoners, allowing them to explore restaurants, street food stalls, share reviews, and more. 
Please note that all diagrams, pictures, or images used in this repository are for educational purposes only.

## Table of Contents
1. [Introduction and Requirements](#introduction-and-requirements)
2. [Design and Documentation](#design-and-documentation)
   - [Mobile Application Map](#mobile-application-map)
   - [Classes Roles](#classes-roles)
   - [Wireframes](#wireframes)
3. [Testing the App](#testing-the-app)
4. [Exceptional Circumstances](#exceptional-circumstances)
5. [Source Code Snippets](#source-code-snippets)
6. [Appendix](#appendix)

## 1. Introduction and Requirements
In this repository, you'll find the code and documentation for the FFBF mobile app. 
The app aims to enhance the foodie experience in London by offering features like user login, restaurant/street food stall search, reviews, reservations, and more. 
The following requirements were set by the assignment:

- User authentication (login, registration, password reset)
- Add and view restaurants and street food stalls
- Make and view reservations
- Add, view, and delete reviews
- Forum for discussions
- Profile management
- Database connectivity

## 2. Design and Documentation

### Mobile Application Map
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/f5b01761-f92d-4f2c-a4cd-27efcb7dee15)


### Classes Roles

#### Adapter Classes
Adapter classes in Java are used to provide default implementations for listener interfaces. They help bridge between different frameworks without modifying existing code. Here are some adapter classes used in our app:

- StreetFoodHolder: Inner class for StreetFoodAdapter.
- StreetFoodAdapter: Manages StreetFood objects for display in StreetFoodActivity.
- PostAdapter: Manages Post objects for display in PostListActivity.
- RestaurantAdapter: Manages Restaurant objects for display in RestaurantActivity.
- EateryHolder: Inner class for RestaurantAdapter.

#### Activity Classes
Activity classes are the building blocks of Android apps. Each window or form corresponds to an activity. Some key activity classes in our app:

- Welcome: Entry point, transitions to the login page.
- MainActivity: Handles user navigation.
- NavigationMenuActivity: Common functionality inherited by all pages after login.
- ForgotPassword: Resets user password via email instructions.
- Login: Manages user login with email and password.
- Register: Registers new users.
- TermsAndConditions: Displays app's terms and conditions.
- Profile: Manages user profile information.
- UpdateUserInfo: Allows users to update profile details.
- ... (other activity classes)

#### Model Classes
Model classes represent data in the application. They are used for data transmission and storage. Some important model classes:

- User: Represents user data with getters and setters.
- StreetFood: Represents street food stall data with getters and setters.
- Post: Represents forum post data with getters and setters.
- Restaurant: Represents restaurant data with getters and setters.

### Wireframes
- Insert wireframes and UI mockups here.
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/1f056684-982c-4474-a78a-8a9bbc0c381b)
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/fcd58b50-2b4f-4bf2-94a3-5b340f3258d2)
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/06500aff-64c7-4c2b-a072-7cbb50cc3340)
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/0d237fa1-16eb-4c59-957d-3008fb9d2771)
- ![image](https://github.com/OVIDIU121/Restaurant-Review-App/assets/94175010/81db9876-28b4-4b33-a2d3-8b138cfd968e)



## 3. Testing the App
To test the app, follow these steps:
1. Clone the repository.
2. Set up the necessary development environment (Android Studio, Firebase, etc.).
3. Build and run the app on an emulator or physical device.
4. Test each feature and ensure it meets the requirements.
5. Report any issues or bugs in the "Issues" section of this repository.

## 4. Exceptional Circumstances
In exceptional cases, such as app crashes or unexpected behavior, refer to this section for troubleshooting tips or known issues.

## 5. Source Code Snippets
Here are some key source code snippets from the app:

- [Login Class](./src/Login.java)
- [Register Class](./src/Register.java)
- [AuthenticationUtility Class](./src/AuthenticationUtility.java)
- [NavigationMenuActivity Class](./src/NavigationMenuActivity.java)
- [Main Activity Class](./src/MainActivity.java)
- ... (other source code snippets)

## 6. Appendix
Additional information and references can be found in the appendix.

For any questions, suggestions, or bug reports, please contact our development team at devteam@example.com.
