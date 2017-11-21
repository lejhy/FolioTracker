public class Driver {

    public static void main(String[] args) {
        IMainFrame mainFrame = new MainFrame();
        Controller control = new Controller(mainFrame);

        IFolio folio1 = new Folio("Folio 1");
        IFolio folio2 = new Folio("Folio 2");
        IFolio folio3 = new Folio("Folio 3");
        mainFrame.addFolioTab(new FolioPanel(folio1));
        mainFrame.addFolioTab(new FolioPanel(folio2));
        mainFrame.addFolioTab(new FolioPanel(folio3));

    }
}
