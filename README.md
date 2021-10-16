# Finish-Th-(is)
Finish Th-(is) is an application that brings the convenience of a mobile keyboard's predictive text to the desktop system-wide. It works the same way you would expect your phone's keyboard to -- often finishing sentences for you with perfect spelling. To use, start typing and press 1, 2, or 3 on your keyboard to fill in a word. When minimized, the application suspends and is not capturing input until it is in view again.
# Demo
# Binaries
To run, download the release and execute java -jar "Finish Th-(is).jar";<br>Java 16+ required.
# Building
This project was built using the [Azul Zulu OpenJDK with JavaFX](https://www.azul.com/downloads/?package=jdk-fx). 
However, this project builds with other recent JDKs (16+) with both dependencies met. On the first compilation, the pm.ser and stn.ser files are generated from the training set and will not be regenerated unless deleted. The binaries come with both of these for the sake of convenience.
### Dependencies
[JavaFX](https://openjfx.io/) - only required for alternative JDKs<br>
[jnativehook](https://github.com/kwhat/jnativehook) - version 2.1.0
# License
GPLv2
