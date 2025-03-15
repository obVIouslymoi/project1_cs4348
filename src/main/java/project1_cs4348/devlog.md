PAST ENTRIES FROM PREV. REPO

March 10, 2025 - 12:01 PM

Initial Thoughts
There will be three programs. My understanding of each are listed below:

Logger
	- input filename
	- constantly takes input until QUIT
	- unsure if input commands are limited in options --> graceful exceptions
	- specific write format for log file: YYYY-MM-DD HH:MM [ACTION] MESSAGE

Encryption Program
	- uses Vigenère cipher --> need to understand better
	- takes in 6 possible commands as input
	- graceful handling for ENCRYPT and DECRYPT and ERROR
	- case-insensitive --> convert all inputs to all-caps before use
	- no numbers allowed --> automatic error thrown

Driver Program
	- takes in log file name
	- 2 processes for logger and encryption program
	- 5 command input options
	- uses Process class

-------------------------------------------------------------------
March 14, 2025 - 7:56 PM

a. The previous/initial entry essentially broke down the instructions. 
I wanted to make bullet points for the functions and requirements for each program.
I think it will help me make sure I can meet the requirements of the project.

b. After breaking down the three programs, I think the encryption program will be the hardest, and I will start with that.
I will start with learning more about and understanding the Vigenère cipher. I aim to include what I learned in the next update.

-------------------------------------------------------------------
March 14, 2025 - 10:14 PM

These are my notes and understanding of the Vigenère cipher:

- It only allows for letters
  --> I can throw an error if invalid and re-ask for input until valid
  --> Do this by using a try-catch? 
  --> also need to ignore spaces and symbols!

- It is case-insensitive
  --> After checking for valid input, turn inputs to all-caps 
  --> see if there are any Java string functions to remove spaces/symbols and turn to all-caps 

- Cipher logic
  --> There is a key that applies to the word to be encrypted.
  --> Each letter of the key has a corresponding letter in the word to be encrypted based on position.
  --> The Caesar shift for the new letter in each position is decided by the position of the corresponding letter from 'a'.
     --> essentially, the Caesar shift is applied by N (depending on corresponding letter) for each
  --> spaces are preserved

- Plan to implement
  --> I want to create functions for each of the six commands
  --> By doing so, I should easily be able to call them as needed
  --> I'm thinking about doing this through 2 try-catches and switch()
  --> try-catch 1 will be to get valid command inputs
  --> try-catch 2 will be to get valid arguments
  --> switch() will allow for easy functioning based on command 

- Implementing cipher
  --> encrypt and decrypt functions
  --> encrypt
     --> convert case
     --> create a new word of same length
     --> take each letter of key and given word
     --> calculate distance from 'A' and perform Caesar for new world
     --> track letters by index and for loop

After this commit, I will be attempting to implement this.

-------------------------------------------------------------------
March 15, 2025 - 1:16 AM

I have attempted an encryption program. 
Because of how I attemped and approached the project, I am unable to run the program. 
This is because there are issues my json and base project files that are preventing me from running this properly. 
I will fix this tomorrow/later today.

In terms of the code, I did as much as I could without a running the program. 
I used a while loop instead to keep taking in lines and used string functions to make sure the invalid are weeded out. 
Also, I added an error message and final message to know when it is completed. 
There is a larger program that breaks down each line as command and argument. 
The switch statement worked great here and allowed for an error message as well.

I initially created functions for each task but found it redundant for setting the passkey and quitting.
For encrypt/decrypt, I created a geneneral function that produced an output calculated based on the boolean.
True mean encrypt. False meant decrypt.
This allowed for less code and easier reading.

After writing the code, I went back and refined it to remove extras and clean it up (as seen in the lesser amount of functions being needed for each command option). I ended the session by going back and adding comments to explain the code.

I will run this tomorrow to see what works and if I can optimize this more.

-------------------------------------------------------------------

March 15, 2025 - 1:16 AM

Small correction. I have mislabeled a few commits. 'Attempt 1' should be 'Commit 4 - Attempt 1 at Encryption'. 'Commit 3' should be 'Commit 5'. This will be 'Commit 6 - Commit Labelling Correction.'

-------------------------------------------------------------------
NEW REPO ENTRIES
-------------------------------------------------------------------

March 15, 2025 - 12:16 PM

I realized what my mistake was! My project was missing important code files necessary for a project. This happened because I first created code files for each individual program rather than doing this after creating the project. This would have ensured my code could run. I'm not sure how to add the necesary files, so I will be creating a new repo and copying files over. I have copied the necesary devlog entries before this and labelled it as such. In my commit, I will try to add screenshots of past repo commits to prove my work for this new repo. This is what I will be doing now. Once I can fix this, I'll move forward with editing the encryption program.

-------------------------------------------------------------------