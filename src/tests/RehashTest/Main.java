package tests.RehashTest;

import java.util.Scanner;
import java.io.FileNotFoundException;

import dataStructures.Dictionary;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.SepChainHashTable;


public class Main {

    private static final String END = "END";
    private static final String ENTRY_PRINT = "  key: %s, value: %s\n";
    private static final String ALL = "all";
    private static final String ADD = "add";
    private static final String QUIT = "quit";
    private static final String CHRISTMAS = "Merry Christmas!!";



    public static void main(String[] args) throws FileNotFoundException {
       Scanner in = new Scanner(System.in);
       int size = in.nextInt();
       in.nextLine();
       Dictionary<String, String> table = new SepChainHashTable<>(size);
       commandReader(in, table, size);
       in.close();
    }   

    private static void commandReader(Scanner in, Dictionary<String, String> table, int maxSize) {
        String command = in.next().toLowerCase();
        while(!command.equals(QUIT)){
            switch(command) {
                case ADD:
                    add(in, table, maxSize);
                    break;
                case ALL:
                    all(in, table);
                    break;
            }
            command = in.next().toLowerCase();
        }
    }

    private static void all(Scanner in, Dictionary<String, String> table) {
        in.nextLine();
        Iterator<Entry<String, String>> it = table.iterator();
        while(it.hasNext()){
            Entry<String, String> curr = it.next();
            System.out.printf(ENTRY_PRINT, curr.getKey(), curr.getValue());
        }
        System.out.println(END);
    }

    @SuppressWarnings("unchecked")
    private static void add(Scanner in, Dictionary<String, String> table, int maxSize) {
        String key = in.next().trim();
        String val = in.nextLine().trim();
        SepChainHashTable<String,String> t = (SepChainHashTable<String, String>) table;
        //if (t.size()+1 == maxSize) System.out.println(CHRISTMAS);	
        table.insert(key, val);
    }

    
}