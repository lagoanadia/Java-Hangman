package hangman;

import java.util.*;
import java.util.ArrayList;
/**
 * A simple console Hangman game.
 * The player guesses letters until they complete the word or run out of lives.
 */
public class Hangman {

   
/**
 * Checks whether the entered letter appears in the word to guess.
 * If it appears, the letter is revealed in the hidden word.
 *
 * @param indexedWord the original word as a character array
 * @param hiddenIndexedWord the hidden word with guessed letters revealed
 * @param letter the letter entered by the player
 * @return true if the letter appears in the word, false otherwise
 */
    public static boolean matches(char[] indexedWord,char[] hiddenIndexedWord, char letter)
    {   
        boolean matches = false; 
        
        for(int i = 0; i < indexedWord.length; i++)
        {
            if(indexedWord[i] == letter)
            {   
                hiddenIndexedWord[i] = letter;
                matches = true;
            }
        }
        
        return matches;
    }

/**
 * Checks whether a letter has already been used by the player.
 *
 * @param usedLetters the list of letters already entered
 * @param letter the letter to check
 * @return true if the letter was already used, false otherwise
 */
public static boolean repeated(ArrayList<Character> usedLetters, char letter)
    {
        if(usedLetters.contains(letter))
        {
            return true;
        }
        
        return false;
    }

/**
 * Checks whether the hidden word still contains blank spaces.
 *
 * @param hiddenIndexedWord the hidden word as a character array
 * @return true if there are still unguessed letters, false otherwise
 */
public static boolean hasBlanks(char[] hiddenIndexedWord)          
    {
        for(int i = 0; i < hiddenIndexedWord.length; i++)
        {
            if(hiddenIndexedWord[i] == '_')
            {
                return true;
            }
        }
        
        return false;
    }

/**
 * Checks whether the player wants to play again.
 *
 * @param answer the player's answer, where 2 means exit
 * @return true if the player wants to continue, false if they want to exit
 */
public static boolean playAgain(int answer)
    {
        return answer != 2;
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        int answer = 0;

        //RANDOM WORD REPOSITORY/////////////////////
        ArrayList<String> words = new ArrayList<>();
        words.add("cat");
        words.add("blow");
        words.add("stream");
        words.add("wash");

        ArrayList<String> wordsN2 = new ArrayList<>();
        wordsN2.add("computer");
        wordsN2.add("centrifuge");
        wordsN2.add("desk");
        wordsN2.add("roadrunner");

        ArrayList<String> wordsN3 = new ArrayList<>();
        wordsN3.add("electroencephalographist");
        wordsN3.add("constitutionally");
        wordsN3.add("parallelepiped");
        wordsN3.add("otorhinolaryngologist");

        while(playAgain(answer))
        {
            /////////////////////////////////////////////
            ArrayList<Character> usedLetters = new ArrayList<>();

            //Initial lives = 5
            int lives = 5;

            //Choose between random word or manually entering one
            System.out.println("********************************************");
            System.out.println("           WELCOME TO HANGMAN!             ");
            System.out.println("********************************************");

            int option = 0;

            while(option < 1 || option > 2)
            {
                System.out.println("Press 1 for a random word. || Press 2 to enter your own word");
                option = sc.nextInt();
                sc.nextLine();
            }

            String word = " ";

            if(option == 1)
            {   
                int level = 0;

                while(level < 1 || level > 3)
                {
                    System.out.println("Choose a difficulty level from 1 to 3");
                    level = sc.nextInt();
                    sc.nextLine();
                }
           
                switch(level)
                {
                    case 1:
                        int index = (int)(Math.random() * words.size());
                        word = words.get(index);
                        break;

                    case 2:
                        int index2 = (int)(Math.random() * wordsN2.size());
                        word = wordsN2.get(index2);
                        break;

                    case 3: 
                        int index3 = (int)(Math.random() * wordsN3.size());
                        word = wordsN3.get(index3);
                        break;
                }
            }

            if(option == 2)
            {
                System.out.println("Enter your word:");
                word = sc.nextLine().toLowerCase();
            }

            //Once the word to guess is set, create a copy and index both strings.
            char[] indexedWord = word.toCharArray();
            char[] hiddenIndexedWord = word.toCharArray();

            for(int i = 0; i < word.length(); i++)
            {
                hiddenIndexedWord[i] = '_';
            }

            System.out.println("Lives: " + lives + "     Guess the word!");

            for(int p = 0; p < indexedWord.length; p++)
            {
                System.out.print(hiddenIndexedWord[p] + " ");              
            }

            System.out.println(" ");

            ////////////////////////GAME/////////////////////////
            while(lives > 0 && hasBlanks(hiddenIndexedWord))
            {
                char letter = ' ';

                System.out.println("Enter a letter:");    
                letter = Character.toLowerCase(sc.next().charAt(0));
          
                //////Repeated?////////////////////
                if(repeated(usedLetters, letter)) 
                {   
                    //YES
                    System.out.println("*********************************"); 
                    System.out.println(letter + " was already checked."); 
                    System.out.println("*********************************");
                    continue;
                }
                //NO
                else 
                {
                    usedLetters.add(letter);
                }

                //////Matches?///////////////////////////////////////
                if(matches(indexedWord, hiddenIndexedWord, letter))
                {
                    //YES 
                    System.out.println("Lives: " + lives);
               
                    for(int p = 0; p < indexedWord.length; p++)
                    {
                        System.out.print(hiddenIndexedWord[p] + " ");
                    }

                    System.out.println(" ");
               
                } 
                else 
                {
                    //NO
                    lives--;

                    System.out.println("Lives: " + lives + " " + letter + " does not match.");

                    for(int p = 0; p < indexedWord.length; p++)
                    {
                        System.out.print(hiddenIndexedWord[p] + " ");      
                    }

                    System.out.println(" ");
                }  
            }

            if(lives == 0)
            {
                System.out.println("You lost... the word was: " + word);           
            } 
            else 
            {
                System.out.println("You won!");
            }
       
            System.out.println("Press 1 to play again. || Press 2 to exit.");
            answer = sc.nextInt();

            if(!playAgain(answer))
            {
                System.out.println("Goodbye!");
            }
        }
    }
}
