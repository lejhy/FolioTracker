import Server.Server;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Updater extends Observable implements IUpdater {
    private List<IFolio> list;

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
    public String[][] updateGUI() {
        return new String[0][];
    }

    private String findStockValue(IStock s) {
        return Server.getLastValue(s.getSymbol());
    }



}
