import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DaySeven {
    FileReader fr;
    public static int totalSmallDirectoryStorage = 0;
    public static int smallestDeleteableDirectory = 0;
    final public static int MAX_STORAGE = 70000000;
    final public static int REQUIRED_FREE_STORAGE = 30000000;
    final public static int SMALL_DIRECTORY_SIZE = 100000;

    public void challenge(String filePath){
        try {
            File file = new File(filePath);
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            Dir currentDir = new Dir("root", null);
            while((line = br.readLine()) != null){
                String[] command = line.split(" ");

                if(command[0].equals("$") && command[1].equals("cd")){
                    if(command[2].equals("..")){
                        currentDir = currentDir.parentalDir;
                    }else {
                        boolean isExisting = false;
                        for(Dir dir : currentDir.dirList){
                            if (dir.name.equals(command[2])) {
                                currentDir = dir;
                                isExisting = true;
                                break;
                            }
                        }
                        if(!isExisting){
                            currentDir.addDir(command[2], currentDir);
                        }
                    }
                }else if(command[0].equals("dir")){
                    boolean isExisting = false;
                    for(Dir dir : currentDir.dirList){
                        if (dir.name.equals(command[1])) {
                            isExisting = true;
                            break;
                        }
                    }
                    if(!isExisting){
                        currentDir.addDir(command[1], currentDir);
                    }
                } else if(!command[0].equals("$")){
                    currentDir.addFile(command[1], Integer.parseInt(command[0]));
                }
            }
            while(currentDir.parentalDir != null){
                currentDir = currentDir.parentalDir;
            }
            currentDir.findSmallDir();
            System.out.println(totalSmallDirectoryStorage);

            smallestDeleteableDirectory = currentDir.totalSize;
            currentDir.findClosestDirectoryTo(Math.abs(MAX_STORAGE-REQUIRED_FREE_STORAGE- currentDir.totalSize));
            System.out.println(smallestDeleteableDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Dir{
        public Dir parentalDir;
        public String name;
        public ArrayList<Files> fileList = new ArrayList<>();
        public ArrayList<Dir> dirList = new ArrayList<>();
        public int totalSize;
        public Dir(String name, Dir parentalDir){
            this.name = name;
            this.parentalDir = parentalDir;
        }
        public void addFile(String name, Integer size){
            this.fileList.add(new Files(name, size));
            changeTotalSize(size);
        }
        public void addDir(String name, Dir parentalDir){
            this.dirList.add(new Dir(name, parentalDir));
        };
        public void changeTotalSize(int size){
            this.totalSize += size;
            if(parentalDir != null){
                this.parentalDir.changeTotalSize(size);
            }
        }

        public void findSmallDir(){
            if(totalSize <= DaySeven.SMALL_DIRECTORY_SIZE){
                DaySeven.totalSmallDirectoryStorage += this.totalSize;
            }
            for(Dir dir : dirList){
                dir.findSmallDir();
            }
        }

        public void findClosestDirectoryTo(int value){
            if(value - this.totalSize <= 0 && Math.abs(value - this.totalSize) <= Math.abs(value - DaySeven.smallestDeleteableDirectory)){
                DaySeven.smallestDeleteableDirectory = this.totalSize;
            }
            for(Dir dir : dirList){
                dir.findClosestDirectoryTo(value);
            }
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            String currentDirString = "dir: " + this.name + " (" + totalSize + ") ";
            result.append(currentDirString).append("\n");
            for(Files file : fileList){
                result.append(file).append("\n");
            }
            for(Dir dir : dirList){
                result.append(dir).append("\n");
            }
            return result.toString();
        }
    }

    static class Files {
        public int size;
        public String name;
        public Files(String name, Integer size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return name + " (" + size + ")";
        }
    }
}
