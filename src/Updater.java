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



    @Override
    public void addFolio(String name) {

    }

    @Override
    public void removeFolio(int name) {

    }

    @Override
    public void manualUpdateGUI() {

    }

    /*
    Note: using this just now just to create test data
     */
    @Override
    public Vector<Vector<String>> getData(String name) {


        String[][] defaultData = {
                {"Kathy", "Smith",
                        "1", "5", "1000"},
                {"John", "Doe",
                        "3", "3", "6"},
                {"Sue", "Black",
                        "5", "2", "200000"},
                {"Jane", "White",
                        "100", "3", "246341"},
                {"Joe", "Brown",
                        "59", "10", "233333"}
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
