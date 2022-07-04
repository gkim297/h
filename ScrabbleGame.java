import java.util.Scanner;


public class ScrabbleGame {
    private static final int[] LETTER_POINTS={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private boolean isDoubleLetter = false;
    private boolean isTripleLetter = false;
    private boolean isDoubleWord = false;
    private boolean isTripleWord = false;

    private char theDoubleLetter; //will be checked only if isDoubleLetter is true
    private char theTripleLetter;

    public ScrabbleGame(boolean isDoubleLetter, boolean isTripleLetter,boolean isDoubleWord, boolean isTripleWord){
        this.isDoubleLetter = isDoubleLetter;
        this.isTripleLetter = isTripleLetter;
        this.isDoubleWord = isDoubleWord;
        if(!this.isDoubleWord){//if isDoubleWord is not true
            this.isTripleWord = isTripleWord;
        }
}

    public int getScrabblePointValue(char ch){
        int counter = 0;
        int value = 0;
        for(char c = 'a'; c <='z';c++){
            if(c==ch){ //if the character is found
                value = LETTER_POINTS[counter]; //get the scrabble value of the letter from the index counter
                break;//if found stop checking further
            }
            counter++;
        }
        return value;
    }

    public int calculateScore(String word){
        String wordInLowerCase = word.toLowerCase();
        char[] wordCharArr = wordInLowerCase.toCharArray();
        int score = 0;
        int doubleLetterCount = 0;
        int tripleLettercount = 0;
        for(char ch: wordCharArr){//for each character in the given word
            int value = getScrabblePointValue(ch); //get the scrabble value of the character
//check if there is a double letter and current character lies in the double letter
            if(this.isDoubleLetter && this.theDoubleLetter == ch && doubleLetterCount==0){
                doubleLetterCount++;
                value = value * 2; //double the letter value
            } //check if there is a triple letter and current character lies in the triple letter
            else if(this.isTripleLetter && this.theTripleLetter ==ch && tripleLettercount==0){
                tripleLettercount++;
                value = value * 3;//triple the letter value
            }
            score = score + value;//add value to score
        }

//if length of word >=7 add 50 to score
        if(wordInLowerCase.length() >= 7){
            score = score + 50;
        }
//if double word score will be doubled else if trple word score is tripled
        if(this.isDoubleWord){
            score =score*2;
        }else if(this.isTripleWord){
            score = score * 3;
        }

        return score;
    }

    public static void main(String[] args){

//Test Case: Taking entry from user

        ScrabbleGame sg = new ScrabbleGame(false,false,false,false);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word:");
        String word = sc.nextLine();
        System.out.println("Is there a DOUBLE LETTER SCORE?[y/n]");
        String input = sc.nextLine();
        sg.isDoubleLetter = input.equalsIgnoreCase("y")?true:false;
        if(sg.isDoubleLetter){
            System.out.println("Which letter?");
            input = sc.nextLine();
            if(input.length() ==1){//to check if it is a character
                sg.theDoubleLetter = input.charAt(0);
            }
        }
        System.out.println("Is there a TRIPLE LETTER SCORE?[y/n]");
        input = sc.nextLine();
        sg.isTripleLetter = input.equalsIgnoreCase("y")?true:false;

        if(sg.isTripleLetter){
            System.out.println("Which letter?");
            input = sc.nextLine();
            if(input.length() ==1){
                sg.theTripleLetter = input.charAt(0);
            }
        }

        System.out.println("Is there a DOUBLE WORD SCORE?[y/n]");
        input = sc.nextLine();
        sg.isDoubleWord = input.equalsIgnoreCase("y")?true:false;
        if(!sg.isDoubleWord){ //triple word will be checked only if double word is false
            System.out.println("Is there a TRIPLE WORD SCORE?[y/n]");
            input = sc.nextLine();
            sg.isTripleWord = input.equalsIgnoreCase("y")?true:false;
        }

        sc.close();
        int score = sg.calculateScore(word);
        System.out.println("The score for the word is: "+ score);
