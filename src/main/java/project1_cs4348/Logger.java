package project1_cs4348;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; //imports and packages

public class Logger {
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM"); //intialize date format to timestap the logs
    static String time = LocalDateTime.now().format(dateFormat); //get time and make it accessible for all methods

    public static void logStart() { //log message for starting
        System.out.println(time + " [START] Logging Started");
        return;
    }

    public static void logQuit() {//log message for quitting
        System.out.println(time + " [QUIT] Logging Ended");
        return;
    }

    public static void logEncryptDecrypt(String inputLine) { //log message for recording all encrypts and decrypts
        String[] wordsArr = inputLine.split(" ", 2); //split command and message                      
        String action = wordsArr[0].toUpperCase(); //first word is action and turn to all caps
        String message = wordsArr[1]; //second part is message
        System.out.println(time + " [" + action + "] " + message + ".");
        return;
    }

    public static void logHistory() {//log message for notifying that history was checked
        System.out.println(time + " [HISTORY] History Checked.");
        return;
    }

    public static void logPassword() {//log message for notifying that passkey was changed or set
        System.out.println(time + " [PASSWORD] Password Set.");
        return;
    }
    
}