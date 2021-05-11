package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Controller;
import model.InsertData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    Controller cm = new Controller();

    @FXML
    private TextField tfNo;

    @FXML
    private TextArea taInserts;

    @FXML
    void doInserts() {
        try {
            taInserts.clear();

            int cant = Integer.parseInt(tfNo.getText());

            makeAll(cant);
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("missing place number");
            a.setContentText("you have to put a number in the box or you put a non-integer number");
            a.showAndWait();
        }
    }

    private ArrayList<InsertData> makeWorksOn(int cant, ArrayList<InsertData> r, ArrayList<InsertData> r3) {
        cm.cleanKeyWType();
        cm.addKeyWType(Controller.NUMBER, -1);
        cm.addKeyWType(Controller.NUMBER, -1);
        cm.addKeyWType(Controller.DATE, -1);
        cm.addKeyWType(Controller.NUMBER, Controller.I_RANDNUM);
        cm.setNoAtt(4);

        ArrayList<InsertData> r4 = new ArrayList<>();

        try {
            r4 = cm.makeInserts(cant, "worksOn");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < r4.size(); i++) {
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
        cm.addKeyWType(Controller.NUMBER, Controller.I_ID);
        cm.addKeyWType(Controller.STRING, Controller.S_PROJECT);
        cm.addKeyWType(Controller.NUMBER, -1);
        cm.setNoAtt(3);

        ArrayList<InsertData> r3 = new ArrayList<>();

        try {
            r3 = cm.makeInserts(cant, "proyect");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < r3.size(); i++) {
            String[] arr1 = r3.get(i).getArr();
            String[] arr = r2.get(i).getArr();

            arr1[2] = arr[0];

            r3.get(i).setArr(arr1);
        }

        return r3;
    }

    private ArrayList<InsertData> makeDepartment(int cant, ArrayList<InsertData> r) {
        cm.cleanKeyWType();
        cm.addKeyWType(Controller.NUMBER, Controller.NOT);
        cm.addKeyWType(Controller.STRING, Controller.S_DEPT);
        cm.addKeyWType(Controller.NUMBER, Controller.I_ID);
        cm.setNoAtt(3);

        ArrayList<InsertData> r2 = new ArrayList<>();

        try {
            r2 = cm.makeInserts(cant, "department");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < r2.size(); i++) {
            String[] arr1 = r2.get(i).getArr();
            String[] arr = r.get(i).getArr();

            arr1[0] = arr[7];

            r2.get(i).setArr(arr1);
        }

        return r2;
    }

    private ArrayList<InsertData> makeEmployee(int cant) {
        cm.cleanKeyWType();
        cm.addKeyWType(Controller.NUMBER, Controller.I_ID);
        cm.addKeyWType(Controller.STRING, Controller.S_NAME);
        cm.addKeyWType(Controller.STRING, Controller.S_LNAME);
        cm.addKeyWType(Controller.STRING, Controller.S_ADDRESS);
        cm.addKeyWType(Controller.DATE, -1);
        cm.addKeyWType(Controller.STRING, Controller.S_SEX);
        cm.addKeyWType(Controller.STRING, Controller.S_JOB);
        cm.addKeyWType(Controller.NUMBER, Controller.I_ID);
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
        ArrayList<InsertData> r;
        ArrayList<InsertData> r2;
        ArrayList<InsertData> r3;
        ArrayList<InsertData> r4;

        r = makeEmployee(cant);
        r2 = makeDepartment(cant, r);
        r3 = makeProject(cant, r2);
        r4 = makeWorksOn(cant, r, r3);

        show(r);
        show(r2);
        show(r3);
        show(r4);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void show(ArrayList<InsertData> r) {
        File f = new File("src/data/outDat.txt");
        try {
            f.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            StringBuilder s = new StringBuilder();
            if (!taInserts.getText().equals("")) {
                s = new StringBuilder(taInserts.getText());
            }
            for (InsertData dat : r) {
                s.append(dat).append("\n");
            }

            taInserts.setText(s.toString());
            bw.write(s.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ControllerMenu() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
