import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo {
    public void rockPaperScissors(String filePath) {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int score = 0;
            int scoreWithSpecialRules = 0;
            while((line = br.readLine()) != null){

                String[] chosings = line.split(" ");
                int elf = chosings[0].charAt(0) - 64;
                int me = chosings[1].charAt(0) - 87;

                score += rules(elf, me);
                scoreWithSpecialRules += rulesSecond(elf, me);
            }
            System.out.println(score);
            System.out.println(scoreWithSpecialRules);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int rulesSecond(int elve, int me) {
        switch (me){
            case 1:
                // lose
                switch (elve){
                    case 1:
                        return 3;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                }
            case 2:
                // draw
                return elve + 3;
            case 3:
                // win
                switch (elve){
                    case 1:
                        return 2+ 6;
                    case 2:
                        return 3+ 6;
                    case 3:
                        return 1+ 6;
                }
        }
        throw new RuntimeException();
    }

    private int rules(int elve, int me) {
        if(elve == me) {
            return 3 + me;
        } else{
            switch(elve){
                case 1:
                    switch(me){
                        case 2:
                            return 6 + me;
                        case 3:
                            return me;
                    }
                    break;
                case 2:
                    switch(me){
                        case 1:
                            return me;
                        case 3:
                            return me+6;
                    }
                    break;
                case 3:
                    switch(me){
                        case 1:
                            return me + 6;
                        case 2:
                            return me;
                    }
                    break;
            }
        }
        throw new RuntimeException();
    }
}
