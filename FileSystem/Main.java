package FileSystem;

import java.util.Arrays;
import java.util.List;

// implementation of composite design pattern.
interface FileSystem {
    void ls();
}

class Directory implements FileSystem{
    String dirName;
    List<FileSystem> subDirectoriesAndFiles;
    Directory(String dirName, List<FileSystem> ls) {
        this.subDirectoriesAndFiles = ls;
        this.dirName = dirName;
    }
    @Override
    public void ls() {
        System.out.println("Directory: "  + dirName);
        for(FileSystem fs: subDirectoriesAndFiles)
            fs.ls();
    }
}

class File implements FileSystem{ // leaf node
    String fileName;
    File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls() {
        System.out.println("File: " + fileName);
    }
}


public class Main {
    public static void main(String[] args) {
        File f1 = new File("f1");
        File f2 = new File("f2");
        File f3 = new File("f3");
        File f4 = new File("f4");
        Directory d2 = new Directory("D2", Arrays.asList(f1, f2));
        Directory d1 = new Directory("D1", Arrays.asList(d2, f3, f4));
        d1.ls();
    }
}
