Using the Keytool
===================

You can generate a signature by running the keytool that comes with the Java JDK. The following shows how to export the key for your app using the debug defaults specified by the Android SDK and Eclipse.

PLEASE READ - the keytool.exe silently generates a keyhash even if it can't find the debug.keystore or if the password is incorrect. Make sure that you have provided the correct path to the debug.keystore. For Windows, it is generally at C:\Users\<user>\.android\ and for Mac at /Users/<user>/.android/. Also make sure you are using the correct password - for the debug keystore, use 'android' to generate the keyhash. General Rule: If the tool does not ask for password, your keystore path is incorrect. More info under 'Signing in Debug Mode' on the Signing Your Applications. Refer to Troubleshoot section below for more tips on keyhash.

```shell
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
```
This tool generates a string that must be registered in the Mobile section of the Developer App for your app. Remember to click 'Save Changes' to save the keyhash.

*Keytool command not found error: Keytool is provided by the standard Java JDK. if you get 'Command not found' error, you need to either add its path to your global 'PATH' or go to the folder to run this command. Standard location on Windows is C:\Program Files (x86)\Java\jdk1.6.0\bin and for MAC, its /usr/bin

https://developers.facebook.com/attachment/AndroidSSO_keyhash.png

+https://developers.facebook.com/docs/mobile/android/build/#sso
+http://developer.android.com/guide/publishing/app-signing.html