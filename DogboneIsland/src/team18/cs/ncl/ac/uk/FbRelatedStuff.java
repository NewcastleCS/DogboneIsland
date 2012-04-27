// FbRelatedStuff.java --- 
// 
// Filename: FbRelatedStuff.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Fri Apr 27 15:55:06 2012 (+0100)
// Version: 
// Last-Updated: 
//           By: 
//     Update #: 0
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// Keeper of all information facebook, A static class where everything is loaded
// at the launch of the program.
// 
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import com.facebook.android.Facebook;

public  class FbRelatedStuff {
	public static Facebook facebook= new Facebook("294370397283238");
	public static long uid;
	public static String FirstName;
	public static String LastName;
	public static String Name;
	public static gender Gender;
	public static String ImagePath;
	public enum gender {male,female}
}
