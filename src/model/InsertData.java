package model;

import java.util.ArrayList;

public class InsertData {
    private String[] arr;
    private final ArrayList<Integer> key;
    private final String tableName;

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
        StringBuilder s = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        for (int i = 0; i < arr.length; i++) {
            if (key.get(i) == Controller.NUMBER){
                s.append(arr[i]);
            } else if (key.get(i) >= Controller.STRING){
                s.append("'").append(arr[i]).append("'");
            } else if (key.get(i) == Controller.DATE){
                s.append("TO_DATE('").append(arr[i]).append("', 'dd/mm/yyyy')");
            }
            if (i != arr.length - 1){
                s.append(",");
            }
        }
        s.append(");");
        return s.toString();
    }
}
