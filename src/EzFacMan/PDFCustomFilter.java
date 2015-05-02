package EzFacMan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Customize the files shown to be only pdf extension
 * @author Ali
 */
public class PDFCustomFilter extends FileFilter {
        @Override
        public boolean accept(File file) {
            
            if(file.isDirectory()) {
                return true;
            }
            
            String extension = Utils.getExtension(file);
            if(extension != null) {
                if(extension.equals(Utils.pdf)) {
                    return true;
                } else {
                    return false;
                }
            }
            
            return false;
        }
        @Override
        public String getDescription() {
            
            return "pdf";
        }
    }
