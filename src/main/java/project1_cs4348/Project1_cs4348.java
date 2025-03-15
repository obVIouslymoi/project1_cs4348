/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package project1_cs4348;
import java.util.Scanner;

/**
 *
 * @author oviya
 */
public class Project1_cs4348 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scanIn = new Scanner(System.in);                                        //create scanner
        while (scanIn.hasNextLine()) {                                                  //loops as long there is an input given
            String userInput = scanIn.nextLine();                                       //take in input
            if (!userInput.isEmpty() && userInput.matches("^[a-zA-Z\\s]*$")) {          //if input is valid and only letter/spaces
                userInput = userInput.toUpperCase();                                    //turn to uppercase
                Encryption.completeInput(userInput);                                               //complete line given
            } else {                                                                    //invalid input
                System.out.println("ERROR Non-alaphabet characters detected");                               //error message
            }
        }
        System.out.println("All inputs completed.");                                    //completed all inputs
        scanIn.close();                                                                 //close scanner
    }
}
