package project1_cs4348;

public class Encryption {

   /* public static void completeInput(String userInput, String key) { //method to executively handle encryption program --> this will be the only program that needs to be called by main/driver
            if (userInput.isEmpty() || !(userInput.matches("^[a-zA-Z\\s]*$"))) { //make sure valid inputs
                System.out.println("ERROR Non-alaphabet characters detected"); //throw error if invalid
                return;
            } else {
                core(userInput, key); //call encryption logic function to handle individual line (if valid)
            }
        }
    

    public static void core (String userLineInput, String key) {    //handles line by line encryption
        userLineInput = userLineInput.toUpperCase();    //turns to uppercase
        if (userLineInput.equalsIgnoreCase("QUIT")){    //check if quit (first to make QUIT is not ignored by following clause)
            System.exit(0);//exit program
            return;   
        }
        else if (!userLineInput.contains(" ")){ //check if only command was given
            System.out.println("ERROR No arguments given"); //print error
            return;
        } 
        String[] wordsArr = userLineInput.split(" ", 2); //split into command and argument at first space
        switch (wordsArr[0]) { //match command
            case "PASSKEY": 
                key = wordsArr[1]; //set argument as key
                key = key.replaceAll("\\s", ""); //remove spaces from key
                System.out.println("RESULT"); //print success statement
                break;
            case "ENCRYPT":
                if (key == null) { //check if there are no keys set
                    System.out.println("ERROR Password not set"); //print error message
                    break;
                }
                System.out.println("RESULT " + applyCipher(wordsArr[1], key, true)); //else, print success with encrypted result which is called via method to encrypt (true)
                break;
            case "DECRYPT":
                if (key == null) { //check if there are no keys set
                    System.out.println("ERROR Password not set"); //print error message
                    return;
                }
                System.out.println("RESULT " + applyCipher(wordsArr[1], key, false)); //else, print success with encrypted result which is called via method to decrpyt (false)
                break;
            default:
                System.out.println("ERROR " + wordsArr[1]); //incorrect command/nonmatching --> print error with argument
        }
    } */
/////////////////////////////////////////////////////////////////////////////////////////////////
    public static String applyCipher(String input, String key,  boolean cipherDirection) { //method to encrypt/decrypt --> true = encrypt | false = decrypt
        String outcome = ""; //initialize final word to be printed
        char inEach; //initialize char variable to track each letter of words
        input = input.replaceAll("[^A-Z]", ""); //remove spaces
        for (int i = 0, j = 0; i != input.length(); i++) { //start applying cipher
            inEach = input.charAt(i); //track each letter
            if (cipherDirection) { //encrypt
                outcome += (char)((inEach + key.charAt(j) - 2 * 'A') % 26 + 'A'); //calculate encrypted letter and append to end of final word
            }
            else {//decrypt
                outcome += (char)((inEach - key.charAt(j) + 26) % 26 + 'A'); //calculate decrypted letter and append to end of final word
            }
            j = ++j % key.length(); //move to next letter for chipher
        }
        return outcome; //return final en/decrypted word
    }


    public static void encrypt(String input, String key) { //
        if (key == null) { //check if there are no keys set
            System.out.println("ERROR Password not set"); //print error message
            return;
        }
        System.out.println("RESULT " + applyCipher(input, key, true)); //else, print success with encrypted result which is called via method to encrypt (true)
    }

    public static void decrypt(String input, String key) { //
        if (key == null) { //check if there are no keys set
            System.out.println("ERROR Password not set"); //print error message
            return;
        }
        System.out.println("RESULT " + applyCipher(input, key, false)); //else, print success with encrypted result which is called via method to encrypt (true)
    }

    public static void quit() { //
        System.exit(0); //
    }



}