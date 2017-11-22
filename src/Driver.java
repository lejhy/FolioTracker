public class Driver {

    public static void main(String[] args) {
        IMainFrame mainFrame = new MainFrame();

        mainFrame.addCreateFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.CREATE_FOLIO, mainFrame));
        mainFrame.addOpenFolioListener(new MainFrameButtonListener(MainFrameButtonListener.type.OPEN_FOLIO, mainFrame));
    }
}
