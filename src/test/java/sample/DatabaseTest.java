package sample;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @org.junit.jupiter.api.Test
    void get() throws Exception {
        Invoice invoice=new Invoice(1,"Client");
        Database database=new Database("testdatabase");
        database.data.add(invoice);
        assertEquals(invoice,database.get(1,"Client"));
    }

    @org.junit.jupiter.api.Test
    void set() {
        Invoice invoice=new Invoice(1,"Client");
        Database database=new Database("testdatabase");
        database.set(invoice);
        assertEquals(invoice,database.data.get(0));
    }
}