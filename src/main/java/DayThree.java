import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DayThree {
    public void backpacks(String filePath) {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int groupSum = 0;
            int itemSum = 0;
            ArrayList<String> group = new ArrayList<>();
            while((line = br.readLine()) != null){
                group.add(line);
                if(group.size() == 3){
                    groupSum += findCommon(group);
                    group.clear();
                }
                int mid = line.length()/2;
                String[] parts = {line.substring(0, mid), line.substring(mid)};

                for (char character : parts[0].toCharArray()){
                    if(parts[1].indexOf(character) != -1){
                        if(Character.isLowerCase(character)){
                            itemSum += (character - 96);
                            break;
                        } else if(Character.isUpperCase(character)){
                            itemSum += (character - 38);
                            break;
                        }
                    }
                }
            }
            System.out.println(itemSum);
            System.out.println("groupSum:" + groupSum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int findCommon(ArrayList<String> group) {
        for (char character : group.get(0).toCharArray()){
            if(group.get(1).indexOf(character) != -1 && group.get(2).indexOf(character) != -1){
                if(Character.isLowerCase(character)){
                    return character - 96;
                } else if(Character.isUpperCase(character)){
                    return character - 38;
                }
            }
        }
        throw new RuntimeException();
    }
}
