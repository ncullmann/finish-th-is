# Finish Th-(is)
Finish Th-(is) is an application that brings the convenience of a mobile keyboard's predictive text to the desktop system-wide. It works the same way you would expect your phone's keyboard to -- often finishing sentences for you with perfect spelling. To use, start typing and press 1, 2, or 3 on your keyboard to fill in a word. When minimized, the application suspends and is not capturing input until it is in view again.
# Demo
https://user-images.githubusercontent.com/56172266/138538196-30966dd9-0e25-4d38-a8cd-1db787de3d22.mp4
# Releases
[Download the latest release](https://github.com/ncullmann/Finish-Th-is/releases) for your platform.
### Linux
Install the appropriate [Azul Zulu OpenJDK with JavaFX for Linux](https://www.azul.com/downloads/?os=linux&package=jdk-fx) depending upon your distribution. Extract the tarball<br>`tar -xzvf Finish-Th-is.jar`, then run `java -jar Finish-Th-is.jar` in a terminal.<br> Alternatively, `<Zulu JavaFX JDK>/bin/java -jar Finish-Th-is.jar`
### Windows
Install the [Azul Zulu OpenJDK with JavaFX for Windows](https://cdn.azul.com/zulu/bin/zulu17.28.13-ca-fx-jdk17.0.0-win_x64.msi), extract the zip, then double-click the jar.
# Building
This project was built using the [Azul Zulu OpenJDK with JavaFX](https://www.azul.com/downloads/?package=jdk-fx). 
However, this project builds with other recent JDKs (17+) with both dependencies met. On the first compilation, the pm.ser and stn.ser files are generated from the training set and will not be regenerated unless deleted. The binaries come with both of these for the sake of convenience.
### Dependencies
[JNativeHook](https://github.com/kwhat/jnativehook) - version 2.1.0<br>
[JavaFX](https://openjfx.io/) - only required for alternative JDKs
# License
GPLv2
