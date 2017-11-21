import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements IMainFrame,Observer {

    JMenuBar menuBar;
    JMenu fileMenu, editMenu;
    JMenuItem saveMenuItem, exitMenuItem, refreshMenuItem;
    JCheckBoxMenuItem autoRefreshCheckbox;
    JTabbedPane tabbedPane;
    JPanel defaultTab,  buttonPanel, panel;
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






        createPortfolioButton = new JButton("Create Portfolio");
        openPortfolioButton = new JButton("Open Portfolio");

        createPortfolioButton.addActionListener(new ButtonListener("create"));
        openPortfolioButton.addActionListener(new ButtonListener("open"));

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
        updater.addFolio(folioTab.getName());
        return true;
    }

    public boolean removeFolioTab() {
        int folioTabIndex = tabbedPane.indexOfComponent(tabbedPane.getSelectedComponent());
        if (folioTabIndex >= 0) tabbedPane.removeTabAt(folioTabIndex);

        if (tabbedPane.getTabCount() == 0) tabbedPane.addTab("Empty", defaultTab);

        return true;
    }

    @Override
    public boolean openFolio() {
        return false;
    }

    @Override
    public boolean closeFolio() {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        updater.getFolios();
    }


    public void refresh() {
        updater.updateGUI();
    }



}

