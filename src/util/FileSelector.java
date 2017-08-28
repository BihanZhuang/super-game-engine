package util;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * Provides support for opening files for reading and writing.
 * Allow user to specify the file extension and its description
 * @author Mike Liu
 *
 */
public class FileSelector {
    
    public static final String IMAGE_EXTENSION_PATH = Constants.RESOURCE_PACKAGE + "image_extensions";
    public static final String DATA_EXTENSION_PATH = Constants.RESOURCE_PACKAGE + "data_extensions";
    
    private FileChooser myChooser;
    
    public FileSelector(String type, String extension) {
        this();
        addExtension(type, extension);
    }
    
    public FileSelector(List<String> type, List<String> extension) {
        this();
        if(type.isEmpty() || type.size() != extension.size()) {
            throw new IllegalArgumentException("Number of file type and extension do not match");
        }
        for(int i = 0; i < type.size(); i++) {
            addExtension(type.get(i), extension.get(i));
        }
    }
    
    public FileSelector(String resourcePath) {
        this();
        new ResourceParser(resourcePath).parseAll((extension, type) -> addExtension(type, extension));
    }
    
    private FileSelector() {
        myChooser = new FileChooser();
        setInitialDirectory(new File(System.getProperty("user.dir")));
    }
    
    public void setInitialDirectory(File value) {
        myChooser.setInitialDirectory(value);
    }
    
    public void addExtension(String type, String extension) {
        myChooser.getExtensionFilters().add(new ExtensionFilter(type, extension.split("\\|")));
    }
    
    public Optional<File> showOpenDialog(Window owner) {
        myChooser.setTitle("Choose file");
        return Optional.ofNullable(myChooser.showOpenDialog(owner));
    }
    
    public Optional<File> showSaveDialog(Window owner) {
        myChooser.setTitle("Save to");
        return Optional.ofNullable(myChooser.showSaveDialog(owner));
    }

}
