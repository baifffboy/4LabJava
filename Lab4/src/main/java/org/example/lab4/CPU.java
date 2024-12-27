package org.example.lab4;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;


public class CPU implements iCPU  {
    static int[] Memory = new int[1024];
    static int[] Registrs = new int[4];
    int Columnregistr = -1;
    int rowRAM = -1;
    int ColumnRAM = -1;
    int tr = -1;

    static private MainController myController;

    public CPU(MainController myController) {
        this.myController = myController;
//        for (int i = 0; i < 10; i++){
//            for (int j = 0; j < 5; j++){
//                String t = Integer.toString((j+1)+5*i) + ": " + Integer.toString(Memory[j+5*i]);
//                modifyGridRAM(i, j, t, Color.BLACK);
//            }
//        }
//        for (int j = 0; j < 4; j++){
//            String t = Integer.toString(Registrs[j]);
//            modifyGridregistr(1, j, t, Color.BLACK);
//        }
//        for (int i = 0; i < Memory.length; i++) {
//            Memory[i] = i*10;
//        }
    }

    public void modifyGridregistr(int row, int col, String newText, Paint color) {
        myController.modifyGrid(myController.getGridregistr(),row, col, newText, color);
    }
    public void modifyGridRAM(int row, int col, String newText, Paint color) {
        myController.modifyGrid(myController.getGridRAM(),row, col, newText, color);
    }

    public GridPane getGridregistr() {
        return myController.getGridregistr();
    }
    public GridPane getGridRAM() {
        return myController.getGridRAM();
    }

    @Override
    public void exec(Command c){

        if (Columnregistr != -1) {
            String t = Integer.toString(Registrs[Columnregistr]);
            modifyGridregistr(1, Columnregistr, t, Color.BLACK);
        }
        if (ColumnRAM != -1) {
            String t = Integer.toString(tr+1) + ": " + Integer.toString(Memory[ColumnRAM+5*rowRAM]);
            modifyGridRAM(rowRAM, ColumnRAM, t, Color.BLACK);
        }
        Columnregistr = -1;
        rowRAM = -1;
        ColumnRAM = -1;
        tr = -1;

        switch(c.info[0]){
            case "init": init(c.info[1], c.info[2]); break;
            case "ld": ld(c.info[1], c.info[2]); break;
            case "st": st(c.info[1], c.info[2]); break;
            case "mv": mv(c.info[1], c.info[2]); break;
            case "print": print(); break;
            case "add": add(); break;
            case "sub": sub(); break;
            case "mult": mult(); break;
        }
    }

    public void ld(String s, String f){ // f - индекс регистра памяти от 0 до 1023
        int y = Integer.parseInt(f);
        switch (s){
            case "A":
            case "a":
            {
                Registrs[0] = Memory[y];
                modifyGridregistr(1, 0, Integer.toString(Memory[y]) , Color.RED);
                Columnregistr = 0;
                break;
            }
            case "B":
            case "b":
            {
                Registrs[0] = Memory[y];
                modifyGridregistr(1, 1, Integer.toString(Memory[y]), Color.RED);
                Columnregistr = 1;
                break;
            }
            case "C":
            case "c":
            {
                Registrs[0] = Memory[y];
                modifyGridregistr(1, 2, Integer.toString(Memory[y]), Color.RED);
                Columnregistr = 2;
                break;
            }
            case "D":
            case "d":
            {
                Registrs[0] = Memory[y];
                modifyGridregistr(1, 3, Integer.toString(Memory[y]),Color.RED);
                Columnregistr = 3;
                break;
            }
        }
    }


