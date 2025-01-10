package com.avery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            //initialise files
            File spellCardsFile = new File("Spell Descriptions - Player’s Handbook - Dungeons & Dragons - Sources - D&D Beyond.html");
            if (!spellCardsFile.exists()){
                throw new FileNotFoundException();
            }

            //initialise required readers and Scanners.
            BufferedReader reader = new BufferedReader(new FileReader(spellCardsFile));
            //buffer for testing purposes
            Scanner buffer = new Scanner(System.in);
            
            //the line indicating where the start of the spells are
            String startLineIndicator = "The spells are presented in alphabetical order";
            
            //variable initialisation
            int lines = 0;
            String nextLine;
            ArrayList<String> fileLines = new ArrayList<>();

            //Iterate through each line and add it to fileLines. If it's too long of a line, then skip it.
            while ((nextLine = reader.readLine())!= null){
                lines++;
                System.out.println(lines + "  "+nextLine.length());
                if (nextLine.length()> 50000){
                    continue;
                }
                fileLines.add(nextLine);
            }
            System.out.println("outofwhileloop");
            System.out.println(fileLines.size());


            //close necessary readers and buffers.
            reader.close();
            buffer.close();

        } 
        catch (FileNotFoundException e){
            System.out.println("mcsussy");

        }
        catch(IOException e){
            
        }
    }
}