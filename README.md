Parley REDAME


Introduction:

This is a README file for the program Parley. Parley is a messaging app on Android system. It is written in xml and java languages. The systems used to create this app include github and Android Studio.




Files Included:

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

Our goal for our Parley project is buliding a convenient customizable chatting application. It has the following functions:
	sending messages within the chat
	customizing your font, background color, textbubble colors and shapes of your chatting windows
	ability to delte all chat messages from the database, thus deleting them from every device
	ability to create a new account using an email
	using your email account and password to log in
	ability to sign out 

The main folder is the most important folder for this project. It contains the java classes that implement the functions of this project. 

Other important folders are included within the res folder. These include the drawable folder, the layout folder, and the values folder. The drawable folder contains all of the png and 9.png image files used throughout the app. The layout folder contains the xml files that determine the appearance of the front end. The activity_main.xml file has the layout code for the main chat activity. The message.xml file has the layout code to be placed within the listview to display the message, the message sender, and the date. The activity_settings.xml file determines the layout of the Settings page and the activity_font_setting.xml file determines the layout of the Font Settings page.

ChatMessage.java is used to save the message, the message sender, and the message time. 

Settings.java is used to save the background color choice, the text bubble shape choice, and the text bubble color choice. It saves these changes upon clicking the save button or the font customize button.

FontSettingsActivity.java saves the font style, color, and size. These changes are saved upon clicking the save button.

All of these four java files are related to the MainActivity.java. The MainActivity.java controls the chat interface of the Parley system. It retrieves the values saved from Settings.java and FontSettingsActvity.java and updates the chat interface according to the user choices. It also handles signing the user in and out.

The code in idTask.java was added to try and fetch the individual tokens to allow individual messaging. Although the code compiles it is not currently impleented.
