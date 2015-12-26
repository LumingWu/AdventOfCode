package advent.of.code;

import Components.Adjacency;
import Components.Dijkstra;
import Components.Edge;
import Components.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AdventOfCode {
    /* 
        Challenge from http://adventofcode.com/
    */
    public static void main(String[] args) {
        /*
        Day_1();
        Day_2();
        Day_3();
        Day_4();
        Day_5();
        Day_6_2();
        Day_7();
        Day_8();
        Day_9();
        Day_10();
        Day_11();
        */
        Day_12();
    }
    
    public static void Day_12(){
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day12Input.txt"));
            String text = scanner.next();
            scanner.close();
            int number = 0;
            /*
            String[] list = text.split("[^0-9-]");
            for(String s : list){
                if(s.length() != 0){
                    number = number + Integer.parseInt(s);
                }
            }
            System.out.println(number);
            */
            /*
            int index = 0, length = text.length(), left, right, counter;
            String temp;
            while(index < length){
                if(text.charAt(index) == 'r' && index + 1 < length && text.charAt(index + 1) == 'e' && index + 2 < length && text.charAt(index + 2) == 'd'){
                    left = index - 1;
                    while(true){
                        if(text.charAt(left) == '['){
                            index = index + 2;
                            break;
                        }
                        else if(text.charAt(left) == '{'){
                            right = index + 3;
                            counter = 1;
                            while(true){
                                if(text.charAt(right) == '{'){
                                    counter = counter + 1;
                                }
                                else if(text.charAt(right) == '}' && counter == 1){
                                    temp = text.substring(left, right + 1);
                                    System.out.println(temp);
                                    String[] list = temp.split("[^0-9-]");
                                    System.out.println(Arrays.asList(list));
                                    for(String s : list){
                                        if(s.length() != 0){
                                            number = number + Integer.parseInt(s);
                                        }
                                    }
                                    index = right;
                                    break;
                                }
                                else if(text.charAt(right) == '}'){
                                    counter = counter - 1;
                                }
                                right = right + 1;
                            }
                            break;
                        }
                        left = left - 1;
                    }
                }
                index = index + 1;
            }I am tired of this non-sense... I found the value that should be ignored to reduce the first answer, still WRONG! Time to use JSON*/
            JSONParser parser = new JSONParser();
            try {
                Object object = parser.parse(text);
                JSONRecursion(object);
            } catch (ParseException ex) {
                Logger.getLogger(AdventOfCode.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(number);
        } catch (FileNotFoundException ex) {}
    }
    
    public static void JSONRecursion(Object object){
        int size;
        if(object instanceof JSONArray){
            JSONArray o = (JSONArray)object;
            size = o.size();
            for(int i = 0; i < size; i++){
                Object innerObject = o.get(i);
                if(innerObject instanceof JSONArray){
                    JSONArray innerO = (JSONArray)innerObject;
                    if(innerO.size() == 0){
                        System.out.println(innerO);
                    }
                    else{
                        JSONRecursion(innerO);
                    }
                }
                else{
                    JSONObject innerO = (JSONObject)innerObject;
                    if(innerO.size() == 0){
                        System.out.println(innerO);
                    }
                    else{
                        JSONRecursion(innerO);
                    }
                }
            }
        }
        else{
            JSONObject o = (JSONObject)object;
            o.size();
            size = o.size();
            for(int i = 0; i < size; i++){
                Object innerObject = o.get(i);
                if(innerObject instanceof JSONArray){
                    JSONArray innerO = (JSONArray)innerObject;
                    if(innerO.size() == 0){
                        System.out.println(innerO);
                    }
                    else{
                        JSONRecursion(innerO);
                    }
                }
                else{
                    JSONObject innerO = (JSONObject)innerObject;
                    if(innerO == null){
                        System.out.println(innerO);
                    }
                    else{
                        JSONRecursion(innerO);
                    }
                }
            }
        }
    }
    
    public static void Day_11(){
        String s = "vzbxkghb";
        charNode LSC = new charNode();
        charNode currentC = LSC;
        LSC.c = s.charAt(s.length() - 1);
        for(int i = s.length() - 2; i >= 0; i--){
            charNode newNode = new charNode();
            newNode.c = s.charAt(i);
            newNode.right = currentC;
            currentC.left = newNode;
            currentC = newNode;
        }
        while(true){
            // Addition
            currentC = LSC;
            currentC.c = currentC.c + 1;
            while(currentC.c == 123){
                currentC.c = 97;
                charNode left = currentC.left;
                if(left == null){
                    left = new charNode();
                    left.right = currentC;
                    currentC.left = left;
                }
                else{
                    left.c = left.c + 1;
                }
                currentC = left;
            }
            // Get leftmost
            while(currentC.left != null){
                currentC = currentC.left;
            }
            // Check
            boolean check1 = false, check2 = true;
            int[] check3 = {0,0};
            while(currentC != null){
                int temp = currentC.c;
                if(currentC.right != null && currentC.right.c == temp + 1 && currentC.right.right != null && currentC.right.right.c == temp + 2){
                    check1 = true;
                }
                if(temp == 'i' || temp == 'o' || temp == 'l'){
                    check2 = false;
                }
                if(check3[0] == 0){
                    if(currentC.right != null && currentC.right.c == temp){
                        check3[0] = temp;
                    }
                }
                else{
                    if(currentC.right != null && temp != check3[0] && currentC.right.c == temp){
                        check3[1] = temp;
                    }
                }
                currentC = currentC.right;
            }
            if(check1 && check2 && check3[1] != 0){
                break;
            }
        }
        // Print
        currentC = LSC;
        while(currentC.left != null){
            currentC = currentC.left;
        }
        while(currentC != null){
            System.out.print((char)currentC.c);
            currentC = currentC.right;
        }
        System.out.print("\n");
    }
    
    public static class charNode {
        
        public charNode left;
        public charNode right;
        public int c;
        // This is for Day_12();
        public int x;
        public boolean typeNumber;
        
        public charNode(){}
    }
    
    public static void Day_10(){
        String s = "1321131112";
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(char e : s.toCharArray()){
            list.addLast(e - 48);
        }
        System.out.println(recursiveParse(list, 50));
    }
    
    public static int recursiveParse(LinkedList<Integer> list, int times){
        if(times == 0){
            return list.size();
        }
        LinkedList<Integer> newList = new LinkedList<Integer>();
        int c = list.pop();
        int counter = 1;
        while(!list.isEmpty()){
            if(c == list.getFirst()){
                counter = counter + 1;
                list.pop();
            }
            else{
                newList.add(counter);
                newList.add(c);
                c = list.pop();
                counter = 1;
            }
        }
        newList.add(counter);
        newList.add(c);
        return recursiveParse(newList, times - 1);
    }
    
    public static void Day_9(){
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day9Input.txt"));
            LinkedList<Edge> edges = new LinkedList<Edge>();
            String[] line;
            while(scanner.hasNextLine()){
                line = scanner.nextLine().split(" ");
                edges.add(new Edge(line[0], line[2], Integer.parseInt(line[4])));
            }
            Dijkstra dijkstra = new Dijkstra();
            ArrayList<Node> nodes = dijkstra.convertEdges(edges);
            //double minimum = Double.POSITIVE_INFINITY, length;
            double maximum = 0.0, length;
            LinkedList<Node> list = new LinkedList<Node>();
            for(Node n : nodes){
                list.add(n);
                //length = getShortest(list, n, 0.0);
                length = getLongest(list, n, 0.0);
                list.removeLast();
                //if(length < minimum){
                    //minimum = length;
                //}
                if(length > maximum){
                    maximum = length;
                }
            }
            //System.out.println("Minimum length: " + minimum);
            System.out.println("Maximum length: " + maximum);
            scanner.close();
        } catch (FileNotFoundException ex) {}
    }
    
    public static double getShortest(LinkedList<Node> list, Node node, double length){
        if(list.size() == 8){
            return length;
        }
        double minimum = Double.POSITIVE_INFINITY, temp;
        for(Adjacency a : node.Ajacencies){
            boolean check = true;
            ListIterator<Node> iterator = list.listIterator();
            while(iterator.hasNext()){
                if(iterator.next().equals(a.Node)){
                    check = false;
                }
            }
            if(check){
                list.addLast(a.Node);
                temp = getShortest(list, a.Node, length + a.Weight);
                list.removeLast();
                if(temp < minimum){
                    minimum = temp;
                }
            }
        }
        return minimum;
    }
    
    public static double getLongest(LinkedList<Node> list, Node node, double length){
        if(list.size() == 8){
            return length;
        }
        double maximum = 0, temp;
        for(Adjacency a : node.Ajacencies){
            boolean check = true;
            ListIterator<Node> iterator = list.listIterator();
            while(iterator.hasNext()){
                if(iterator.next().equals(a.Node)){
                    check = false;
                }
            }
            if(check){
                list.addLast(a.Node);
                temp = getLongest(list, a.Node, length + a.Weight);
                list.removeLast();
                if(temp > maximum){
                    maximum = temp;
                }
            }
        }
        return maximum;
    }
    
    public static void Day_8(){
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day8Input.txt"));
            String text;
            int encode = 0;
            int literals = 0;
            int memory = 0;
            int temp;
            int i, length, state;
            while(scanner.hasNextLine()){
                text = scanner.nextLine();
                length = text.length() - 1;
                literals = literals + length + 1;
                encode = encode + length + 5;   /* 1 + 4 = 5 */
                i = 1;
                state = 0;
                char c;
                while(i < length){
                    switch(state){
                        case 0:
                            if(text.charAt(i) == '\\'){
                                state = 1;
                                encode = encode + 1;
                            }
                            else{
                                if(text.charAt(i) == '\"'){
                                    encode = encode + 1;
                                }
                                memory = memory + 1;
                            }
                            break;
                        case 1:
                            switch(text.charAt(i)){
                                case '\\':
                                    memory = memory + 1;
                                    encode = encode + 1;
                                    state = 0;
                                    break;
                                case '\"':
                                    memory = memory + 1;
                                    encode = encode + 1;
                                    state = 0;
                                    break;
                                case 'x':
                                    state = 2;
                                    break;
                                default:
                                    memory = memory + 2;
                                    state = 0;
                                    break;
                            }
                            break;
                        case 2:
                            c = text.charAt(i);
                            /* The input has no capital letter. */
                            if((c > 47 && c < 58) || (c > 96 && c < 103)){
                                state = 3;
                            }
                            else{
                                if(c == '\\' || c == '\"'){
                                    encode = encode + 1;
                                }
                                memory = memory + 3;
                                state = 0;
                            }
                            break;
                        case 3:
                            c = text.charAt(i);
                            /* The input has no capital letter. */
                            if((c > 47 && c < 58) || (c > 96 && c < 103)){
                                state = 0;
                                memory = memory + 1;
                            }
                            else{
                                if(c == '\\' || c == '\"'){
                                    encode = encode + 1;
                                }
                                memory = memory + 4;
                                state = 0;
                            }
                            break;
                    }
                    i = i + 1;
                }
                memory = memory + state;
            }
            System.out.println("Literal: " + literals + ", Memory: " + memory + ", Encode: " + encode + ", Result: " + (literals - memory) + ", " + (encode - literals));
            scanner.close();
        } catch (FileNotFoundException ex) {}
    }
    
    public static void Day_7(){
        try {
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day7Input.txt"));
            ArrayList<String[]> array = new ArrayList<String[]>();
            while(scanner.hasNextLine()){
                array.add(scanner.nextLine().split(" "));
            }
            scanner.close();
            Collections.sort(array, new Comparator<String[]>(){

                @Override
                public int compare(String[] o1, String[] o2) {
                    return o1[o1.length - 1].compareTo(o2[o2.length - 1]);
                }
            });
            HashMap<String, ArrayList> map = new HashMap<String, ArrayList>(array.size());
            int length = array.size();
            for(int i = 0; i < length; i++){
                map.put(array.get(i)[array.get(i).length - 1], new ArrayList(Arrays.asList(array.get(i))));
            }
            /* Part 2 */
            map.put("b", new ArrayList(Arrays.asList(46065)));
            /* Part 2 */
            String var = "a";
            System.out.println("The value of variable " + var + " is " + find(map, var));
        } catch (FileNotFoundException ex) {}
    }
    
    public static int find(HashMap<String, ArrayList> map, String variable){
        ArrayList expression = map.get(variable);
        switch(expression.size()){
            case 1:
                return (int)expression.get(0);
            case 3:
                if(((String)expression.get(0)).charAt(0) < 58){
                    int value = Integer.parseInt((String)expression.get(0));
                    map.put(variable, new ArrayList(Arrays.asList(value)));
                    return value;
                }
                else{
                    int value = find(map, (String)expression.get(0));
                    map.put(variable, new ArrayList(Arrays.asList(value)));
                    return value;
                }
            case 4:
                int value = 65535 - find(map, (String)expression.get(1));
                map.put(variable, new ArrayList(Arrays.asList(value)));
                return value;
            case 5:
                switch(((String)expression.get(1)).charAt(0)){
                    case 'A':
                        if(((String)expression.get(0)).charAt(0) < 58){
                            if(((String)expression.get(2)).charAt(0) < 58){
                                int value2 = Integer.parseInt((String)expression.get(0)) & Integer.parseInt((String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                            else{
                                int value2 = Integer.parseInt((String)expression.get(0)) & find(map, (String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                        }
                        else{
                            if(((String)expression.get(2)).charAt(0) < 58){
                                int value2 = Integer.parseInt((String)expression.get(2)) & find(map, (String)expression.get(0));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                            else{
                                int value2 = find(map, (String)expression.get(0)) & find(map, (String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                        }
                    case 'O':
                        if(((String)expression.get(0)).charAt(0) < 58){
                            if(((String)expression.get(2)).charAt(0) < 58){
                                int value2 = Integer.parseInt((String)expression.get(0)) | Integer.parseInt((String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                            else{
                                int value2 = Integer.parseInt((String)expression.get(0)) | find(map, (String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                        }
                        else{
                            if(((String)expression.get(2)).charAt(0) < 58){
                                int value2 = Integer.parseInt((String)expression.get(2)) | find(map, (String)expression.get(0));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                            else{
                                int value2 = find(map, (String)expression.get(0)) | find(map, (String)expression.get(2));
                                map.put(variable, new ArrayList(Arrays.asList(value2)));
                                return value2;
                            }
                        }
                    case 'L':
                        int value2 = find(map, (String)expression.get(0)) << Integer.parseInt((String)expression.get(2));
                        map.put(variable, new ArrayList(Arrays.asList(value2)));
                        return value2;
                    case 'R':
                        int value3 = find(map, (String)expression.get(0)) >> Integer.parseInt((String)expression.get(2));
                        map.put(variable, new ArrayList(Arrays.asList(value3)));
                        return value3;
                }
        }
        return 0;
    }
    
    public static void Day_6_2(){
        try{
            int[][] grid = new int[1000][1000];
            for(int[] array : grid){
                Arrays.fill(array, 0);
            }
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day6Input.txt"));
            String[] coordinates;
            int i, j, k, l, temp, counter = 0;
            while(scanner.hasNext()){
                if(scanner.next().equals("turn")){
                    if(scanner.next().equals("on")){
                        coordinates = scanner.next().split(",");
                        i = Integer.parseInt(coordinates[0]);
                        j = Integer.parseInt(coordinates[1]);
                        scanner.next();
                        coordinates = scanner.next().split(",");
                        k = Integer.parseInt(coordinates[0]);
                        l = Integer.parseInt(coordinates[1]);
                        while(i <= k){
                            temp = j;
                            while(j <= l){
                                grid[i][j] = grid[i][j] + 1;
                                j = j + 1;
                            }
                            j = temp;
                            i = i + 1;
                        }
                    }
                    else{
                        coordinates = scanner.next().split(",");
                        i = Integer.parseInt(coordinates[0]);
                        j = Integer.parseInt(coordinates[1]);
                        scanner.next();
                        coordinates = scanner.next().split(",");
                        k = Integer.parseInt(coordinates[0]);
                        l = Integer.parseInt(coordinates[1]);
                        while(i <= k){
                            temp = j;
                            while(j <= l){
                                if(grid[i][j] != 0){
                                    grid[i][j] = grid[i][j] - 1;
                                }
                                j = j + 1;
                            }
                            j = temp;
                            i = i + 1;
                        }
                    }
                }
                else{
                    coordinates = scanner.next().split(",");
                    i = Integer.parseInt(coordinates[0]);
                    j = Integer.parseInt(coordinates[1]);
                    scanner.next();
                    coordinates = scanner.next().split(",");
                    k = Integer.parseInt(coordinates[0]);
                    l = Integer.parseInt(coordinates[1]);
                    while(i <= k){
                        temp = j;
                        while(j <= l){
                            grid[i][j] = grid[i][j] + 2;
                            j = j + 1;
                        }
                        j = temp;
                        i = i + 1;
                    }
                }
            }
            for(int[] col : grid){
                for(int value : col){
                    counter = counter + value;
                }
            }
            System.out.println("Total brightness: " + counter);
            scanner.close();
        } catch(FileNotFoundException ex){}
    }
    
    public static void Day_6_1(){
        try{
            boolean[][] grid = new boolean[1000][1000];
            for(boolean[] array : grid){
                Arrays.fill(array, false);
            }
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day6Input.txt"));
            String[] coordinates;
            int i, j, k, l, temp, counter = 0;
            while(scanner.hasNext()){
                if(scanner.next().equals("turn")){
                    if(scanner.next().equals("on")){
                        coordinates = scanner.next().split(",");
                        i = Integer.parseInt(coordinates[0]);
                        j = Integer.parseInt(coordinates[1]);
                        scanner.next();
                        coordinates = scanner.next().split(",");
                        k = Integer.parseInt(coordinates[0]);
                        l = Integer.parseInt(coordinates[1]);
                        while(i <= k){
                            temp = j;
                            while(j <= l){
                                grid[i][j] = true;
                                j = j + 1;
                            }
                            j = temp;
                            i = i + 1;
                        }
                    }
                    else{
                        coordinates = scanner.next().split(",");
                        i = Integer.parseInt(coordinates[0]);
                        j = Integer.parseInt(coordinates[1]);
                        scanner.next();
                        coordinates = scanner.next().split(",");
                        k = Integer.parseInt(coordinates[0]);
                        l = Integer.parseInt(coordinates[1]);
                        while(i <= k){
                            temp = j;
                            while(j <= l){
                                grid[i][j] = false;
                                j = j + 1;
                            }
                            j = temp;
                            i = i + 1;
                        }
                    }
                }
                else{
                    coordinates = scanner.next().split(",");
                    i = Integer.parseInt(coordinates[0]);
                    j = Integer.parseInt(coordinates[1]);
                    scanner.next();
                    coordinates = scanner.next().split(",");
                    k = Integer.parseInt(coordinates[0]);
                    l = Integer.parseInt(coordinates[1]);
                    while(i <= k){
                        temp = j;
                        while(j <= l){
                            grid[i][j] = !grid[i][j];
                            j = j + 1;
                        }
                        j = temp;
                        i = i + 1;
                    }
                }
            }
            for(boolean[] col : grid){
                for(boolean value : col){
                    if(value){
                        counter = counter + 1;
                    }
                }
            }
            System.out.println("Lights lit: " + counter);
            scanner.close();
        } catch(FileNotFoundException ex){}
    }
    public static void Day_5(){
        try {
            System.out.println("Day5");
            /* Part 1
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day5Input.txt"));
            String text;
            int counter = 0;
            while(scanner.hasNextLine()){
                text = scanner.nextLine();
                if( !text.contains("ab") && !text.contains("cd") && !text.contains("pq") && !text.contains("xy") &&
                    (text.contains("aa") || text.contains("bb") || text.contains("cc") || text.contains("dd") ||
                    text.contains("ee") || text.contains("ff") || text.contains("gg") || text.contains("hh") || 
                    text.contains("ii") || text.contains("jj") || text.contains("kk") || text.contains("ll") || 
                    text.contains("mm") || text.contains("nn") || text.contains("oo") || text.contains("pp") || 
                    text.contains("qq") || text.contains("rr") || text.contains("ss") || text.contains("tt") || 
                    text.contains("uu") || text.contains("vv") || text.contains("ww") || text.contains("xx") || 
                    text.contains("yy") || text.contains("zz")) && (num_contain(text,'a') + num_contain(text,'e') +
                    num_contain(text,'i') + num_contain(text,'o') + num_contain(text,'u') > 2)
                    ){
                    counter = counter + 1;
                }
            }
            System.out.println("Nice strings: " + counter);
            */
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day5Input.txt"));
            String text;
            int counter = 0, i, length, j, length2;
            boolean contain1, contain2;
            while(scanner.hasNextLine()){
                text = scanner.nextLine();
                contain1 = false; contain2 = false;
                i = 0; length = text.length() - 3;
                while(i < length){
                    if(contain1){
                        break;
                    }
                    j = i + 2; length2 = length + 2;
                    while(j < length2){
                        if(text.charAt(i) == text.charAt(j) && text.charAt(i + 1) == text.charAt(j + 1)){
                            contain1 = true;
                            break;
                        }
                        j = j + 1;
                    }
                    i = i + 1;
                }
                i = 0; length = text.length() - 2;
                while(i < length){
                    if(text.charAt(i) == text.charAt(i + 2)){
                        contain2 = true;
                        break;
                    }
                    i = i + 1;
                }
                if(contain1 && contain2){
                    counter = counter + 1;
                }
            }
            System.out.println("Nice strings: " + counter);
            scanner.close();
        } catch (FileNotFoundException ex) {}
    }
    
    public static int num_contain(String s, char c){
        int counter = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == c){
                counter = counter + 1;
            }
        }
        return counter;
    }
    public static void Day_4() {
        System.out.println("Day4");
        String input = "yzbqklnj";
        int i = 0;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            while(i <= Integer.MAX_VALUE){
                md.update((input + Integer.toString(i)).getBytes());
                byte[] digest = md.digest();
                if( (((digest[0] >> 7) & 1) * 8 + ((digest[0] >> 6) & 1) * 4 + ((digest[0] >> 5) & 1) * 2 + ((digest[0] >> 4) & 1)) == 0 &&
                    (((digest[0] >> 3) & 1) * 8 + ((digest[0] >> 2) & 1) * 4 + ((digest[0] >> 1) & 1) * 2 + (digest[0] & 1)) == 0 &&
                    (((digest[1] >> 7) & 1) * 8 + ((digest[1] >> 6) & 1) * 4 + ((digest[1] >> 5) & 1) * 2 + ((digest[1] >> 4) & 1)) == 0 &&
                    (((digest[1] >> 3) & 1) * 8 + ((digest[1] >> 2) & 1) * 4 + ((digest[1] >> 1) & 1) * 2 + (digest[1] & 1)) == 0 &&
                    (((digest[2] >> 7) & 1) * 8 + ((digest[2] >> 6) & 1) * 4 + ((digest[2] >> 5) & 1) * 2 + ((digest[2] >> 4) & 1)) == 0 &&
                    (((digest[2] >> 3) & 1) * 8 + ((digest[2] >> 2) & 1) * 4 + ((digest[2] >> 1) & 1) * 2 + (digest[2] & 1)) == 0
                    /* Comment last row for part 1 */
                    ){
                    System.out.println("The decimal is: " + Integer.toString(i));
                    break;
                }
                i = i + 1;
            }
        } catch (NoSuchAlgorithmException ex) {}
    }
    
    public static void Day_3(){
        try {
            System.out.println("Day3");
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day3Input.txt"));
            String text = scanner.next();
            scanner.close();
            int h = 0, v = 0, h1 = 0, v1 = 0, i = 0, length = text.length(), coordinate;
            HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
            while(i < length){
                switch(text.charAt(i)){
                    case '^':
                        if(i % 2 == 0){
                            v = v + 1;
                        }
                        else{
                            v1 = v1 + 1;
                        }
                        break;
                    case 'v':
                        if(i % 2 == 0){
                            v = v - 1;
                        }
                        else{
                            v1 = v1 - 1;
                        }
                        break;
                    case '>':
                        if(i % 2 == 0){
                            h = h + 1;
                        }
                        else{
                            h1 = h1 + 1;
                        }
                        break;
                    case '<':
                        if(i % 2 == 0){
                            h = h - 1;
                        }
                        else{
                            h1 = h1 - 1;
                        }
                        break;
                }
                if(i % 2 == 0){
                    if(map.containsKey(v)){
                        if(!map.get(v).containsKey(h)){
                            map.get(v).put(h, 1);
                        }
                    }
                    else{
                        map.put(v, new HashMap<Integer, Integer>());
                        map.get(v).put(h, 1);
                    }
                }
                else{
                    if(map.containsKey(v1)){
                        if(!map.get(v1).containsKey(h1)){
                            map.get(v1).put(h1, 1);
                        }
                    }
                    else{
                        map.put(v1, new HashMap<Integer, Integer>());
                        map.get(v1).put(h1, 1);
                    }
                }
                i = i + 1;
            }
            i = 0;
            for(HashMap<Integer, Integer> entryi : map.values()){
                for(Integer entryj : entryi.values()){
                    i = i + 1;
                }
            }
            System.out.println("Houses: " + i);
        } catch (FileNotFoundException ex) {}
    }
    
    public static void Day_2(){
        try {
            System.out.println("Day2");
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day2Input.txt"));
            int side1, side2, side3, surface1, surface2, surface3, totalSurface = 0, ribbonLength = 0;
            String[] sides;
            while(scanner.hasNextLine()){
                sides = scanner.nextLine().split("x");
                side1 = Integer.parseInt(sides[0]);
                side2 = Integer.parseInt(sides[1]);
                side3 = Integer.parseInt(sides[2]);
                surface1 = side1 * side2;
                surface2 = side1 * side3;
                surface3 = side2 * side3;
                totalSurface = totalSurface + (surface1 + surface2 + surface3) * 2 + Math.min(surface1, Math.min(surface2, surface3));
                ribbonLength = ribbonLength + 2 * (side1 + side2 + side3 - Math.max(Math.max(side1, side2), side3)) + side1 * side2 * side3;
            }
            System.out.println("Surface: " + totalSurface);
            System.out.println("Ribbon length: " + ribbonLength);
            scanner.close();
        } catch (FileNotFoundException ex) {}
    }
    
    public static void Day_1(){
        try {
            System.out.println("Day1");
            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "\\Day1Input.txt"));
            String text = scanner.next();
            scanner.close();
            int floor = 0;
            int i = 0;
            int length = text.length();
            boolean found = true;
            while(i < length){
                if(text.charAt(i) == '('){
                    floor = floor + 1;
                }
                else{
                    floor = floor - 1;
                }
                if(floor == -1 && found){
                    System.out.println("Position:" + (i + 1));
                    found = false;
                }
                i = i + 1;
            }
            System.out.println("Floors: " + floor);
        } catch (FileNotFoundException ex) {}
    }
}
