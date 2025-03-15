package project1_cs4348;

public class Encryption {                                                               //create class
    static String key = null;                                                           //create key variable
    public static void completeInput(String userInput) {                                //function to deal with valid input line
        if (userInput.equalsIgnoreCase("QUIT")){                          //if the only 1 word is QUIT, quit program.
            System.exit(0);                                                      //exit program
            return;   
        }
        else if (!userInput.contains(" ")){                                           //if there is only 1 word in the entry
            System.out.println("ERROR No arguments given");                           //end and state invalid input as only command was given
            return;   
        } 
        String[] wordsArr = userInput.split(" ", 2);                        //split command ([0]) and argument ([1])
        switch (wordsArr[0]) {                                                          //switch statement to match and deal with command
            case "PASSKEY":                                                             //sets passkey with argument
                key = wordsArr[1];                                                      //set passkey
                key = key.replaceAll("\\s", "");                      //remove spaces from passkey
                System.out.println("RESULT");                                         //print sucess statement
                break;
            case "ENCRYPT":                                                             //encrypts
                if (key == null) {                                                      //checks if key exists
                    System.out.println("ERROR Password not set");                     //error message for no key
                    break;
                }
                System.out.println("RESULT " + applyCipher(wordsArr[1], true));         //success --> print message and new word
                break;
            case "DECRYPT":                                                             //decrypts
                if (key == null) {                                                      //checks if key exists
                    System.out.println("ERROR Password not set");                     //error message for no key
                    return;
                }
                System.out.println("RESULT " + applyCipher(wordsArr[1], false));        //success --> print message and decrypted
                break;
            default:                                                                    //wrong command
                System.out.println("ERROR " + wordsArr[1]);                             //print error and remaining line
        }
    }

    public static String applyCipher(String input, boolean cipherDirection) {           //applies cipher to input word while direction determines (en = true/de = false)crypt
        String outcome = "";                                                            //creates result string
        char inEach;                                                                    //creates char variable to travel by each letter
        input = input.replaceAll("[^A-Z]", "");                       //remove spaces
        for (int i = 0, j = 0; i != input.length(); i++) {                              //loop until each letter of argument is decrypted
            inEach = input.charAt(i);                                                   //get current input letter
            if (cipherDirection) {                                                      //if true = encrypt
                outcome += (char)((inEach + key.charAt(j) - 2 * 'A') % 26 + 'A');       //encrypt letter by calculating and applying Caesar shift --> append encrypted letter to outcome
            }
            else {                                                                      //if false = decrypt
                outcome += (char)((inEach - key.charAt(j) + 26) % 26 + 'A');            //decrypt letter by calculating and reverse applying Caesar shift --> append decrypted letter to outcome
            }
            j = ++j % key.length();                                                     //move to next for key
        }
        return outcome;                                                                 //return final outcome
    }
}

