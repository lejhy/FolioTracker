import Controller.MainFrameButtonListener;
import View.IMainFrame;
import View.MainFrame;

public class Driver {

    public static void main(String[] args) {
        IMainFrame mainFrame = new MainFrame();

        mainFrame.addCreateListener(new MainFrameButtonListener(MainFrameButtonListener.Type.CREATE_FOLIO, mainFrame));
        mainFrame.addOpenListener(new MainFrameButtonListener(MainFrameButtonListener.Type.OPEN_FOLIO, mainFrame));
    }
}
