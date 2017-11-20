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

    private String findStockValue(IStock s) {
        try {
            return StrathQuoteServer.getLastValue(s.getSymbol());
        } catch (WebsiteDataException e) {
            e.printStackTrace();
        } catch (NoSuchTickerException e) {
            e.printStackTrace();
        }
        return "Oops";
    }



}
