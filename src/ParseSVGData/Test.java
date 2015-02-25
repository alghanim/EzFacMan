package ParseSVGData;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test extends JFrame {
    static public ArrayList<Room> rList;
    
    public Test(ArrayList<Room> rList) {
        this.rList = rList;
        initUI();
    }

    public final void initUI() {

        TestPanel dpnl = new TestPanel(rList);
        add(dpnl);

        setSize(1000, 650);
        setTitle("Test");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


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
