import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DaySeven {
    FileReader fr;

    public void challenge(String filePath){
        try {
            File file = new File(filePath);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
