package sample;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void displayinvoice() throws IOException {
        Database database=new Database("TestDatabase");
        File file =new File("File");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(database);
        objectOutputStream.close();
        fileOutputStream.close();
        assertTrue(file.exists());
    }
}