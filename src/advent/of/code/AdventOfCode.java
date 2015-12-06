package advent.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
        */
        Day_6_2();
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
    /*
    Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

For example:

If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....

Part2: Now find one that starts with six zeroes.
    */
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
    /*
    Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.

However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?

For example:

> delivers presents to 2 houses: one at the starting location, and one to the east.
^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
    
Part2:
The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.

Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.

This year, how many houses receive at least one present?

For example:

^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
    */
    public static void Day_3(){
        try {
            System.out.println("Day3");
            String text = new Scanner(new File(System.getProperty("user.dir") + "\\Day3Input.txt")).next();
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
    
    /*
    The elves are running low on wrapping paper, and so they need to submit an order for more. They have a list of the dimensions (length l, width w, and height h) of each present, and only want to order exactly as much as they need.

Fortunately, every present is a box (a perfect right rectangular prism), which makes calculating the required wrapping paper for each gift a little easier: find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l. The elves also need a little extra paper for each present: the area of the smallest side.

For example:

A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of wrapping paper plus 6 square feet of slack, for a total of 58 square feet.
A present with dimensions 1x1x10 requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper plus 1 square foot of slack, for a total of 43 square feet.
All numbers in the elves' list are in feet. How many total square feet of wrapping paper should they order?
    
Part2:
The elves are also running low on ribbon. Ribbon is all the same width, so they only have to worry about the length they need to order, which they would again like to be exact.

The ribbon required to wrap a present is the shortest distance around its sides, or the smallest perimeter of any one face. Each present also requires a bow made out of ribbon as well; the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the present. Don't ask how they tie the bow, though; they'll never tell.

For example:

A present with dimensions 2x3x4 requires 2+2+3+3 = 10 feet of ribbon to wrap the present plus 2*3*4 = 24 feet of ribbon for the bow, for a total of 34 feet.
A present with dimensions 1x1x10 requires 1+1+1+1 = 4 feet of ribbon to wrap the present plus 1*1*10 = 10 feet of ribbon for the bow, for a total of 14 feet.
How many total feet of ribbon should they order?    
    */
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
        } catch (FileNotFoundException ex) {}
    }
    
    /*
     Santa was hoping for a white Christmas, but his weather machine's "snow" function is powered by stars, and he's fresh out! To save Christmas, he needs you to collect fifty stars by December 25th.

Collect stars by helping Santa solve puzzles. Two puzzles will be made available on each day in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

Here's an easy puzzle to warm you up.

Santa is trying to deliver presents in a large apartment building, but he can't find the right floor - the directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the instructions one character at a time.

An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should go down one floor.

The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.

For example:

(()) and ()() both result in floor 0.
((( and (()(()( both result in floor 3.
))((((( also results in floor 3.
()) and ))( both result in floor -1 (the first basement level).
))) and )())()) both result in floor -3.
To what floor do the instructions take Santa?
    
Part 2:
Now, given the same instructions, find the position of the first character that causes him to enter the basement (floor -1). The first character in the instructions has position 1, the second character has position 2, and so on.

For example:

) causes him to enter the basement at character position 1.
()()) causes him to enter the basement at character position 5.
What is the position of the character that causes Santa to first enter the basement?
     */
    public static void Day_1(){
        try {
            System.out.println("Day1");
            String text = new Scanner(new File(System.getProperty("user.dir") + "\\Day1Input.txt")).next();
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
