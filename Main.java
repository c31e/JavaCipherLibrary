package Cipher;

import static Cipher.Cipher.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class customDecimal {

    int type[];
    int maxType[];
    int iteration[];
    int maxIteration[];
    static int currentLayer = 1;
    static int maxLayers;

    customDecimal(int types, int maxIterations[], int maxLayers) {
        this.maxLayers = maxLayers;

        maxIteration = maxIterations;
        type = new int[types];
        iteration = new int[types];

        for (int x = 0; x < types; x++) {

            type[x] = 0;
        }

        maxType = new int[type.length];
        for (int x = 0; x < type.length; x++) {
            maxType[x] = type.length;
        }
        resetIteration();
        type[type.length - 1]++;

    }

    public void incrementIteration() {
        int largest = 0;

        for (int x = iteration.length - 1; x >= 0; x--) {

            if (type[x] != 0) {
                if (type[x] > largest) {
                    largest = type[x];
                }

            }
        }
        iteration[largest - 1]++;

        for (int x = iteration.length - 1; x >= 0; x--) {
            if (iteration[x] > maxIteration[x]) {
                cleanIteration();
            }

        }

    }

    public void cleanIteration() {
//        System.out.print("before clean ");
//        for (int x : iteration) {
//            System.out.print(x + " ");
//        }
//        System.out.println();

        for (int x = iteration.length - 1; x > 0; x--) {

            if (iteration[x] > maxIteration[x]) {
                iteration[x] = 1;

                while (x > 0) {
                    x--;
                    if (iteration[x] != 0) {
                        iteration[x]++;
                        break;
                    }
                }
                break;

            }
        }

//        System.out.print("after clean ");
//        for (int x : iteration) {
//           System.out.print(x + " ");
//        }
//        System.out.println();
    }

//        public void cleanIteration() {
//        System.out.print("before clean ");
//        for (int x : iteration) {
//            System.out.print(x + " ");
//        }
//        System.out.println();
//
//        for (int x = iteration.length - 1; x > 0; x--) {
//
//            if (iteration[x] > maxIteration[x]) {
//                iteration[x] = 1;
//
//                while (x > 0) {
//                    x--;
//                    if (iteration[type[x] - 1] != 0) {
//                        iteration[type[x] - 1]++;
//                        break;
//                    }
//                }
//                break;
//
//            }
//        }
//
//        System.out.print("after clean ");
//        for (int x : iteration) {
//           System.out.print(x + " ");
//        }
//        System.out.println();
//    }
//   public void incrementIteration() {
//
//        for (int x = iteration.length - 1; x >= 0; x--) {
//
//            if (type[x] != 0) {
//
//                iteration[type[x] - 1]++;
//
//                if (iteration[type[x] - 1] > maxIteration[type[x] - 1]) {
//                    System.out.println("Max");
//                    iteration[type[x] - 1] = 1;
//                    while (true) {
//                         x--;
//                        if (type[x] != 0 && !(iteration[type[x]-1] == maxIteration[type[x]-1])) {
//                            iteration[type[x] - 1]++;
//                            System.out.println("iteration" + (type[x] - 1) + "=1");
//                            return;
//                        }
//                       
//                    }
//
//                }
//                return;
//
//            }
//
//        }
//
//    }
    public boolean maxIteration() {
        int combos = 0;
        int combosMaxed = 0;

        for (int x = 0; x < iteration.length; x++) {

            if (type[x] != 0) {

                if (iteration[type[x] - 1] != maxIteration[type[x] - 1]) {
                    return false;

                }
            }

        }

        return true;

//         for(int x =0;x<type.length;x++){
//             
//             if(type[x]!=0){
//                 
//                 if(iteration[x]==maxIteration[x]){
//                     return true;
//                 }
//                 
//                 
//             }
//             
//             
//         }
//        
//        if (Arrays.equals(iteration, maxIteration)) {
//            return true;
//        }
    }

    public void incrementPermutation() {
        type[type.length - 1]++;// increase last number

        for (int x = type.length - 1; x > 0; x--) {
            if (type[x] > type.length) {
                type[x - 1]++;
                type[x] = 1;
            }
        }

    }

    public boolean duplicateFound() {

        int count[] = new int[type.length + 1];
        for (int x = 0; x < type.length; x++) {
            if (type[x] != 0) {
                count[type[x] - 1]++;
            }
        }

        for (int x : count) {
            if (x > 1) {

                return true;
            }
        }
        return false;
    }

    public boolean maxPermutation() {
        if (Arrays.equals(type, maxType)) {
            return true;
        }
        return false;
    }

//    public boolean nextPermutation() {
//        incrementPermutation();
//        while (duplicateFound() && !maxPermutation()) {
//            incrementPermutation();
//        }
//
//        return !maxPermutation();
//    }
    public void resetIteration() {
        for (int x = 0; x < iteration.length; x++) {
            iteration[x] = 0;

        }

        for (int x = 0; x < type.length; x++) {
            if (type[x] != 0) {
                iteration[type[x] - 1] = 1;

            }

        }

        //for (int y : iteration) {
        //    System.out.print("=" + y);
        //}
        //System.out.println();
    }

    public boolean nextPart() {

        if (maxIteration()) {

            incrementPermutation();
            while (duplicateFound() && !maxPermutation()) {
                incrementPermutation();
            }

            //System.out.println("=============================================");
            resetIteration();
            currentLayer++;
            System.out.println(currentLayer + "/" + maxLayers);

        } else {
            incrementIteration();
        }

        return !maxPermutation();
    }

    public String print() {
        String output = "Type: ";
        for (int x = 0; x < type.length; x++) {
            output += type[x] + " ";

        }
        output += " Iteration: ";
        for (int x = 0; x < iteration.length; x++) {
            output += iteration[x] + " ";

        }
        return output;
    }

}

