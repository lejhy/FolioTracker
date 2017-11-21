import java.util.Vector;

public class Controller {

    private IMainFrame mainFrame;



    public Controller(IMainFrame frame) {
        this.mainFrame = frame;

        mainFrame.addCreateFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.CREATE_FOLIO, mainFrame));

        mainFrame.addOpenFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.OPEN_FOLIO, mainFrame));
    }

    public void deleteFolio() { mainFrame.removeFolioTab(); }

    public Vector<Vector<String>> refreshVector(String name) {
        return mainFrame.getData(name);
    }

    public void modifyFolio(Vector<String> i, String name, int index) {
        mainFrame.modifyFolio(i,name,index);

    }

    public void closeFolio() { mainFrame.closeFolio(); }

    public void openFolio() { mainFrame.openFolio(); }

    public void refresh() { mainFrame.refresh(); }
}


