Parley REDAME


Introduction:

This is a README file for the program Parley. Parley is a messaging app on Android system. It based on the java language, and based on the github and Android Studio.


Included:

A folder named ".idea" with several files in. In below is the list of these files:

	compiler.xml
	gradle.xml
	misc.xml
	modules.xml
	runConfigurations.xml
	vcs.xml
	"copyright" folder with profiles_settings.xml in

A folder named "app" with several files in. In below is the list of these files:

	"build" folder with -- 
		"generated/res/google-services/debug/values" folder with values.xml in
		"intermediates" folder with -- incremental, manifests/full/debug, res, and symbols/debug in
		"outputs/logs" folder with manifest-merger-debug-report.txt in

	"src" folder with --
		"androidTest/java/com/parley/parley" folder with ExampleInstrumentedTest.java in
		"main" folder with --
			"java/com/parley/parley" folder with --
				ChatMessage.java
				FontSettingsActivity.java
				MainActivity.java
				MainActivity.java_
				Settings.java
				idTask.java
			"res" folder with --
				"drawable" folder
				"layout" folder
				"menu" folder
				"mipmap-hdpi" folder
				"mipmap-mdpi" folder
				"mipmap-xhdpi" folder
				"mipmap-xxhdpi" folder
				"mipmap-xxxhdpi" folder
				"values-w820dp" folder
				"values" folder
		"test/java/com/parley/parley"folder with ExampleUnitTest.java in

	.gitignore
	app.iml
	build.gradle
	google-services.json
	proguard-rules.pro
	
A folder named "gradle/wrapper" with several files in. In below is the list of these files:

	gradle-wrapper.jar
	gradle-wrapper.properties

.gitignore

SystemRequirements.pdf

build.gradle

clientID.txt

gradle.properties

gradlew

gradlew.bat

keys.jks

local.properties

parley.iml

settings.gradle

this README file


Project Parley:

Our goal for our Parley project is buliding a convenient customizable messaging-chatting appliacation. It has the function of a group chatting; the function of voice messaging; the functions of customming your font, background color, textbubble colors and shapes of your chatting windows; the functions of saving, deleting and checking your chatting message history; and the functions of creating a new account or using your google account to log in.

The main folder is the most important folder for this project. It contains almost everything the most main functions of this project. Messaging, Font-Setting, Time-Setting, Textbubble setting, background color setting, the other setting for the main system and so on.

For ChatMessage.java, which is used to control the functions of text message, user and text time and contains a public class ChatMessage. 

For the Settings.java, it controls the textbubble's shape and color. 

For idTask.java, it controls the user Account information system. All the functions as creating an new ID, setting a password, checking the ID statues and so on. 

For FontSettingsActivity.java, it controls the font of all the texts, as if a user want to change the font of the size of text.

All of these four java files are related to the MainActivity.java. The MainActivity.java controls almost every main activity of the Parley system. It could be seen as the main java file in the whole project.
