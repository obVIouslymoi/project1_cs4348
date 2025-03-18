package project1_cs4348;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.util.Scanner; //imports and packages

public class Logger {
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM"); //intialize date format to timestap the logs
    static String time = LocalDateTime.now().format(dateFormat); //get time and make it accessible for all methods

    public static void main(String[] args) {
        Scanner scanIn = new Scanner(System.in);
        while (scanIn.hasNextLine()) { //loop while there are user inputs                                       
            String userLineInput = scanIn.nextLine().trim(); //get user input   
            
            if (!userLineInput.contains(" ")) { //check if 1 word
                switch (userLineInput.toUpperCase().trim()) { //call correct log message based on command
                    case "START":
                        logStart();
                        break;
                        
                    case "QUIT":
                        logQuit();
                        return;
                    
                    case "HISTORY":
                        logHistory();
                        break;
                        
                    default:
                        System.out.println(time + " ERROR Invalid Action");
                        break;
                       
                }
            } else {
                String[] wordsArr = userLineInput.split(" ", 2); //split command and message                      
                String action = wordsArr[0].toUpperCase().trim(); //first word is action and turn to all caps
                String message = wordsArr[1].trim(); //second part is message

                if ((action.equalsIgnoreCase("RESULT")) ||(action.equalsIgnoreCase("ERROR")) ) {
                    System.out.println(time + " [" + action + "] " + message);
                }
                else{
                    System.out.println(time + " [ERROR] Invalid Action");
                }
        }
    }
    }

    public static void logStart() { //log message for starting
        System.out.println(time + " [START] Logging Started");
        return;
    }

    public static void logQuit() {//log message for quitting
        System.out.println(time + " [QUIT] Logging Ended");
        return;
    }

    public static void logHistory() {//log message for notifying that history was checked
        System.out.println(time + " [HISTORY] History Checked.");
        return;
    }
    
}