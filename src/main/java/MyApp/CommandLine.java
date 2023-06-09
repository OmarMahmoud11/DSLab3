package MyApp;


import java.util.Arrays;
import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AbstractFactory Hash;
        while (true) {
            System.out.print("Enter the type of the backend HashTable (HashingTable1 - HashingTable2): ");
            String HashType = sc.nextLine();
            System.out.print("Enter the number N: ");
            int n = sc.nextInt();
            Hash = FactoryProducer.GetDictionaryHashingType(HashType,n);
            if (Hash == null) System.out.println("Invalid Tree Type");
            else break;
        }
        System.out.println("Welcome To Simple Dictionary!");
        System.out.println("Supported Commands:");
        System.out.println("    • Insert <word>");
        System.out.println("    • Delete <word>");
        System.out.println("    • Search <word>");
        System.out.println("    • Batch Insert");
        System.out.println("    • Batch Delete");
        System.out.println("    • GetTable");
        System.out.println("    • Exit");
        while(true){
            System.out.print("Enter Command: ");
            String input = sc.nextLine();
            String[] in = input.split(" ");
            if (in.length == 0) {
                System.out.println("Invalid Command");
                continue;
            }
            if (in.length == 1) {
                if (in[0].equalsIgnoreCase("exit")) break;
                else if(in[0].equalsIgnoreCase("GetTable")){
                    System.out.println(Arrays.toString(Hash.GetTable()));
                }
                else {
                    System.out.println("Invalid Command");
                }
            }
            else{
                String command = in[0];
                String word = in[1];
                if (command.equalsIgnoreCase("insert")) {
                    long start = System.nanoTime();
                    if (Hash.insert(word)) {
                        System.out.println("Inserted Successfully");
                    } else {
                        System.out.println("Word Already in Dictionary");
                    }
                    long end = System.nanoTime() - start;
                    System.out.println("Time Taken = " + end);
                }
                else if (command.equalsIgnoreCase("delete")) {
                    long start = System.nanoTime();
                    if (Hash.delete(word)) {
                        System.out.println("Deleted Successfully");
                    } else {
                        System.out.println("Word Not in Dictionary");
                    }
                    long end = System.nanoTime() - start;
                    System.out.println("Time Taken = " + end);
                }
                else if (command.equalsIgnoreCase("search")) {
                    long start = System.nanoTime();
                    if (Hash.search(word)) {
                        System.out.println("Found!");
                    } else {
                        System.out.println("Not Found!");
                    }
                    long end = System.nanoTime() - start;
                    System.out.println("Time Taken = " + end);
                }
                else if (command.equalsIgnoreCase("batch")) {
                    long start, end = 0;
                    if (word.equalsIgnoreCase("insert")) {
                        System.out.print("Enter File Path: ");
                        String file = sc.nextLine();
                        start = System.nanoTime();
                        int[] res = Hash.BatchInsert(file);
                        end = System.nanoTime() - start;
                        System.out.println("Successful Inserts = " + res[0]);
                        System.out.println("Failed Inserts = " + res[1]);
                    }
                    else if (word.equalsIgnoreCase("delete")) {
                        System.out.print("Enter File Path: ");
                        String file = sc.nextLine();
                        start = System.nanoTime();
                        int[] res = Hash.BatchDelete(file);
                        end = System.nanoTime() - start;
                        System.out.println("Successful Deletions = " + res[0]);
                        System.out.println("Failed Deletions = " + res[1]);
                    }
                    else {
                        System.out.println("Invalid Command");
                    }
                    System.out.println("Time Taken = " + end);
                }
            }
        }
    }
}
