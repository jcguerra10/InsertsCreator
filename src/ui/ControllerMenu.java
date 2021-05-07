package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Controller;
import model.InsertData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    Controller cm = new Controller();

    @FXML
    private ComboBox<String> cbData;

    private ObservableList<String> olData = FXCollections.observableArrayList("Employee", "Department", "Project", "WorksOn", "All");

    @FXML
    private TextField tfNo;

    @FXML
    private TextArea taInserts;

    @FXML
    void doInserts() {
        taInserts.clear();
        String op = cbData.getValue();
        int cant = Integer.parseInt(tfNo.getText());

        switch (op){
            case "Employee":
                makeEmployee(cant);
                break;
            case "Department":
                break;
            case "Proyect":
                break;
            case "WorksOn":
                break;
            case "All":
                break;
            default:
        }

    }

    private void makeEmployee(int cant) {
        cm.cleanKeyWType();
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.addKeyWType(cm.STRING, cm.S_NAME);
        cm.addKeyWType(cm.STRING, cm.S_LNAME);
        cm.addKeyWType(cm.STRING, cm.S_ADDRESS);
        cm.addKeyWType(cm.DATE, -1);
        cm.addKeyWType(cm.STRING, cm.S_JOB);
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.setNoAtt(7);
        try {
            ArrayList<InsertData> r = cm.makeInserts(cant, "employee");
            show(r);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void show(ArrayList<InsertData> r) {
        String s = "";
        for (InsertData dat:r) {
            s += dat +"\n";
        }
        taInserts.setText(s);
    }

    public ControllerMenu() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbData.setItems(olData);
    }
}
