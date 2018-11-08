package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Databaseinterface,Serializable{
    ArrayList<Invoice> data;
    String name;

    Database(String name){
        this.name=name;
        this.data= new ArrayList<Invoice>();
    }

    public Invoice get(int number,String client) throws Exception{
        int i=0;
        while(i<data.size()){
            if(data.get(i).number==number && data.get(i).client.equals(client)){
                return data.get(i);
            }
            i++;
        }
        throw new Exception("Nie znaleziono podanej faktury.");
    }

    public void set(Invoice invoice) {
        data.add(invoice);
    }

}
