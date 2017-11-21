import Server.Server;

import java.util.*;

public class Updater extends Observable implements IUpdater {

    public Updater() {
        autoUpdateThread();

    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }


    private void autoUpdateThread() {
        Thread a = new Thread();
    }



    private void updateFolios() {

    }

    public List<IFolio> getFolios() {
        return null;
    }

    @Override
    public void addFolio(String name) {

    }

    @Override
    public void removeFolio(int name) {

    }

    @Override
    public void manualUpdateGUI() {

    }

    @Override
    public Vector<Vector<String>> getData() {


        String[][] defaultData = {
                {"Kathy", "Smith",
                        "Snowboarding", "5", "false"},
                {"John", "Doe",
                        "Rowing", "3", "false"},
                {"Sue", "Black",
                        "Knitting", "2", "true"},
                {"Jane", "White",
                        "Speed reading", "3", "true"},
                {"Joe", "Brown",
                        "Pool", "10", "false"}
                 };



        Vector<Vector<String>> data = new Vector<>();
        for(int i=0;i<5;i++) {
            data.add(new Vector<>(Arrays.asList(defaultData[i])));
        }

        return data;
    }

    @Override
    public void folioModified(Vector<String> i, String name, int x) {

    }

    private String findStockValue(IStock s) {
        return Server.getLastValue(s.getSymbol());
    }



}
