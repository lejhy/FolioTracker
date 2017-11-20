import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements IMainFrame,Observer {

    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem createMenuItem, openMenuItem, saveMenuItem, exitMenuItem, refreshMenuItem;
    JCheckBoxMenuItem autoRefreshCheckbox;
    JTabbedPane tabbedPane;
    JPanel defaultTab,  buttonPanel, panel;
    JButton createPortfolioButton;
    JButton openPortfolioButton;
    IUpdater updater;

    public MainFrame() {

        updater = new Updater();
        updater.addObserver(this);

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






        createPortfolioButton = new JButton("Create Portfolio");
        openPortfolioButton = new JButton("Open Portfolio");

        buttonPanel= new JPanel();
        buttonPanel.add(createPortfolioButton);
        buttonPanel.add(openPortfolioButton);
        GridLayout buttonsLay = new GridLayout(2,1);

        buttonPanel.setVisible(true);
        buttonPanel.setLayout(buttonsLay);

        tabbedPane = new JTabbedPane();
        panel = new JPanel();
//        add(tabbedPane);
        defaultTab = new JPanel();
        defaultTab.setSize(1000,500);
        tabbedPane.add(defaultTab);
        defaultTab.setVisible(true);
        tabbedPane.setVisible(true);
        panel.add(tabbedPane);
        panel.setVisible(true);
        panel.add(buttonPanel);
        add(panel);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        tabbedPane.addTab("Empty", defaultTab);
        defaultTab.setPreferredSize(new Dimension(800, 600));
        setSize(1000, 600);
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

    @Override
    public void update(Observable o, Object arg) {
        updater.getFolios();
    }
}
