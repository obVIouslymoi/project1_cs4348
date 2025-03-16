/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package project1_cs4348;
import java.util.Scanner; //imports and packages

/**
 * @author oviya
 */
public class Project1_cs4348 {
    public static void main(String[] args) { //main method
        Scanner scanIn = new Scanner(System.in); //create scanner to get input from console                                       
        String fileName = scanIn.nextLine(); //get file name from input
        //Encryption.completeInput(); //Call encrption program
        Logger.handleInput(fileName); //Call logger program
                                       
        scanIn.close(); //close scanner when complete                                                               
    }
}