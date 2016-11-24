import gui.MainView;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Application extends JFrame
{
    public static int WindowWidth = 1280;
    public static int WindowHeight = 750;

    public Application() {
        setTitle("Magic: the Gathering Simulator");
        setSize(WindowWidth, WindowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        MainView.Init(0, 0, WindowWidth, WindowHeight);
        add(MainView.Get());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                Application application = new Application();
                application.setVisible(true);
            }
        });
    }
}