public class Main {

    //static String input = "test";
    static String input = "obkruoxoghulbsolifbbwflrvqqprngkssotwtqsjqssekzzwatjkludiawinfbnypvttmzfpkwgdkzxtjcdigkuhuauekcar";
    // public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static String alphabet = "kryptosabcdefghijlmnquvwxz";
    static boolean encryption = false;

    static String[] words;

    public static void main(String[] args) {
        //testCiphers();
        //////////////////////////
        int[] calculatedLayers = {0, 4, 15, 64, 325, 1956, 13699, 109600, 986409, 9864100};
        words = textFileToArray();
        int layers = 3;
        int maxIterations[] = {25, 1, 10000};
        customDecimal current = new customDecimal(layers, maxIterations, calculatedLayers[layers - 1]);

        long totalCount = 0;
        System.out.println("Input                : " + input);
        System.out.println("Input length         : " + input.length());
        System.out.println("Alphabet in use      : " + alphabet);
        System.out.println("Encrypt?             : " + encryption);
        System.out.println("layers               : " + layers);
        System.out.println("total layers         : " + calculatedLayers[layers - 1]);
        System.out.print("Iterations per layer   : ");
        for (int x : maxIterations) {
            System.out.print(x + ",");
        }
        System.out.println();

        System.out.println("Words in dictionary  : " + (words.length));
        /////////////////////////////////
        System.out.println("====================");
        long startTime = System.nanoTime();
        while (current.nextPart()) {

            //String output = convert(current);
            //System.out.println(current.print());
   
           if (test(convert(current))) {
            System.out.println(current.print() + " " + convert(current));
            }
            totalCount++;

        }
        long timeElapsed = System.nanoTime() - startTime;

        System.out.printf("Time: %.2f, Iterations: %,d",(double)timeElapsed / 1000000000.0,totalCount);
        /////////////////////////////////

    }

    public static void testCiphers() {
        String testInput = "wellyvdfdjnbifdsuhsfdiashbvd";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("each cipher is ran 1000000 times");

        long startTime = System.nanoTime();
        for (int x = 0; x < 1000000; x++) {
            generateCaesar(testInput, encryption, 25, alphabet);
        }
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("  Caesar: " + timeElapsed / 1000000 + " ms");
        //
        startTime = System.nanoTime();
        for (int x = 0; x < 1000000; x++) {
            generateAtbash(testInput, alphabet);
        }
        timeElapsed = System.nanoTime() - startTime;
        System.out.println("  Affine: " + timeElapsed / 1000000 + " ms");
        //
        startTime = System.nanoTime();
        for (int x = 0; x < 1000000; x++) {
            generateVigenere(testInput, "helloth", alphabet, encryption, true);
        }
        timeElapsed = System.nanoTime() - startTime;
        System.out.println("Vigenere: " + timeElapsed / 1000000 + " ms");

    }

    public static String[] textFileToArray() {
        ArrayList<String> temp = new ArrayList<>();
        File txt = new File("words_alpha.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(txt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (sc.hasNext()) {
            String x = sc.nextLine();
            if (x.length() > 3) {
                temp.add(x);
            }

        }

        String[] arr = new String[temp.size()];
        arr = temp.toArray(arr);

        return arr;

    }

    static String convert(customDecimal current) {
        
        String output = input;
        for (int x = 0; x < current.type.length; x++) {

            if (current.type[x] == 1) {
                output = generateCaesar(output, encryption, current.iteration[current.type[x] - 1], alphabet);
            } else if (current.type[x] == 2) {
                output = generateAtbash(output, alphabet);
            } else if (current.type[x] == 3) {
                if (current.iteration[current.type[x] - 1] > words.length - 1) {
                    current.print();
                } else {
                    output = generateVigenere(output, words[current.iteration[current.type[x] - 1]], alphabet, encryption, false);
                }
            }
            else if(current.type[x] == 4){
                
                
                
            }

        }
        return output;
    }

    static boolean test(String input) {
        if (input.substring(63, 74).equals("berlinclock")) {
            System.out.println("wait hang on a sec is this it?");
            return true;
        }
        return false;
    }

}
