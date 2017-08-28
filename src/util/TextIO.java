package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Provides support for text file I/O.
 * @author Mike Liu
 *
 */
public class TextIO {
    
    public static final String INVALID_FILE = "Invalid file";
    
    public void save(File file, String data) throws IOException {
        if(file != null) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
            bw.close();
        }
    }
    
    public void save(File file, Collection<String> data) throws IOException {
        save(file, String.join(Constants.NEWLINE, data));
    }
    
    public String load(File file) throws IOException {
        if(file != null) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(Constants.NEWLINE);
            }
            br.close();
            return sb.toString();
        }
        return "";
    }

}
