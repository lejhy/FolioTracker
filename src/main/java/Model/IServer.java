package Model;

public interface IServer {

    public String getLastValue(String ticker) throws WebsiteDataException, NoSuchTickerException;

}
