    Getting Started
	        Websites
			        Apps on Facebook
					        Mobile
							        Samples & How-Tos
									        Videos
											    Core Concepts
												    Advanced Topics
													    SDK Reference
														    Tools
															
															    Getting Started
																        Mobile Distribution
																		    Mobile Web
																			        Mobile Web Tutorial
																					    iOS
																						        iOS Tutorial
																								        iOS SDK
																										    Android
																											        Android Tutorial
																													        Android SDK
																															        Hackbook for Android
																																	
																																	Android Tutorial
																																	Getting Started › Mobile › Android Tutorial
																																	
																																	This document will guide you through Facebook Platform integration for Android. We will walk you through a tutorial to show the key steps to building a social Android app. This will include showing you how to enable Single Sign-On. We will also cover additional topics around integrating with Facebook Platform.
																																	
																																	Getting started
																																	
																																	  1. Register your Android App with Facebook
																																	    2. Download and install the Android SDK
																																		
																																		Prepare your project
																																		
																																		  3. Create New Facebook SDK Project
																																		    4. Add reference to the Facebook SDK
																																			  5. Add your app's signature to the Facebook App Settings
																																			  
																																			  Single Sign On
																																			  
																																			    6. Enable Single Sign-On for your App
																																				
																																				    6.1. Modify AndroidManifest.xml for the Network calls
																																					    6.2. Single-Sign-On (SSO)
																																						    6.3. Install the Facebook Android App
																																							    6.4. Build and run the project
																																								    6.5. More permissions
																																									    6.6. Save your access token
																																										
																																										  7. Enable user to Log Out of your App
																																										    8. Extending the access token
																																											
																																											Adding social context
																																											
																																											  9. Use the Graph API
																																											    10. Social Channels
																																												  11. Timeline and Open Graph
																																												  
																																												  Error Handling
																																												  
																																												  Handle Errors
																																												  Tips
																																												  Troubleshoot
																																												  
																																												  Samples
																																												  
																																												  Hackbook for Android
																																												  Open Graph Wishlist
																																												  Step 1: Register your Android app with Facebook
																																												  
																																												  To begin integrating with the Facebook Platform, create a new app on Facebook and enter your app's basic information.
																																												  
																																												  
																																												  Note your App ID. You are going to need it when integrating Facebook Android SDK into your code.
																																												  You application is now set up and you’re ready to begin integrating with Facebook!
																																												  Step 2: Download and install the Android SDK
																																												  
																																												      Install Eclipse if you don't have it already.
																																													      Set your Java Compliance Level to Java 1.6: Eclipse->Preferences->Java->Compiler->Compiler Compliance Level->1.6
																																														      Install Android SDK & the Eclipse Plugin
																																															      Install Git: Win Setup, OSX Setup, Linux Setup
																																																      Clone the GitHub repository: git clone git://github.com/facebook/facebook-android-sdk.git
																																																	      For Emulator testing, create Virtual devices by going to Eclipse->Window->Android SDK and AVD Manager->Virtual devices
																																																		  
																																																		  Step 3: Create New Facebook SDK Project
																																																		  
																																																		  For the first time, you will need to create a new Android project for the Facebook SDK source which can be reference from your app. This is only required once. Open Eclipse and create a new Android Project (File | New | Project | Android Project) for the Facebook Android SDK source and later reference it from your app. Get the content by selecting Create project from existing source and specifying the facebook directory from your git repository (~/facebook-android-sdk/facebook).
																																																		  Step 4: Add reference to the Facebook SDK
																																																		  
																																																		  Create a new Android project for your app or use your existing project and add a reference to the Facebook SDK project. You do this by opening the properties window for your app (File | Properties | Android), pressing the Add... button in the Library area and selecting the Facebook SDK project created above.
																																																		  Step 5: Add your app's signature to the Facebook App Settings
																																																		  
																																																		  Facebook requires an additional layer of security for mobile apps in the form of an application signature. You need to put your Android application signature into your Facebook app settings.
																																																		  
																																																		  There are two ways to find your Android application signature:
																																																		  
																																																		      From debug logs generated during Single Sign On when there is a signature mismatch.
																																																			  
																																																			      From the Java JDK keytool.
																																																				  
																																																				  Debug Log Data Setup
																																																				  
																																																				  Using this method you will turn on debugging in the Android SDK then view the error logs during the Single Sign On step later on in this tutorial. The error logs will provide you with information about the signature sent to Facebook.
																																																				  
																																																				  To turn on debugging in the Android SDK, modify Util.java:
																																																				  
																																																				  private static boolean ENABLE_LOG = true;
																																																				  
																																																				  Skip to the next step in this tutorial. The instructions in the build step will show you how to get the application signature that you should then register in the Mobile section of the Developer App.
																																																				  Using the Keytool
																																																				  
																																																				  You can generate a signature by running the keytool that comes with the Java JDK. The following shows how to export the key for your app using the debug defaults specified by the Android SDK and Eclipse.
																																																				  
																																																				  PLEASE READ - the keytool.exe silently generates a keyhash even if it can't find the debug.keystore or if the password is incorrect. Make sure that you have provided the correct path to the debug.keystore. For Windows, it is generally at C:\Users\<user>\.android\ and for Mac at /Users/<user>/.android/. Also make sure you are using the correct password - for the debug keystore, use 'android' to generate the keyhash. General Rule: If the tool does not ask for password, your keystore path is incorrect. More info under 'Signing in Debug Mode' on the Signing Your Applications. Refer to Troubleshoot section below for more tips on keyhash.
																																																				  
																																																				  keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
																																																				  
															This tool generates a string that must be registered in the Mobile section of the Developer App for your app. Remember to click 'Save Changes' to save the keyhash.
