package org.example.lab4;

public class Command {
    String[] info = {"0", "0", "0"};
    Command(String a, String b, String c) {
        info[0] = a;
        info[1] = b;
        info[2] = c;
    }
    Command(String d){this.info[0] = d;}

    String getcommands(int i){
        return info[i];
    }
}


