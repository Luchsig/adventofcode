import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class DayFive {
    public void challenge(String filePath) {
        ArrayList<Character> firstSlot = new ArrayList<>(Arrays.asList('D','M','S','Z','R','F','W','N'));
        ArrayList<Character> secondSlot = new ArrayList<>(Arrays.asList('W','P','Q','G','S'));
        ArrayList<Character> thirdSlot = new ArrayList<>(Arrays.asList('W','R','V','Q','F','N','J', 'C'));
        ArrayList<Character> fourthSlot = new ArrayList<>(Arrays.asList('F','Z','P','C','G','D','L'));
        ArrayList<Character> fifthSlot = new ArrayList<>(Arrays.asList('T','P','S'));
        ArrayList<Character> sixthSlot = new ArrayList<>(Arrays.asList('H','D','F','W','R','L'));
        ArrayList<Character> seventhSlot = new ArrayList<>(Arrays.asList('Z','N','D','C'));
        ArrayList<Character> eightSlot = new ArrayList<>(Arrays.asList('W','N','R','F','V','S','J','Q'));
        ArrayList<Character> ninthSlot = new ArrayList<>(Arrays.asList('R','M','S','G','Z','W','V'));
        ArrayList<ArrayList<Character>> slots = new ArrayList<>();
        slots.add(firstSlot);
        slots.add(secondSlot);
        slots.add(thirdSlot);
        slots.add(fourthSlot);
        slots.add(fifthSlot);
        slots.add(sixthSlot);
        slots.add(seventhSlot);
        slots.add(eightSlot);
        slots.add(ninthSlot);

        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            for(int i = 0; i<= 9; i++){
                br.readLine();
            }
            while((line = br.readLine()) != null) {
                String[] command = line.split(" ");
                int listLength = slots.get(Integer.parseInt(command[3])-1).size();
                List<Character> containers = slots.get(Integer.parseInt(command[3])-1).subList(listLength-(Integer.parseInt(command[1])), (listLength));
                Collections.reverse(containers);
                slots.get(Integer.parseInt(command[5])-1).addAll(containers);

                for (int i = listLength; i>listLength-Integer.parseInt(command[1]); i--){
                    slots.get(Integer.parseInt(command[3])-1).remove(i-1);
                }
            }
            for (ArrayList<Character> slot : slots) {
                System.out.print(slot.get(slot.size()-1));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
