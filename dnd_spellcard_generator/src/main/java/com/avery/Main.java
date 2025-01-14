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
            
            //the lines indicating where the start and end of the spells are
            String startLineIndicator = "The spells are presented in alphabetical order";
            String endLineIndicator = "<!-- FOOTER -->";
            String spellSeparationIndicator = "<hr class=\"separator\">";
            String spellNewLetterIndicator = "compendium-hr heading-anchor\" id=\"Spells";

            
            //variable initialisation
            int lines = 0;
            String nextLine;
            ArrayList<String> fileLines = new ArrayList<>();

            //Iterate through each line and add it to fileLines. If it's too long of a line, then skip it.
            while ((nextLine = reader.readLine())!= null){
                lines++;
                // System.out.println(lines + "  "+nextLine.length());
                if (nextLine.length()> 50000){
                    continue;
                }
                fileLines.add(nextLine);
            }
            System.out.println("outofwhileloop");
            System.out.println(fileLines.size());


            ArrayList<ArrayList<String>> spellsAsLists = new ArrayList<>();

            //iterate through and find what lines the spells start and end
            int currentIdx = 0;
            while (true){
                if (fileLines.get(currentIdx).contains(startLineIndicator)){
                    currentIdx+=2;
                    break;
                }
                currentIdx++;
            }

            // System.out.println(currentIdx);
            
            //separating them by line indicator, then putting them in a 2d array
            for (int i = currentIdx;i < fileLines.size();i++){
                ArrayList<String> tempSpell = new ArrayList<>();
                while (!fileLines.get(i).contains(endLineIndicator) && !fileLines.get(i).contains(spellSeparationIndicator) && !fileLines.get(i).contains(spellNewLetterIndicator)){
                    
                    tempSpell.add(fileLines.get(i));
                    i++;
                    // System.out.println(i);
                }
                // System.out.println("outttttt");
                spellsAsLists.add(tempSpell);
                if (fileLines.get(i).contains(endLineIndicator)){
                    break;
                }
            }
            System.out.println(spellsAsLists.size());
            // for (int j = 0; j < spellsAsLists.size();j++){
            //     System.out.println(j + " " + spellsAsLists.get(j).size());
            // }
            // System.out.println(String.join("\n", spellsAsLists.get(25)));
            ArrayList<String> test = spellsAsLists.get(1);
            for (int k = 0;k < test.size();k++){
                System.out.println(utils.removeTags(test.get(k)));
            }
            System.out.println("\n");
            System.out.println(utils.removeTags(spellsAsLists.get(24).get(0)));

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