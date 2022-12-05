import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DayFour {
    public void cleaning(String filePath) {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int duplicateCounter = 0;
            int overlapCounter = 0;
            while((line = br.readLine()) != null){
                String[] elvesSections = line.split(",");
                ArrayList<Integer> firstCompleteInterval = getAllValuesInInterval(elvesSections[0].split("-"));
                ArrayList<Integer> secondCompleteInterval = getAllValuesInInterval(elvesSections[1].split("-"));

                if(firstCompleteInterval.get(0) >= secondCompleteInterval.get(0)
                        && firstCompleteInterval.get(firstCompleteInterval.size()-1) <= secondCompleteInterval.get(secondCompleteInterval.size()-1)
                        || firstCompleteInterval.get(0) <= secondCompleteInterval.get(0)
                        && firstCompleteInterval.get(firstCompleteInterval.size()-1) >= secondCompleteInterval.get(secondCompleteInterval.size()-1)){
                    duplicateCounter++;
                }

                if(!Collections.disjoint(firstCompleteInterval, secondCompleteInterval)){
                    overlapCounter++;
                }
            }
            System.out.println("There are " + duplicateCounter + " fully enclosed entries!");
            System.out.println("There are " + overlapCounter + " room overlaps!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Integer> getAllValuesInInterval(String[] intervalBorders) {
        int min = Integer.parseInt(intervalBorders[0]);
        int max = Integer.parseInt(intervalBorders[1]);
        ArrayList<Integer> firstCompleteInterval= new ArrayList<>();
        for(int i = min; i<= max; i++){
            firstCompleteInterval.add(i);
        }
        return firstCompleteInterval;
    }
}
