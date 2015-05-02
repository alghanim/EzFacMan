package EzFacMan;


import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 * customize the files shown to be only CSV
 * @author Ali
 */
public class CSVCustomFilter extends FileFilter {
        @Override
        public boolean accept(File file) {
            
            if(file.isDirectory()) {
                return true;
            }
            
            String extension = Utils.getExtension(file);
            if(extension != null) {
                if(extension.equals(Utils.csv)) {
                    return true;
                } else {
                    return false;
                }
            }
            
            return false;
        }
        @Override
        public String getDescription() {
            
            return "csv";
        }
    }
