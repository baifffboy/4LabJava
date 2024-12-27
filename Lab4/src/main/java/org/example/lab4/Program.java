package org.example.lab4;

import java.util.*;

public class Program implements Iterable<Command> {
    static public ArrayList<Command> commands = new ArrayList<>();
    static public HashMap<String, Integer> counter = new HashMap<>();
//    int sizee(){
//        return commands.size();
//    }

    public Program(){};

    static void addCommand(Command c) {
        commands.add(c);
        if(counter.containsKey(c.info[0])){
            counter.put(c.info[0], counter.get(c.info[0]) + 1);
        }
        else
            counter.put(c.info[0], 1 );
    }

    @Override
    public Iterator<Command> iterator() {
        return new CommandIterator();
    }

    public class CommandIterator implements Iterator<Command> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < commands.size();
        }

        @Override
        public Command next() {
            return commands.get(currentIndex++);
         }
    }
}