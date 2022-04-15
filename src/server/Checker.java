package server;

import java.text.MessageFormat;

public class Checker {

    private static final char[][] ANSWERS = {
            {'V', 'F', 'F', 'V'},
            {'F', 'V', 'F', 'V'},
            {'V', 'F', 'F', 'V'},
            {'V', 'V', 'V', 'V'},
            {'F', 'F', 'F', 'V'},
            {'V', 'F', 'V', 'V'},
            {'F', 'V', 'V', 'V'},
            {'F', 'F', 'F', 'V'}
    };

    public static String check(String[] test){
        String studentName = test[0];
        int wrongs=0, rights=0;
        for (int i = 1; i < ANSWERS.length+1; i++)
            for (int j = 2, k = 0; j <= 8; j = j + 2, k++)
                if (test[i].charAt(j) == ANSWERS[i - 1][k]) {
                    rights++;
                } else wrongs++;

        return  rights + " rights and " + wrongs + " wrongs" + " by " + studentName;
    }

}
