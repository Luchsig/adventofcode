import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class DayEight {
    FileReader fr;

    public void treeHouse(String filePath){
        try {
            File file = new File(filePath);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            ArrayList<ArrayList<Integer>> treeGrid= new ArrayList<>();
            while((line = br.readLine()) != null){
                ArrayList<Integer> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add((int)c -48);
                }
                treeGrid.add(row);
            }

            System.out.println(checkGrid(treeGrid));
            System.out.println(searchPerfectSpot(treeGrid));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int checkGrid(ArrayList<ArrayList<Integer>> treeGrid) {
        int treesInSightCounter = 0;

        int height = treeGrid.size();
        int length = treeGrid.get(0).size();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < length; j++){
                if(i == 0 || j == 0 || i == height-1 || j==length-1){
                    treesInSightCounter++;
                }else{
                    ArrayList<Integer> row = treeGrid.get(i);
                    ArrayList<Integer> col = new ArrayList<>();
                    for(ArrayList<Integer> a : treeGrid){
                        col.add(a.get(j));
                    }
                    int currentTree = treeGrid.get(i).get(j);

                    if (Collections.max(row.subList(0, j)) < currentTree
                            || Collections.max(col.subList(0, i)) < currentTree
                            || Collections.max(row.subList(j+1, length)) < currentTree
                            || Collections.max(col.subList(i+1, height)) < currentTree){
                        treesInSightCounter++;
                    }
                }
            }
        }
        return treesInSightCounter;
    }

    private int searchPerfectSpot(ArrayList<ArrayList<Integer>> treeGrid){
        int height = treeGrid.size();
        int length = treeGrid.get(0).size();
        int highScore = 0;

        for(int i = 0; i< height; i++){
            for(int j = 0; j< length; j++){
                Integer currentTree = treeGrid.get(i).get(j);
                int xscoreH = 0;
                int xscoreL = 0;
                int yscoreH = 0;
                int yscoreL = 0;

                for(int x = j+1; x < length; x++){
                    xscoreH++;
                    if(treeGrid.get(i).get(x) >= currentTree){
                        break;
                    }
                }
                for(int x = j-1; x >= 0; x--){
                    xscoreL++;
                    if(treeGrid.get(i).get(x) >= currentTree){
                        break;
                    }
                }
                for(int y = i+1; y < height; y++){
                    yscoreH++;
                    if(treeGrid.get(y).get(j) >= currentTree){
                        break;
                    }
                }
                for(int y = i-1; y >= 0; y--){
                    yscoreL++;
                    if(treeGrid.get(y).get(j) >= currentTree){
                        break;
                    }
                }
                int currentScore = xscoreH * xscoreL * yscoreH * yscoreL;
                if(currentScore > highScore){
                    highScore = currentScore;
                }
            }
        }
        return highScore;
    }
}
