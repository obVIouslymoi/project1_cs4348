Hello!

The code consists of 3 files: Logger.java, Encryption.java, Driver.java. All 3 are executable on their own. 

Driver:
The Driver presents a menu of options to the user. The program then takes the input and acts accordingly after the input is validated. Depending on the choice, the program will act as below:

    - History: Display all past encryption inputs and outputs
    - Quit: End the program and all its processes
    - Password: Set a passkey through Encryption.java
    - Encrypt/Decrypt: Complete cipher action through Encryption.java (if there is a passkey set)
    
For all actions and results, there is a log entry made to a separate file through Logger.java

As all 3 files are executable with STD IN/OUT, all inter-file communication is set up through the Process and Processbuilder classes.


Logger:
The logger takes input from the user. Depending on the action and message, the log records the activity with a timestamp and re-formats the input.

Encryption:
The encryption takes input from the user. The input is a line where the first word is the action and the second part is the argument. Depending on the command, the program can encrypt, decrypt, and set passwords to an alphabet string. The cipher's logic is integrated. The output will print whether it was a success or not.


 To run the program through the terminal, please use "java Driver.java" when in the ~/src/main directory.

