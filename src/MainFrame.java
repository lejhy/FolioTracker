import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class MainFrame extends JFrame implements IMainFrame,Observer {

    private JTabbedPane tabbedPane;
    private JPanel defaultTab;
    public JButton createFolioButton, deleteFolioButton, openFolioButton;


    private IUpdater updater;
    private boolean autoRefresh;

    public MainFrame() {
        autoRefresh=false;

        updater = new Updater();
        updater.addObserver(this);

        setTitle("Folio Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(e -> refresh());
        JCheckBoxMenuItem autoRefreshCheckbox = new JCheckBoxMenuItem("Auto Refresh");
        autoRefreshCheckbox.addActionListener(e-> autoRefresh=!autoRefresh);

        editMenu.add(refreshMenuItem);
        editMenu.add(autoRefreshCheckbox);


        createFolioButton = new JButton("Create Portfolio");
        openFolioButton = new JButton("Open Portfolio");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createFolioButton);
        buttonPanel.add(openFolioButton);
        GridLayout buttonsLay = new GridLayout(2,1);

        buttonPanel.setVisible(true);
        buttonPanel.setLayout(buttonsLay);

        tabbedPane = new JTabbedPane();
        JPanel panel = new JPanel();
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

    public boolean addFolioTab(FolioPanel folioTab) {
        updater.addObserver(folioTab);
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
        updater.addFolio(folioTab.getName());
        return true;
    }

    public boolean removeFolioTab() {
        int folioTabIndex = tabbedPane.indexOfComponent(tabbedPane.getSelectedComponent());
        if (folioTabIndex >= 0) tabbedPane.removeTabAt(folioTabIndex);
        updater.removeFolio(folioTabIndex);

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
    public Vector<Vector<String>> getData(String name) {
        return updater.getData("");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    @Override
    public void refresh() {
        updater.manualUpdateGUI();
    }

    @Override
    public void addCreateFolioListener(ActionListener a) {
        createFolioButton.addActionListener(a);
    }

    @Override
    public void addOpenFolioListener(ActionListener a) {
        openFolioButton.addActionListener(a);
    }

    @Override
    public void addDeleteFolioListener(ActionListener a) {
        deleteFolioButton.addActionListener(a);
    }

    public void modifyFolio(Vector<String> i, String name, int index) {
        updater.folioModified(i,name,index);
    }

}

