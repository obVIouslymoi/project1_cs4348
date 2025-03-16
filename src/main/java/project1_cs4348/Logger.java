package project1_cs4348;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; //imports and packages

public class Logger {                                                                  

    public static void handleInput(String fileName) { //essentially the entire logger program that can be called and takes in the name of file
        FileWriter fileWriter;
        PrintWriter printWriter;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM");
        //intialize all file writer and print writer to create/write to file that is accessible by entire method + date format to timestap the logs

        try { //try-catch to make sure the program only runs if files are created --> allows for less errors and easy handling
            fileWriter = new FileWriter(fileName); //create new file with given name
            printWriter = new PrintWriter(fileWriter); //writes to newly created file
            Scanner scanIn = new Scanner(System.in); //create new scanner to get user input                             
        
            while (scanIn.hasNextLine()) { //loop while there are user inputs                                       
                String userLineInput = scanIn.nextLine(); //get user input                                       
                if (userLineInput.equalsIgnoreCase("QUIT")){  //check if command is quit
                    scanIn.close();
                    fileWriter.close(); 
                    printWriter.close();                                                                
                    System.exit(0);  
                    //gracefully close all outputs and exit                                                    
                }
                else if (!userLineInput.contains(" ")){ //check if there are no arguments provided                                     
                    System.out.println("ERROR No arguments given"); //throw error message if only command                          
                } 
                String[] wordsArr = userLineInput.split(" ", 2); //split command and message                      
                String action =  wordsArr[0].toUpperCase(); //first word is action and turn to all caps
                String message =  wordsArr[1]; //second part is message
                String time = LocalDateTime.now().format(dateFormat); //get time and format as listed above
                printWriter.println(time + " [" + action + "] " + message); //write into file in desired format
            }

        } 
        catch (IOException e) {
            System.out.println("File not created."); //throw error to state that file/program did not work and exit
            return;
        }                          
    } 

}

