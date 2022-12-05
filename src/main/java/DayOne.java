import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DayOne {
    FileReader fr;

    public void caloriesFinder(String filePath){
        try {
            File file = new File(filePath);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int index = 0;
            List<Integer> gnomeList = new ArrayList<>();
            gnomeList.add(0);
            while((line = br.readLine()) != null){
                try{
                    int lineValue = Integer.parseInt(line);
                    System.out.println(lineValue);
                    gnomeList.set(index, gnomeList.get(index)+lineValue);
                }catch (NumberFormatException e){
                    gnomeList.add(0);
                    index++;
                }
            }
            System.out.println("max Calories per Elve: " + Collections.max(gnomeList));
            Collections.sort(gnomeList);
            System.out.println("Calories of top 3 Elves: " + (gnomeList.get(gnomeList.size()-1)+gnomeList.get(gnomeList.size()-2)+gnomeList.get(gnomeList.size()-3)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
