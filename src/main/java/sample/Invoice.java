package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Invoice implements Serializable {
    ArrayList<Product> products;
    String client;
    int number;

    Invoice(int number,String client){
        this.products=new ArrayList<Product>();
        this.number=number;
        this.client=client;
    }
}
