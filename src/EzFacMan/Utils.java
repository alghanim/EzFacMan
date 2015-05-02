package EzFacMan;

import java.io.File;

/**
 * Class gets the path depending on the extension
 *
 * @author Ali
 */
public class Utils {

    public final static String pdf = "pdf";
    public final static String csv = "csv";
 
    /**
     * 
     * @param f contains the file chosen
     * @return the file extension
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
