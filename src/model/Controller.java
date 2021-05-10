package model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Controller {

    private static final String PATH = "src/data/sDat.csv";
    private static final String PATH1 = "src/data/sDat1.csv";

    private static final int ANAME = 0;
    private static final int ALNAME = 1;
    private static final int AADDRESS = 2;
    private static final int AJOB = 3;

    private static final int ADEPT = 0;
    private static final int APROJECT = 1;

    //--Keys--
    public static final int STRING = 900;
    public static final int NUMBER = 801;
    public static final int DATE = 803;
    public static final int NOT = 800;

    //--Types--

    //String
    public static final int S_NAME = 552;
    public static final int S_LNAME = 304;
    public static final int S_JOB = 494;
    public static final int S_ADDRESS = 22;
    public static final int S_SEX = 10;
    public static final int S_DEPT = 13;
    public static final int S_PROJECT = 709;

    //Number
    public static final int I_ID = 700;
    public static final int I_RANDNUM = 415;
    public static final int I_AGE = 759;
    
    private ArrayList<InsertData> inserts;
    private ArrayList<Integer> key;
    private ArrayList<Integer> type;
    private int noAtt;

    public Controller() {
        this.inserts = new ArrayList<>();
        this.key = new ArrayList<>();
        this.type = new ArrayList<>();
    }

    public void cleanKeyWType(){
        key = new ArrayList<>();
        type = new ArrayList<>();
        inserts = new ArrayList<>();
    }

    public void addKeyWType(int k, int t){
        key.add(k);
        type.add(t);
    }

    public void setNoAtt(int noAtt) {
        this.noAtt = noAtt;
    }

    public ArrayList<InsertData> makeInserts(int noOfData, String tableName) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        BufferedReader bis = new BufferedReader(new FileReader(PATH));
        BufferedReader bis1 = new BufferedReader(new FileReader(PATH1));

        for (int i = 0; i < noOfData; i++) {
            String[] arr = new String[noAtt];
            for (int j = 0; j < key.size(); j++) {
                if (key.get(j) == NUMBER) {
                    arr[j] = numberInserts(type.get(j), numbers);
                } else if (key.get(j) == STRING) {
                    arr[j] = stringInserts(type.get(j), bis, bis1);
                } else if (key.get(j) == DATE) {
                    arr[j] = dateInserts();
                } else {
                    arr[j] = "--";
                }
            }
            InsertData newId = new InsertData(arr, key, tableName);
            inserts.add(newId);
        }
        return inserts;
    }

    private String dateInserts() {
        Calendar adate;
        Random rand;
        rand = new Random();

        adate = Calendar.getInstance();
        adate.set (rand.nextInt(10)+1998, rand.nextInt(12)+1, rand.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(adate.getTime());
    }

    private String stringInserts(Integer in, BufferedReader bis, BufferedReader bis1) throws IOException {
        String res = "";
        switch (in){
            case S_ADDRESS:
                res += stringAddress(bis);
                break;
            case S_JOB:
                res += stringJob(bis);
                break;
            case S_LNAME:
                res += stringLName(bis);
                break;
            case S_NAME:
                res += stringName(bis);
                break;
            case S_SEX:
                res += stringSex();
                break;
            case S_DEPT:
                res += stringDept(bis1);
                break;
            case S_PROJECT:
                res += stringProject(bis1);
                break;
            default:
                res += "--";
                break;
        }
        return res;
    }

    private String stringProject(BufferedReader bis1) throws IOException {
        String s = bis1.readLine();
        String[] sp = s.split(",");
        return sp[APROJECT];
    }

    private String stringDept(BufferedReader bis1) throws IOException {
        String s = bis1.readLine();
        String[] sp = s.split(",");
        return sp[ADEPT];
    }

    private String stringSex() {
        String s = "";
        int res = (int) Math.floor(Math.random()*3+1);
        if (res == 1)
            s = "Female";
        else
            s = "Male";
        return s;
    }

    private String stringName(BufferedReader bis) throws IOException {
        String s = bis.readLine();
        String[] sp = s.split(",");
        return sp[ANAME];
    }

    private String stringLName(BufferedReader bis) throws IOException {
        String s = bis.readLine();
        String[] sp = s.split(",");
        return sp[ALNAME];
    }

    private String stringJob(BufferedReader bis) throws IOException {
        String s = bis.readLine();
        String[] sp = s.split(",");
        return sp[AJOB];
    }

    private String stringAddress(BufferedReader bis) throws IOException {
        String s = bis.readLine();
        String[] sp = s.split(",");
        return sp[AADDRESS];
    }

    private String numberInserts(Integer in, List<Integer> numbers) {
        String res = "";
        switch (in){
            case I_AGE:
                    res += numberIAge();
                break;
            case I_RANDNUM:
                    res += numberIRandom();
                break;
            case I_ID:
                    res += numberIId(numbers);
                break;
            default:
                res += "--";
                break;
        }
        return res;
    }

    private String numberIId(List<Integer> numbers) {

        if (numbers.size() == 0) {
            for (int i=1;i<201;i++){
                numbers.add(i);
            }
        }

        Random random = new Random();

        int randomIndex = random.nextInt(numbers.size());

        String res = numbers.get(randomIndex) + "";

        numbers.remove(randomIndex);

        return res;
    }

    private String numberIRandom() {
        String res = Math.floor(Math.random()*12+1) + "";
        return res;
    }

    private String numberIAge() {
        String res = Math.floor(Math.random()*100+1) + "";
        return res;
    }



}
