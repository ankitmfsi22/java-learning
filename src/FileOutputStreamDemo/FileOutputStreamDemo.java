package FileOutputStreamDemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStreamDemo fo = new FileOutputStreamDemo();
        fo.writeToFile(new File("output.txt"));
    }

    private void writeToFile(File file) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write("Hello world, once again".getBytes());
        fileOutputStream.close();
    }
}
