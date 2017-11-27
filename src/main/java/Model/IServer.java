package Model;

import java.io.Serializable;

public interface IServer extends Serializable {

    public String getLastValue(String ticker) throws WebsiteDataException, NoSuchTickerException;

}