    public void st(String s, String f){
        int y = Integer.parseInt(f);
        switch (s){
            case "A":
            case "a":
            {
                Memory[y] = Registrs[0];
                modifyGridRAM(y/5, y%5, Integer.toString(y) + ": " + Integer.toString(Registrs[0]), Color.RED);
                rowRAM = y/5;
                ColumnRAM = y%5;
                tr = y+1;
                break;
            }
            case "B":
            case "b":
            {
                Memory[y] = Registrs[0];
                modifyGridRAM(y/5, y%5, Integer.toString(y) + ": " + Integer.toString(Registrs[1]), Color.RED);
                rowRAM = y/5;
                ColumnRAM = y%5;
                tr = y+1;
                break;
            }
            case "C":
            case "c":
            {
                Memory[y] = Registrs[0];
                modifyGridRAM(y/5, y%5, Integer.toString(y) + ": " + Integer.toString(Registrs[2]), Color.RED);
                rowRAM = y/5;
                ColumnRAM = y%5;
                tr = y+1;
                break;
            }
            case "D":
            case "d":
            {
                Memory[y] = Registrs[0];
                modifyGridRAM(y/5, y%5, Integer.toString(y) + ": " + Integer.toString(Registrs[3]), Color.RED);
                rowRAM = y/5;
                ColumnRAM = y%5;
                tr = y+1;
                break;
            }
        }
    }
    public void mv(String s, String f) {
        switch (s) {
            case "A":
            case "a": {
                if ((f.equals("b")) || (f.equals("B"))) {
                    Registrs[0] = Registrs[1];
                    Columnregistr = 0;
                }
                if ((f.equals("c")) || (f.equals("C"))) {
                    Registrs[0] = Registrs[2];
                    Columnregistr = 0;
                }
                if ((f.equals("d")) || (f.equals("D"))) {
                    Registrs[0] = Registrs[3];
                    Columnregistr = 0;
                }
                modifyGridregistr(1, 0, Integer.toString(Registrs[0]), Color.RED);
                break;
            }
            case "B":
            case "b": {
                if ((f.equals("a")) || (f.equals("A"))) {
                    Registrs[1] = Registrs[0];
                    Columnregistr = 1;
                }
                if ((f.equals("c")) || (f.equals("C"))) {
                    Registrs[1] = Registrs[2];
                    Columnregistr = 1;
                }
                if ((f.equals("d")) || (f.equals("D"))) {
                    Registrs[1] = Registrs[3];
                    Columnregistr = 1;
                }
                modifyGridregistr(1, 1, Integer.toString(Registrs[1]), Color.RED);
                break;
            }
            case "C":
            case "c": {
                if ((f.equals("b")) || (f.equals("B"))) {
                    Registrs[2] = Registrs[1];
                    Columnregistr = 2;
                }
                if ((f.equals("a")) || (f.equals("A"))) {
                    Registrs[2] = Registrs[0];
                    Columnregistr = 2;
                }
                if ((f.equals("d")) || (f.equals("D"))) {
                    Registrs[2] = Registrs[3];
                    Columnregistr = 2;
                }
                modifyGridregistr(1, 2, Integer.toString(Registrs[2]), Color.RED);
                break;
            }
            case "D":
            case "d": {
                if ((f.equals("b")) || (f.equals("B"))) {
                    Registrs[3] = Registrs[1];
                    Columnregistr = 3;
                }
                if ((f.equals("c")) || (f.equals("C"))) {
                    Registrs[3] = Registrs[2];
                    Columnregistr = 3;
                }
                if ((f.equals("a")) || (f.equals("A"))) {
                    Registrs[3] = Registrs[0];
                    Columnregistr = 3;
                }
                modifyGridregistr(1, 3, Integer.toString(Registrs[3]), Color.RED);
                break;
            }
        }
    }
    public void init(String s, String f){
        int x = Integer.parseInt(s);
        int y = Integer.parseInt(f);
        Memory[x] = y;
        modifyGridRAM(x/5, x%5, s + ": " + Integer.toString(y), Color.RED);
        rowRAM = x/5;
        ColumnRAM = x%5;
        tr = x - 1;
    }
    public void print(){
        System.out.println("Значения регистров a: " + Registrs[0] + " b: " + Registrs[1] + " c: " + Registrs[2] + " d: " + Registrs[3]);
    }
    public void add(){
        Registrs[3] = Registrs[0] + Registrs[1];
        modifyGridregistr(1, 3, Integer.toString(Registrs[0] + Registrs[1]), Color.RED);
        Columnregistr = 3;
    }
    public void sub(){
        Registrs[3] = Registrs[0] - Registrs[1];
        modifyGridregistr(1, 3, Integer.toString(Registrs[0] - Registrs[1]), Color.RED);
        Columnregistr = 3;
    }
    public void mult(){
        Registrs[3] = Registrs[0] * Registrs[1];
        modifyGridregistr(1, 3, Integer.toString(Registrs[0] * Registrs[1]), Color.RED);
        Columnregistr = 3;
    }
}
