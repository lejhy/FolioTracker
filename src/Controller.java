import java.util.Vector;

public class Controller {

    private IMainFrame mainFrame;
    private StrathQuoteServer server;

    public Controller(IMainFrame frame) {
        this.mainFrame = frame;
        server = new StrathQuoteServer();

        mainFrame.addCreateFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.CREATE_FOLIO, mainFrame));

        mainFrame.addOpenFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.OPEN_FOLIO, mainFrame));
    }

    public void deleteFolio() { mainFrame.removeFolioTab(); }

    public void closeFolio() { mainFrame.closeFolio(); }

    public void openFolio() { mainFrame.openFolio(); }
}


