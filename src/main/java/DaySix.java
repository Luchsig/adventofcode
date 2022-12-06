import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DaySix {
    FileReader fr;

    public void protocol(String filePath){
        try {
            File file = new File(filePath);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            System.out.println("start of package counter is: " + getStartCharacter(line, 4));
            System.out.println("start of message counter is: " + getStartCharacter(line, 14));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getStartCharacter(String line, int length) {
        for (int i = 0; i<line.length()-length; i++) {
            if(checkForUniqueCharacters(line.substring(i, i+length))){
                return i + length;
            }
        }
        throw new RuntimeException();
    }

    private boolean checkForUniqueCharacters(String str)
    {
        char[] chArray = str.toCharArray();
        Arrays.sort(chArray);

        for (int i = 0; i < chArray.length - 1; i++) {
            if (chArray[i] != chArray[i + 1])
                continue;
            else
                return false;
        }
        return true;
    }
}
