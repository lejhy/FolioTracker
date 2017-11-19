package main.java;

public class Driver {

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();

        mainFrame.addFolioTab(new FolioPanel("Folio 1"));
        mainFrame.addFolioTab(new FolioPanel("Folio 2"));
        mainFrame.addFolioTab(new FolioPanel("Folio 3"));

        new MainFrame();
    }
}
