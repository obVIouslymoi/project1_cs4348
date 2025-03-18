/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package project1_cs4348;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner; //imports and packages

/**
 * @author oviya
 */
public class Driver {

    static ArrayList<String> history = new ArrayList<String>(); //array list that stores history
    static Scanner scanIn = new Scanner(System.in); //scanner

    public static void printHistory() { //prints all entries in history (used for listing past entries and when history command is selelcted)
        for (int i = 0; i != history.size(); i++) {
            System.out.println("[" + i + "] " + history.get(i));
        }
    }

    public static int offerHistory() { //asks user if they want history and returns index of desired past entry; returns -1 if they don't want to or change their mind
        System.out.println("Would you like to use the history? (Y/N)");
        if ((scanIn.nextLine().trim().toUpperCase().equals("Y"))) {
            printHistory();
            System.out.println("[-1] Return to enter new word)");
            System.out.println("Select word by entering its corresponding number: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanIn.nextLine());
                if (choice == -1) {
                    return choice;
                }
                while (!((choice > 0) && (choice <= (history.size()-1)))) {
                    choice = Integer.parseInt(scanIn.nextLine());
                    
                }
            } catch (Exception e) {
                System.out.println("ERROR Invalid type");
            }
            return choice;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Process logProcess = null;
        PrintWriter logWriter = null;
        Process encryptProcess = null;
        BufferedReader encryptReader = null;
        PrintWriter encryptWriter = null;

        try {
            //create logger process
            ProcessBuilder logBuilder = new ProcessBuilder("java", "Logger.java");
            logBuilder.redirectOutput(new File("logOutFile.txt")); // redirects logger output to the file
            logBuilder.redirectErrorStream(true);//errors to console
            logProcess = logBuilder.start();//starts logger process
            OutputStream logInput = logProcess.getOutputStream();//send output for logger
            logWriter = new PrintWriter(logInput, true); //writes to logger

            //create encryt process
            ProcessBuilder encryptBuilder = new ProcessBuilder("java", "Encryption.java");
                encryptBuilder.redirectErrorStream(true);//errors to console
                encryptProcess = encryptBuilder.start();//starts encryption process
                InputStream encryptInput = encryptProcess.getInputStream();  //read encryption output
                OutputStream encryptOutput = encryptProcess.getOutputStream();  //send input to encryption
                encryptReader = new BufferedReader(new InputStreamReader(encryptInput)); //gives output of encrypt
                encryptWriter = new PrintWriter(encryptOutput, true); // writes to encrypt
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        logWriter.println("START"); //start logging
        //..waitFor(); //attempts to debug missing file issue

        //////////////////////////////////////////////////////////////////////////////////////////CORE STARTS
        /// necessary varaibles
        String input;
        int historyChoice;
        while (true) { //loops until quit
            String sendCommand = ""; //variable that holds command for encrypt and resets for each menu choice
            System.out.println("Select command (password, encrypt, decrypt, history, quit): "); //print menu
            input = scanIn.nextLine().trim().toLowerCase(); //get user input
            if(input.contains("\n")){
                input.replaceAll("\n", "");
            }

            switch (input) { //match command to process
                case "encrypt":
                    sendCommand = sendCommand.concat(input); //add command to send to encrypt
                    sendCommand = sendCommand.concat(" "); // add space for arguments

                    historyChoice = offerHistory(); //ask if user wants to pick from history
                    if (historyChoice == -1) { //if not 
                        System.out.print("Enter argument to encrypt: "); //ask for new word
                        input = scanIn.nextLine().trim().toUpperCase(); //get user input
                        if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                            sendCommand = sendCommand.concat(input); //add and complete command
                            history.add(input); //add new word to history
                        } else {
                            System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                            break;
                        }
                    } else {
                        sendCommand = sendCommand.concat(history.get(historyChoice)); //valid history choice --> complete command
                    }
                    encryptWriter.println(sendCommand); //send command to encryption process to be completed by process
                    //encryptProcess.waitFor(); //wait for completion
                    logWriter.println(sendCommand); //send command to be logged
                    //logProcess.waitFor(); //wait for completion
                    String storeEncryptOutTemp = encryptReader.readLine();
                    System.out.println(storeEncryptOutTemp);//print result to console
                    logWriter.println(storeEncryptOutTemp); //print to logger
                    //logProcess.waitFor(); //wait for completion
                    history.add(storeEncryptOutTemp.split(" ", 2)[1]); //add encrypted to history;

                    break;

                case "decrypt":

                    sendCommand = sendCommand.concat(input); //add command to send to encrypt
                    sendCommand = sendCommand.concat(" "); // add space for arguments

                    historyChoice = offerHistory(); //ask if user wants to pick from history                    
                    if (historyChoice == -1) { //if not 
                        System.out.print("Enter argument to decrypt: "); //ask for new word
                        input = scanIn.nextLine().trim().toUpperCase(); //get user input
                        if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                            sendCommand = sendCommand.concat(input); //add and complete command
                            history.add(input); //add new word to history
                        } else {
                            System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                            break;
                        }
                    } else {
                         //System.out.println(sendCommand);

                        sendCommand = sendCommand.concat(history.get(historyChoice)); //valid history choice --> complete command                        
                    }
                    encryptWriter.println(sendCommand); //send command to encryption process to be completed by process
                    //encryptProcess.waitFor(); //wait for completion
                    logWriter.println(sendCommand); //send command to be logged
                    //logProcess.waitFor(); //wait for completion
                    //logProcess.waitFor(); //wait for completion
                    storeEncryptOutTemp = encryptReader.readLine();
                    System.out.println(storeEncryptOutTemp);//print result to console
                    logWriter.println(storeEncryptOutTemp); //print to logger
                    history.add(storeEncryptOutTemp.split(" ", 2)[1]); //add encrypted to history;

                    break;

                case "password":
                    sendCommand = sendCommand.concat("PASSKEY"); //add command to send to change passkey
                    sendCommand = sendCommand.concat(" "); // add space for arguments

                    System.out.print("Enter new passkey: "); //ask for new word
                    input = scanIn.nextLine();
                    if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                        input = input.trim().toUpperCase();
                        sendCommand = sendCommand.concat(input); //complete command to send to change passkey

                    } else {
                        System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                        break;
                    }

                    encryptWriter.println(sendCommand); //send command to encryption process to be completed by process
                    logWriter.println(sendCommand); //send command to be logged
                    storeEncryptOutTemp = encryptReader.readLine();
                    System.out.println(storeEncryptOutTemp);//print result to console
                    logWriter.println(storeEncryptOutTemp); //print to logger
                    break;

                case "history":
                    printHistory();
                    sendCommand = sendCommand.concat(input); //add command to send to check history
                    logWriter.println(sendCommand); //send command to be logged
                    //logProcess.waitFor(); //wait for completion
                    break;

                case "quit":
                    sendCommand =  sendCommand.concat(input); //add command to send to quit
                    logWriter.println(sendCommand); //send command to be logged
                    //logProcess.waitFor(); //wait for completion
                    encryptWriter.println(sendCommand); //send command to be logged
                    //encryptProcess.waitFor();//wait for completion
                    encryptReader.close(); //close streams
                    scanIn.close(); //close streams
                    System.exit(0); //exit program

                default:
                    System.out.println("Invalid command. Retry"); //invalid entry for command but while loop continues
            }

        }
    }
}
