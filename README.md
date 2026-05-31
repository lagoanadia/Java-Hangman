# Java Hangman

A console-based Hangman game built in Java. Supports random words across three difficulty levels or a custom word entered by a second player.

## 📁 Project Structure

```
src/java/hangman/
└── Hangman.java   → All game logic: word selection, input handling, win/lose detection
nbproject/         → NetBeans project config (build system, compiler settings)
```

## 🚀 How It Works

The game runs in a loop that keeps going as long as the player wants to play again. Each round:

1. The player chooses random word or manual input
2. If random, they pick a difficulty level (1–3)
3. The word is converted to a hidden `_ _ _` representation
4. The player guesses letters one at a time — 5 lives, no more

### Three difficulty levels

Words are stored in three separate `ArrayList<String>` pools. A random index is used to pick from the chosen pool:

```java
int index = (int)(Math.random() * words.size());
word = words.get(index);
```

| Level | Examples |
|---|---|
| 1 | cat, blow, wash |
| 2 | computer, centrifuge, desk |
| 3 | electroencephalographist, otorhinolaryngologist |

### Word represented as two parallel char arrays

Once the word is chosen, it's converted into two `char[]` arrays — both the same length, both indexable by position:

```java
char[] indexedWord       = word.toCharArray(); // the real word
char[] hiddenIndexedWord = word.toCharArray(); // starts as _ _ _ _
```

`hiddenIndexedWord` starts filled with `'_'`. As letters are correctly guessed, the corresponding positions are revealed in place. This is why `matches()` can update the display without returning the array — it modifies it directly since arrays in Java are passed by reference.

### Static utility methods with Javadoc

The logic is split into three clean static methods, each documented with Javadoc:

```java
matches(indexedWord, hiddenIndexedWord, letter)  // reveals letter if found
repeated(usedLetters, letter)                    // checks if letter was already tried
hasBlanks(hiddenIndexedWord)                     // checks if word is still incomplete
```

Keeping them separate makes each responsibility clear and easy to test in isolation.

### Repeated letter detection

Guessed letters are tracked in an `ArrayList<Character>`. Before processing each guess, `repeated()` checks if it's already there using `ArrayList.contains()`. If so, the loop `continue`s without consuming a life — fair play.

### Play again loop

The outer `while(playAgain(answer))` loop wraps the entire game. `playAgain()` just checks whether the answer is `2` (exit):

```java
public static boolean playAgain(int answer) {
    return answer != 2;
}
```

All per-round state (lives, usedLetters, the hidden word) is re-initialized inside the loop, so each game starts clean.

## 🧠 Key Concepts

- **Parallel char arrays** — using two arrays of the same length to represent the word and its hidden state simultaneously
- **Pass by reference** — `char[]` passed to `matches()` is modified in place; no return value needed
- **`ArrayList<Character>`** — dynamic list with `.contains()` for O(n) duplicate detection
- **Static utility methods** — logic separated from `main()` and documented with Javadoc
- **`Math.random()` + integer cast** — `(int)(Math.random() * list.size())` for random index selection
- **`Scanner` input loop** — `sc.nextLine()` after `sc.nextInt()` to consume the leftover newline

## 🛠️ How To Run

Built with **NetBeans** using **Java 25**. Open the project in NetBeans and run, or build from the command line:

```bash
ant run
```
