package sample;

public interface Databaseinterface {
    Invoice get(int number,String client) throws Exception;
    void set(Invoice invoice);
}
