package model;

import java.util.ArrayList;

public class InsertData {
    private String[] arr;
    private ArrayList<Integer> key;
    private String tableName;

    public InsertData(String[] arr, ArrayList<Integer> key, String tableName) {
        this.arr = arr;
        this.key = key;
        this.tableName = tableName;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public String toString() {
        String s = "INSERT INTO " + tableName + " VALUES (";
        for (int i = 0; i < arr.length; i++) {
            if (key.get(i) == Controller.NUMBER){
                s += arr[i] + "";
            } else if (key.get(i) >= Controller.STRING){
                s += "'" + arr[i] + "'";
            } else if (key.get(i) == Controller.DATE){
                s += "TO_DATE('" + arr[i] + "', 'dd/mm/yyyy')";
            }
            if (i != arr.length - 1){
                s += ",";
            }
        }
        s += ");";
        return s;
    }
}
