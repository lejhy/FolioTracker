import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observer;

public class MainFrame extends JFrame implements IMainFrame {

    private JTabbedPane tabbedPane;
    private JPanel defaultTab;
    public JMenuItem createMenuItem, openMenuItem, exitMenuItem;
    IAutoUpdate auto;


    public MainFrame() {

        auto = new AutoUpdate();
        setTitle("Folio Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        createMenuItem = new JMenuItem("Create");
        openMenuItem = new JMenuItem("Open");
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));

        fileMenu.add(createMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);


        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.PAGE_AXIS));

        tabbedPane = new JTabbedPane();
        defaultTab = new JPanel();
        defaultTab.setSize(1000,500);
        tabbedPane.add(defaultTab);

        wrapperPanel.add(tabbedPane);

        add(wrapperPanel);

        tabbedPane.addTab("Empty", defaultTab);
        defaultTab.setPreferredSize(new Dimension(800, 600));
        setSize(1000, 600);
        setVisible(true);
    }



    public boolean addFolioTab(Component folioTab) {
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
        auto.addObserver((Observer) folioTab);
        return true;
    }

    public boolean removeFolioTab() {
        int folioTabIndex = tabbedPane.indexOfComponent(tabbedPane.getSelectedComponent());
        if (folioTabIndex >= 0) {
            auto.deleteObserver((Observer) tabbedPane.getComponentAt(folioTabIndex));
            tabbedPane.removeTabAt(folioTabIndex);
        }

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
    public void addCreateListener(ActionListener a) {
        createMenuItem.addActionListener(a);
    }

    @Override
    public void addOpenListener(ActionListener a) {
        openMenuItem.addActionListener(a);
    }

    @Override
    public void addExitListener(ActionListener a) {
        exitMenuItem.addActionListener(a);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public void flipAutoOnOff() {
        auto.flipIsRunning();
    }

}

