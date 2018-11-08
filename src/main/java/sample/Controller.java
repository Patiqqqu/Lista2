package sample;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;


public class Controller {
    Invoice invoice = new Invoice(0, "default");
    Database database;

    public TextField clientnames;
    public TextField invoicenames;
    public TextField clientnamed;
    public TextField price;
    public TextField invoicenamed;
    public TextField amount;
    public TextField productname;
    public TextField databasename;
    @FXML
    TextArea viewtextarea;

    public void displayinvoice(ActionEvent event) throws Exception {
        database.set(invoice);
        FileOutputStream fileOutputStream = new FileOutputStream(databasename.getText());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(database);
        objectOutputStream.close();
        fileOutputStream.close();
        invoice = new Invoice(0, "default");
    }

    public void viewinvoice(ActionEvent event) throws Exception {
        if (databasename.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wystąpił błąd!!");
            alert.setContentText("Nie podano bazy danych.\nSpróbuj jeszcze raz.");
            alert.showAndWait();
        } else {
            FileInputStream fileInputStream = new FileInputStream(databasename.getText());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            database = (Database) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            Invoice newinvoice = database.get(Integer.parseInt(invoicenames.getText()), clientnames.getText());
            StringBuilder viewstring = new StringBuilder();
            for (int i = 0; i < newinvoice.products.size(); i++) {
                viewstring.append("Nazwa artykułu: ").append(newinvoice.products.get(i).name).append("\n");
                viewstring.append("Cena: ").append(newinvoice.products.get(i).price).append("\n");
                viewstring.append("Ilość: ").append(newinvoice.products.get(i).amount).append("\n");
            }
            viewtextarea.setText(String.valueOf(viewstring));
        }
    }

    public void addinvoice(ActionEvent event) throws IOException, ClassNotFoundException {
        if (invoice.number == 0 && invoice.client.equals("default")) {
            FileInputStream fileInputStream = new FileInputStream(databasename.getText());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            database = (Database) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            invoice = new Invoice(Integer.parseInt(invoicenamed.getText()), clientnamed.getText());
        }
        Product nproduct = new Product(productname.getText(), Integer.parseInt(amount.getText()), Float.parseFloat(price.getText()));
        invoice.products.add(nproduct);
    }

    public void createnewdatabase(ActionEvent event) {
        try {
            if (!databasename.getText().isEmpty()) {
                database = new Database(databasename.getText());
                FileOutputStream fileOutputStream = new FileOutputStream(new File(database.name));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(database);
                objectOutputStream.close();
                fileOutputStream.close();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                warning.setTitle("Błąd");
                warning.setHeaderText("Wystąpił błąd!!");
                warning.setContentText("Nie udało się utworzyć nowej bazy danych.\nSpróbuj jeszcze raz.");
                warning.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
