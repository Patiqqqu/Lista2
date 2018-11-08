package sample;

import java.io.Serializable;

public class Product implements Serializable{
    String name;
    int amount;
    float price;

    Product(String name,int amount,float price){
        this.name=name;
        this.amount=amount;
        this.price=price;
    }
}
