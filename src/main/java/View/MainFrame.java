package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements IMainFrame {

    private JTabbedPane tabbedPane;
    private JPanel defaultTab;
    private JMenuItem createMenuItem;
    private JMenuItem openMenuItem;


    public MainFrame() {

        setTitle("Model.Folio Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        createMenuItem = new JMenuItem("Create");
        openMenuItem = new JMenuItem("Open");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
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



    public void addFolioTab(Component folioTab) {
        if (tabbedPane.getComponentAt(0) == defaultTab) tabbedPane.removeTabAt(0);
        tabbedPane.addTab(folioTab.getName(), folioTab);
    }

    public void removeFolioTab() {
        int folioTabIndex = tabbedPane.indexOfComponent(tabbedPane.getSelectedComponent());
        if (folioTabIndex >= 0) {
            tabbedPane.removeTabAt(folioTabIndex);
        }

        if (tabbedPane.getTabCount() == 0) tabbedPane.addTab("Empty", defaultTab);

    }

    @Override
    public void addCreateListener(ActionListener a) {
        createMenuItem.addActionListener(a);
    }

    @Override
    public void addOpenListener(ActionListener a) {
        openMenuItem.addActionListener(a);
    }

}

