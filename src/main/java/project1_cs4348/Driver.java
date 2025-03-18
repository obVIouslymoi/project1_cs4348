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

    static ArrayList<String> history = new ArrayList<String>();
    static Scanner scanIn = new Scanner(System.in);

    public static void printHistory() {
        for (int i = 0; i != history.size(); i++) {
            System.out.println("[" + i + "] " + history.get(i));
        }
    }

    public static int offerHistory() {
        System.out.println("Would you like to use the history? (Y/N)");
        if ((scanIn.nextLine().trim().toUpperCase().equals("Y"))) {
            printHistory();
            System.out.println("[-1] Return to Main Menu)");
            System.out.println("Select word by entering its corresponding number: ");
            while (true) {
                try {
                    return scanIn.nextInt();
                } catch (Exception e) {
                    System.out.println("Incorrect selection format. Please enter number only.");
                }
            }
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println(System.getProperty("java.class.path"));
        Process logProcess = null;
        PrintWriter logWriter = null;
        Process encryptProcess = null;
        BufferedReader encryptReader = null;
        PrintWriter encryptWriter = null;

        try {
            // Start Logger process and redirect output to .txt
            
                ProcessBuilder logBuilder = new ProcessBuilder("java", "Logger.java");
            logBuilder.redirectOutput(new File("logOutFile.txt")); // redirects logger output to the file
            logBuilder.redirectErrorStream(true);
            logProcess = logBuilder.start();
            OutputStream logInput = logProcess.getOutputStream();
            logWriter = new PrintWriter(logInput, true); //writes to logger

            // Encrypt
            ProcessBuilder encryptBuilder = new ProcessBuilder("java", "Encryption.java");
                encryptBuilder.redirectErrorStream(true);
                encryptProcess = encryptBuilder.start();
                InputStream encryptInput = encryptProcess.getInputStream();  //read encryption output
                OutputStream encryptOutput = encryptProcess.getOutputStream();  //send input to encryption
                encryptReader = new BufferedReader(new InputStreamReader(encryptInput)); //gives output of encrypt
                encryptWriter = new PrintWriter(encryptOutput, true); // writes to encrypt
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        logWriter.println("START");
        logProcess.waitFor();

        //////////////////////////////////////////////////////////////////////////////////////////CORE STARTS
            /// necessary varaibles
           String input;
        int historyChoice;
        while (true) { //loops until quit
            String sendCommand = ""; //variable that holds command for encrypt and resets for each menu choice
            System.out.println("Select command (password, encrypt, decrypt, history, quit): "); //print menu
            input = scanIn.nextLine().trim().toLowerCase(); //get user input

            switch (input) { //match command to process
                case "encrypt":
                    sendCommand.concat(input); //add command to send to encrypt
                    sendCommand.concat(" "); // add space for arguments

                    historyChoice = offerHistory(); //ask if user wants to pick from history
                    if (historyChoice == -1) { //if not 
                        System.out.print("Enter argument to encrypt: "); //ask for new word
                        input = scanIn.nextLine().trim().toUpperCase(); //get user input
                        if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                            sendCommand.concat(input); //add and complete command
                            history.add(input); //add new word to history
                        } else {
                            System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                            break;
                        }
                    } else {
                        sendCommand.concat(history.get(historyChoice)); //valid history choice --> complete command
                    }
                    encryptWriter.println(sendCommand); //send command to encryption process to be completed by process
                    encryptProcess.waitFor();
                    logWriter.println(sendCommand);
                    logProcess.waitFor();
                    break;

                case "decrypt":

                    sendCommand.concat(input); //add command to send to encrypt
                    sendCommand.concat(" "); // add space for arguments

                    historyChoice = offerHistory(); //ask if user wants to pick from history
                    if (historyChoice == -1) { //if not 
                        System.out.print("Enter argument to decrypt: "); //ask for new word
                        input = scanIn.nextLine().trim().toUpperCase(); //get user input
                        if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                            sendCommand.concat(input); //add and complete command
                            history.add(input); //add new word to history
                        } else {
                            System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                            break;
                        }
                    } else {
                        sendCommand.concat(history.get(historyChoice)); //valid history choice --> complete command
                    }
                    encryptWriter.println(sendCommand); //send command to encryption process to be completed by process
                    encryptProcess.waitFor();
                    logWriter.println(sendCommand);
                    logProcess.waitFor();
                    break;

                case "password":
                    sendCommand.concat(input); //add command to send to change passkey
                    sendCommand.concat(" "); // add space for arguments

                    System.out.print("Enter new passkey: "); //ask for new word
                    input = scanIn.nextLine();
                    if ((!input.equals("")) && (input.matches("^[a-zA-Z]*$"))) { //if valid input
                        input = input.trim().toUpperCase();
                        sendCommand.concat(input); //complete command to send to change passkey

                    } else {
                        System.out.print("Invalid argument"); //invalid input --> break and send to main menu
                        break;
                    }

                    
                    break;

                case "history":

                    break;

                case "quit":
                    // encryptWriter.println("quit"); // Tell Encryptor to terminate
                    logWriter.println("quit"); // Tell Logger to terminate
                    scanIn.close();
                    return;

                default:
                    System.out.print("Invalid command. Retry");
            }

            
/* 
        } catch (IOException ex) {
            System.out.println("Error starting processes.");
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            System.out.println("Process execution interrupted.");
        }
             */
        }
    }
}
