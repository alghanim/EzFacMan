package ParseSVGData;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Class to set up test of SVG parsing
 * 
 * @author Nick Killion
 */
public class Test extends JFrame {
    static public ArrayList<Room> rList;
    
    /**
     * Constructor for Test
     * 
     * @param rList ArrayList of Room objects which will be drawn on test output
     */
    public Test(ArrayList<Room> rList) {
        this.rList = rList;
        initUI();
    }

    /**
     * Initializes Test UI
     */
    public final void initUI() {

        TestPanel dpnl = new TestPanel(rList);
        add(dpnl);

        setSize(1000, 650);
        setTitle("Test");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Start the runnable for testing
     */
    public static void start() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Test test = new Test(rList);
                test.setVisible(true);
            }
        });
    }
}
