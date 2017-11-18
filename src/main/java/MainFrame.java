import javax.swing.*;

public class MainFrame extends JFrame implements IView {

    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem createMenuItem, openMenuItem, saveMenuItem, exitMenuItem, refreshMenuItem;
    JCheckBoxMenuItem autoRefreshCheckbox;
    JTabbedPane tabbedPane;
    JPanel defaultTab;
    JButton createPortfolioButton;
    JButton openPortfolioButton;

    public MainFrame() {
        setTitle("Folio Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        createMenuItem = new JMenuItem("Create...");
        openMenuItem = new JMenuItem("Open...");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(createMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        refreshMenuItem = new JMenuItem("Refresh");
        autoRefreshCheckbox = new JCheckBoxMenuItem("Auto Refresh");

        editMenu.add(refreshMenuItem);
        editMenu.add(autoRefreshCheckbox);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        defaultTab = new JPanel();

        createPortfolioButton = new JButton("Create Portfolio");
        openPortfolioButton = new JButton("Open Portfolio");

        defaultTab.add(createPortfolioButton);
        defaultTab.add(openPortfolioButton);

        tabbedPane.addTab("Empty", defaultTab);

        setSize(800, 600);
        setVisible(true);
    }

    public boolean addFolioTab(JPanel folioTab) {
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
        return true;
    }

    public boolean removeFolioTab(JPanel folioTab) {
        int folioTabIndex = tabbedPane.indexOfComponent(folioTab);
        if (folioTabIndex >= 0) tabbedPane.removeTabAt(folioTabIndex);

        if (tabbedPane.getTabCount() == 0) tabbedPane.addTab("Empty", defaultTab);

        return true;
    }

}
