*TRAILER*
https://github.com/user-attachments/assets/2b65351b-3572-49fd-a8e9-c767ef55e382



*Engilish*

This structure enables allows users to sign in quickly and securely using their phone numbers. The app leverages Firebase Authentication with an OTP (One-Time Password) verification system to ensure high security. Additionally, Auto Fill Hints feature is included to make it easier and faster for users to fill in their information.

Features
User-friendly interface
Secure phone number verification
Fast OTP verification
Firebase Authentication integration
Auto Fill Hints for easy information entry
Getting Started
Follow these instructions to get the project up and running on your local machine.

*Prerequisites*
* Android Studio
* Firebase account

*Installation*

1. Create Firebase Project:

* Go to Firebase Console and create a new project.
* Enable Firebase Authentication and Firebase Realtime Database services.

2.Integrate Firebase SDK:

* Download the google-services.json file from the Firebase Console and place it in the app folder of your project root directory.
  - ![jsonin](https://github.com/user-attachments/assets/76a2d8e0-e9ba-4f18-8af2-46fbe139caaf)


* Update your build.gradle files with Firebase SDK dependencies.

3.Project Code:

* Clone this repository:
    git clone https://github.com/0bera1/OTPauth.git
    cd OTPauth

* Open the project in Android Studio and ensure all dependencies are downloaded.

*Usage*
*Phone Number Verification*
1. MainActivity.kt:

* Manages the phone number verification process where users can enter their phone numbers to start the verification.
* Uses Firebase Authentication and PhoneAuthProvider for verification.

2. OtpVerifyNumberActivity.kt:

* Allows users to enter the OTP sent via SMS to complete the verification process.
* Verifies the entered OTP and signs in the user.

*Auto Fill Hints*

* Auto Fill Hints feature is added to enable users to quickly fill in information like phone numbers.

*Code Structure*

* "MainActivity.kt": Manages the phone number verification process for users.
* "OtpVerifyNumberActivity.kt": Allows users to enter and verify the OTP to complete the verification process.

*Contributing*
If you wish to contribute, please fork the repository, create a feature branch, and submit a pull request. For any suggestions or feedback, please open an issue.

*Contact*
For any questions regarding the project, feel free to contact me:

Email: ahmedberaekimci@gmail.com
LinkedIn: linkedin.com/in/ahmed-bera-ekimci
