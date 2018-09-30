/*
Date:  June 30, 2018 
Author: Deanna M. Presswood 
Title: Login App 
Description: Login Application that will log username, password, the date and
the time into a log.txt file.

 */
package sdev460;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.ButtonGroup;

public class loginApp extends Application {

    
@Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Welcome to SDEV460 Login App Hw1");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(20);
    grid.setVgap(20);

    // Create some text to place in the scene
    Text scenetitle = new Text("Login Application");

    grid.add(scenetitle, 0, 0, 2, 1);
try {
    // Create Label
    Label userName = new Label("User Name:");
    // Add label to grid 0,1
    grid.add(userName, 0, 1);
    // Create Textfield
    TextField userTextField = new TextField();
    // Add textfield to grid 1,1
    grid.add(userTextField, 1, 1);
    // Create Label
    Label pass = new Label("Password:");
    // Add label to grid 0,2
    grid.add(pass, 0, 2);
    // Create Passwordfield
    PasswordField passBox = new PasswordField();
    // Add Password field to grid 1,2
    grid.add(passBox, 1, 2);

    // Create Login Button
    Button btn = new Button("Submit");
    // Add button to grid 1,5
    grid.add(btn, 1, 7);

    final Text actiontarget = new Text();
    grid.add(actiontarget, 1, 8);

    // Set the Action when button is clicked
    btn.setOnAction(new EventHandler<ActionEvent>() {

    @Override

        public void handle(ActionEvent e) {
            // Authenticate the user
            boolean isValid = authenticate(userTextField.getText(), passBox.getText());
            String username = userTextField.getText();
            accessLog(username, isValid);
            //if valid clear the grid and Welcome the user

        if (isValid) {
            grid.setVisible(false);
            GridPane grid2 = new GridPane();
            grid2.setAlignment(Pos.CENTER);
            grid2.setHgap(10);
            grid2.setVgap(10);
            Text scenetitle = new Text("Login Successful");
            grid2.add(scenetitle, 0, 0, 2, 1);
            Scene scene3 = new Scene(grid2, 450, 350);
            primaryStage.setScene(scene3);
            primaryStage.show();
        // If Invalid Ask user to try again
        } else {
        //loginAttempts counter

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 9);
        actiontarget.setFill(Color.BLUE);
        actiontarget.setText("Login Incorrect.");
    }
    }
});

    } catch (Exception ex) {
    ex.printStackTrace();
}
    // Set the size of Scene
    Scene scene = new Scene(grid, 400, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
}


        public static void main(String[] args) {
        launch(args);
    }
           

            //Enter Password
            public boolean authenticate(String user, String pword) {
                    boolean isValid = false;
                if (user.equalsIgnoreCase("Admin")&& pword.equals("password")) {
                    isValid = true;
            }
                    return isValid;
    }

                public static void accessLog(String username, boolean isValid) {
                String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                // declaring variables of log and initializing the buffered writer
                String log = "Username: " + username + " Attempted login timestamp: " 
                        + timestamp + " Login: " + isValid + "\n";
                BufferedWriter writer = null;

                //write to the log.txt file using Buffered Writer
                try {
                     writer = new BufferedWriter(new FileWriter("Log.txt",true)); // here true means append data
                     writer.write(log);
                     writer.write("\r\n"); //Add new line after a log entry
                } //print error message if there is one
                catch (IOException io) {
                System.out.println("File IO Exception" + io.getMessage());
                } //close the file
                finally {
                try {
                if (writer != null) {
                writer.close();
                }
                } //print error message if there is one
                catch (IOException io) {
                System.out.println("Issue closing the file." + io.getMessage());
}
}
}
}