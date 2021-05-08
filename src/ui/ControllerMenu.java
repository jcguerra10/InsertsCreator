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
                show(makeEmployee(cant));
                break;
            case "Department":
                show(makeDepartment(cant, null));
                break;
            case "Proyect":

                break;
            case "WorksOn":

                break;
            case "All":
                makeAll(cant);
                break;
            default:
        }

    }

    private ArrayList<InsertData> makeWorksOn(int cant, ArrayList<InsertData> r, ArrayList<InsertData> r3) {
        cm.cleanKeyWType();
        cm.addKeyWType(cm.NUMBER, -1);
        cm.addKeyWType(cm.NUMBER, -1);
        cm.addKeyWType(cm.DATE, -1);
        cm.addKeyWType(cm.NUMBER, Controller.I_RANDNUM);
        cm.setNoAtt(4);

        ArrayList<InsertData> r4 = new ArrayList<>();

        try {
            r4 = cm.makeInserts(cant, "worksOn");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i < r4.size() ; i++) {
            String[] arr4 = r4.get(i).getArr();
            String[] arr = r.get(i).getArr();
            String[] arr3 = r3.get(i).getArr();

            arr4[0] = arr[0];
            arr4[1] = arr3[0];

            r4.get(i).setArr(arr4);
        }
        return r4;
    }

    private ArrayList<InsertData> makeProject(int cant, ArrayList<InsertData> r2) {
        cm.cleanKeyWType();
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.addKeyWType(cm.STRING, cm.S_PROJECT);
        cm.addKeyWType(cm.NUMBER, -1);
        cm.setNoAtt(3);

        ArrayList<InsertData> r3 = new ArrayList<>();

        try {
            r3 = cm.makeInserts(cant, "proyect");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i < r3.size() ; i++) {
            String[] arr1 = r3.get(i).getArr();
            String[] arr = r2.get(i).getArr();

            arr1[2] = arr[0];

            r3.get(i).setArr(arr1);
        }

        return r3;
    }

    private ArrayList<InsertData> makeDepartment(int cant, ArrayList<InsertData> r) {
        cm.cleanKeyWType();
        cm.addKeyWType(cm.NUMBER, cm.NOT);
        cm.addKeyWType(cm.STRING, cm.S_DEPT);
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.setNoAtt(3);

        ArrayList<InsertData> r2 = new ArrayList<>();

        try {
            r2 = cm.makeInserts(cant, "department");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0;i < r2.size(); i++) {
            String[] arr1 = r2.get(i).getArr();
            String[] arr = r.get(i).getArr();

            arr1[0] = arr[7];

            r2.get(i).setArr(arr1);
        }

        return r2;
    }

    private ArrayList<InsertData> makeEmployee(int cant) {
        cm.cleanKeyWType();
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.addKeyWType(cm.STRING, cm.S_NAME);
        cm.addKeyWType(cm.STRING, cm.S_LNAME);
        cm.addKeyWType(cm.STRING, cm.S_ADDRESS);
        cm.addKeyWType(cm.DATE, -1);
        cm.addKeyWType(cm.STRING, cm.S_SEX);
        cm.addKeyWType(cm.STRING, cm.S_JOB);
        cm.addKeyWType(cm.NUMBER, cm.I_ID);
        cm.setNoAtt(8);
        ArrayList<InsertData> r = new ArrayList<>();
        try {
            r = cm.makeInserts(cant, "employee");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }


    public void makeAll(int cant) {
        ArrayList<InsertData> r = new ArrayList<>();
        ArrayList<InsertData> r2 = new ArrayList<>();
        ArrayList<InsertData> r3 = new ArrayList<>();
        ArrayList<InsertData> r4 = new ArrayList<>();

        r = makeEmployee(cant);
        r2 = makeDepartment(cant, r);
        r3 = makeProject(cant, r2);
        r4 = makeWorksOn(cant, r, r3);

        show(r);
        show(r2);
        show(r3);
        show(r4);
    }

    private void show(ArrayList<InsertData> r) {
        String s = "";
        if (!taInserts.getText().equals("")) {
            s = taInserts.getText();
        }
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
