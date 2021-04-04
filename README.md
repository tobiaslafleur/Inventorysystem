# ECINV

##How to run
1. Clone the repository
![Screenshot](src/resources/images/readme/clone.png)

2. Download the javafx SDK for your operating system from https://gluonhq.com/products/javafx/
![Screenshot](src/resources/images/readme/download_sdk.png)

3. Extract the folder from the zip and save it somewhere easily accessible (ex. Documents)

4. Open the cloned repository with IntelliJ IDEA

5. Go to File -> Project Structure -> Project, and set the project SDK to 15
![Screenshot](src/resources/images/readme/sdk15.png)

6. Go to File -> Project Structure -> Libraries and add the JavaFX 15 SDK as a library to the project. Point to the lib folder of the JavaFX SDK.
![Screenshot](src/resources/images/readme/library.png)

7. Go to Run -> Edit Configurations and write either of the following, depending on your operating system
<br/>
<br/>
Linux/Mac
<br/>
--module-path /path/to/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml
<br/>
<br/>
Windows
<br/>
--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
![Screenshot](src/resources/images/readme/vm_options.png)

8. Now simply run Main.java