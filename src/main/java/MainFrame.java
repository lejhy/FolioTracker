import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements IMainFrame,Observer {

    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem saveMenuItem, exitMenuItem, refreshMenuItem;
    JCheckBoxMenuItem autoRefreshCheckbox;
    JTabbedPane tabbedPane;
    JPanel defaultTab;
    JButton createPortfolioButton;
    JButton openPortfolioButton;


    IUpdater updater;
    private boolean autoRefresh;

    public MainFrame() {
        autoRefresh=false;

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

        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(e -> refresh());
        autoRefreshCheckbox = new JCheckBoxMenuItem("Auto Refresh");
        autoRefreshCheckbox.addActionListener(e-> autoRefresh=!autoRefresh);

        editMenu.add(refreshMenuItem);
        editMenu.add(autoRefreshCheckbox);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        defaultTab = new JPanel();

        createPortfolioButton = new JButton("Create Portfolio");
        openPortfolioButton = new JButton("Open Portfolio");
        
        createPortfolioButton.addActionListener(new ButtonListener("create"));
        openPortfolioButton.addActionListener(new ButtonListener("open"));


        defaultTab.add(createPortfolioButton);
        defaultTab.add(openPortfolioButton);

        tabbedPane.addTab("Empty", defaultTab);

        setSize(800, 600);
        setVisible(true);
    }

    public boolean addFolioTab(JPanel folioTab) {
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
        tabbedPane.repaint();
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


    public void refresh() {

    }

    public void deleteCurrentFolio() {
        tabbedPane.remove(tabbedPane.getSelectedIndex());
        repaint();

    }

}

