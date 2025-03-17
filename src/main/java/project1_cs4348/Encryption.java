package project1_cs4348;

import java.util.Scanner;

public class Encryption {
    static String key = null;

   public static void main(String[] args) {
       Scanner scanIn = new Scanner(System.in);
        while (scanIn.hasNextLine()) { //loop while there are user inputs
            String line = scanIn.nextLine(); 
            String[] wordsArr = line.split(" ", 2); //split command and message                      
            String command = wordsArr[0].toUpperCase().trim(); //first word is command and turn to all caps
            String argument = wordsArr[1].toUpperCase().trim(); //second part is arguments

            switch (command) { //match function with command
                case "ENCRYPT":
                    encrypt(argument);
                    break;

                case "DECRYPT":
                    decrypt(argument);
                    break;
                
                case "PASSKEY":
                    setKey(argument);
                    break;
            }
            
        }
        scanIn.close();
   }
    public static String applyCipher(String input, boolean cipherDirection) { //method to encrypt/decrypt --> true = encrypt | false = decrypt
        String outcome = ""; //initialize final word to be printed
        char inEach; //initialize char variable to track each letter of words
        input = input.replaceAll("[^A-Z]", ""); //remove spaces
        for (int i = 0, j = 0; i != input.length(); i++) { //start applying cipher
            inEach = input.charAt(i); //track each letter
            if (cipherDirection) { //encrypt
                outcome += (char) ((inEach + key.charAt(j) - 2 * 'A') % 26 + 'A'); //calculate encrypted letter and append to end of final word
            } else {//decrypt
                outcome += (char) ((inEach - key.charAt(j) + 26) % 26 + 'A'); //calculate decrypted letter and append to end of final word
            }
            j = ++j % key.length(); //move to next letter for chipher
        }
        return outcome; //return final en/decrypted word
    }

    public static void encrypt(String input) { //encrypt function
        if (key == null) { //check if there are no keys set
            System.out.println("ERROR Password not set"); //print error message
            return;
        }
        System.out.println("RESULT " + applyCipher(input, true)); //else, print success with encrypted result which is called via method to encrypt (true)
    }

    public static void decrypt(String input) { //decrypt function
        if (key == null) { //check if there are no keys set
            System.out.println("ERROR Password not set"); //print error message
            return;
        }
        System.out.println("RESULT " + applyCipher(input, false)); //else, print success with encrypted result which is called via method to decrypt (false)
    }

    public static void setKey(String xkey) { //decrypt function
        key = xkey.toUpperCase().trim();
        System.out.println("RESULT Password set"); //successful passkey set
    }

}
