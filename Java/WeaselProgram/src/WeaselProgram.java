import java.util.Random;

public class WeaselProgram {
    static final String PHRASE = "METHINKS IT IS LIKE A WEASEL";
    static final int SIZE = 28;
    static final int PROBABILITY = 5;
    static final String[] CHARACTERS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " " };
    static Random random = new Random();

    public static void main(String[] args) {
        String initialStr = "", randomStr;
        String[] reproducedCopy = new String[SIZE];
        String[] bestFitCopy = new String[SIZE];

        int greatestScore = -1, generation = 1;

        for (int i = 0; i < SIZE; i++) {
            initialStr += CHARACTERS[random.nextInt(27)];
        }
        
        randomStr = initialStr;
        
        while (greatestScore < 28) {
            greatestScore = 0;
            
            for (int i = 0; i < 100; i++) {
                reproducedCopy = convertStrToArray(randomStr);

                for (int j = 0; j < SIZE; j++) {
                    reproducedCopy[j] = checkRate(reproducedCopy[j]);
                }

                System.out.println("--------------------" + i + "------------------------");
                System.out.println("|" + randomStr + "| <- Most Close the final string");
                System.out.println("|" + convertArrayToStr(reproducedCopy) + " -> score=" + getScore(reproducedCopy) + "| <- String mutation");

                if (getScore(reproducedCopy) > greatestScore) {
                    greatestScore = getScore(reproducedCopy);
                    bestFitCopy = reproducedCopy;
                }

                if (greatestScore == SIZE) {
                    break;
                }
            }
            randomStr = convertArrayToStr(bestFitCopy);
            System.out.println("Generation " + generation + " best candidate: " + randomStr + " (" + greatestScore + ")");
            generation++;
        }
    }

    static int getScore(String[] reproducedCopy) {
        int score = 0;
        for (int i = 0; i < SIZE; i++) {
            if (reproducedCopy[i].charAt(0) == PHRASE.charAt(i)) {
                score++;
            }
        }
        return score;
    }

    static String checkRate(String pos) {
        if (random.nextInt(100) < PROBABILITY) {
            return CHARACTERS[random.nextInt(CHARACTERS.length)];
        }
        return pos;
    }

    static String convertArrayToStr(String[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += array[i];
        }
        return str;
    }

    static String[] convertStrToArray(String str) {
        String[] array = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = "" + str.charAt(i);
        }
        return array;
    }
}
