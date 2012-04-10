Using the Keytool
===================

You can generate a signature by running the keytool that comes with the Java JDK. The following shows how to export the key for your app using the debug defaults specified by the Android SDK and Eclipse.

PLEASE READ - the keytool.exe silently generates a keyhash even if it can't find the debug.keystore or if the password is incorrect. Make sure that you have provided the correct path to the debug.keystore. For Windows, it is generally at C:\Users\<user>\.android\ and for Mac at /Users/<user>/.android/. Also make sure you are using the correct password - for the debug keystore, use 'android' to generate the keyhash. General Rule: If the tool does not ask for password, your keystore path is incorrect. More info under 'Signing in Debug Mode' on the Signing Your Applications. Refer to Troubleshoot section below for more tips on keyhash.
