package org.example.lab4;

import java.util.ArrayList;

public class Executor {
    iCPU cpu;

    Executor(iCPU i) {
        cpu = i;
    }

    void run(ArrayList<Command> C) {
        for (Command i : C) {
            cpu.exec(i);
        }
    }
}