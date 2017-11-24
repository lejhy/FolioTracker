import Controller.MainFrameButtonListener;
import View.IMainFrame;
import View.MainFrame;

public class Driver {

    public static void main(String[] args) {
        IMainFrame mainFrame = new MainFrame();

        mainFrame.addCreateListener(new MainFrameButtonListener(MainFrameButtonListener.type.CREATE_FOLIO, mainFrame));
        mainFrame.addOpenListener(new MainFrameButtonListener(MainFrameButtonListener.type.OPEN_FOLIO, mainFrame));
    }
}
