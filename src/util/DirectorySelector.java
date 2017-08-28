package util;

import java.io.File;
import java.util.Optional;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class DirectorySelector {

    private DirectoryChooser myChooser;
    
    public DirectorySelector() {
        myChooser = new DirectoryChooser();
        setInitialDirectory(new File(System.getProperty("user.dir")));
        myChooser.setTitle("Choose folder");
    }
    
    public void setInitialDirectory(File value) {
        myChooser.setInitialDirectory(value);
    }
    
    public Optional<File> showDialog(Window owner) {
        return Optional.ofNullable(myChooser.showDialog(owner));
    }
}
