package main.java;

import java.util.ArrayList;
import java.util.List;

public class Folio {

    String name;
    List<Stock> stocks;

    public Folio(String name) {
        this.name = name;
        stocks = new ArrayList<>();
    }

}
