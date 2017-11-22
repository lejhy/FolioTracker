import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame implements IMainFrame,Observer {

    private JTabbedPane tabbedPane;
    private JPanel defaultTab;
    public JButton createFolioButton, deleteFolioButton, openFolioButton;


    private boolean autoRefresh;

    public MainFrame() {
        autoRefresh=false;

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
        JCheckBoxMenuItem autoRefreshCheckbox = new JCheckBoxMenuItem("Auto Refresh");
        autoRefreshCheckbox.addActionListener(e-> autoRefresh=!autoRefresh);

        editMenu.add(refreshMenuItem);
        editMenu.add(autoRefreshCheckbox);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));

        tabbedPane = new JTabbedPane();
        defaultTab = new JPanel();
        defaultTab.setSize(1000,500);
        tabbedPane.add(defaultTab);

        wrapperPanel.add(tabbedPane);

        createFolioButton = new JButton("Create Portfolio");
        openFolioButton = new JButton("Open Portfolio");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createFolioButton);
        buttonPanel.add(openFolioButton);

        wrapperPanel.add(buttonPanel);

        add(wrapperPanel);

        tabbedPane.addTab("Empty", defaultTab);
        defaultTab.setPreferredSize(new Dimension(800, 600));
        setSize(1000, 600);
        setVisible(true);
    }



    public boolean addFolioTab(Component folioTab) {
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
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

    }

    @Override
    public void addCreateFolioListener(ActionListener a) {
        createFolioButton.addActionListener(a);
    }

    @Override
    public void addOpenFolioListener(ActionListener a) {
        openFolioButton.addActionListener(a);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}

