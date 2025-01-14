package com.avery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.avery.spell_Attributes.char_class;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    static int SPELL_MAX_LEVEL = 9;
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
            Scanner inputScanner = new Scanner(System.in);
            
            //the lines indicating where the start and end of the spells are
            String startLineIndicator = "The spells are presented in alphabetical order";
            String endLineIndicator = "<!-- FOOTER -->";
            String spellSeparationIndicator = "<hr class=\"separator\">";
            String spellNewLetterIndicator = "compendium-hr heading-anchor\" id=\"Spells";

            //all spells
            ArrayList<spell> allSpells = new ArrayList<>();

            
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
            
            //separating them by line indicator, then putting them in a 2d array
            for (int i = currentIdx;i < fileLines.size();i++){
                ArrayList<String> tempSpell = new ArrayList<>();
                while (!fileLines.get(i).contains(endLineIndicator) && !fileLines.get(i).contains(spellSeparationIndicator) && !fileLines.get(i).contains(spellNewLetterIndicator)){
                    
                    tempSpell.add(fileLines.get(i));
                    i++;
                }
                spellsAsLists.add(tempSpell);
                if (fileLines.get(i).contains(endLineIndicator)){
                    break;
                }
            }

            //testing code
            System.out.println(spellsAsLists.size());
            // for (int j = 0; j < spellsAsLists.size();j++){
            //     System.out.println(j + " " + spellsAsLists.get(j).size());
            // }
            // System.out.println(String.join("\n", spellsAsLists.get(25)));
            ArrayList<String> test = spellsAsLists.get(205);
            for (int k = 0;k < test.size();k++){
                System.out.println(utils.removeTags(test.get(k)));
            }
            System.out.println("\n");
            System.out.println(utils.removeTags(spellsAsLists.get(24).get(0)));

            spell tempspell = new spell(test);
            System.out.println("\n");
            System.out.println(tempspell);



            //turning the spells as lists into spell objects.
            for (int i = 1;i<spellsAsLists.size();i++){
                // System.out.println(i);
                if (spellsAsLists.get(i).size() > 8){
                    allSpells.add(new spell(spellsAsLists.get(i)));
                }
            }


            //filter spells
            System.out.println("What class do you want spells for");
            char_class[] classes = char_class.values();
            for (int i = 0; i < classes.length;i++){
                System.out.println(i +": "+ classes[i]);
            }

            //get a valid class
            int chosenClassIdx = -1;
            while (chosenClassIdx < 0 || chosenClassIdx > classes.length){
                System.out.println("input a number between 0 and "+classes.length);
                String inp = inputScanner.nextLine();
                try{
                    chosenClassIdx = Integer.parseInt(inp);
                }finally{
                    
                }
            }
            char_class chosen_class = classes[chosenClassIdx];

            //get a valid range of levels
            int chosenLowestLevel = -1;
            int chosenHighestLevel = -1;

            while (chosenLowestLevel < 0 || chosenLowestLevel > SPELL_MAX_LEVEL){
                System.out.println("what's the lowest spell level you want? 0-"+SPELL_MAX_LEVEL);
                String inp = inputScanner.nextLine();
                try{
                    chosenLowestLevel = Integer.parseInt(inp);
                }finally{
                    
                }
            }
            
            while (chosenHighestLevel < chosenLowestLevel || chosenHighestLevel > SPELL_MAX_LEVEL){
                System.out.println("what's the highest spell level you want? "+chosenLowestLevel + " - " +SPELL_MAX_LEVEL);
                String inp = inputScanner.nextLine();
                try{
                    chosenHighestLevel = Integer.parseInt(inp);
                }finally{
                    
                }
            }

            System.out.println("\n"+chosen_class+"\n"+chosenLowestLevel+"\n"+chosenHighestLevel);


            //2d array for spells of different levels
            ArrayList<ArrayList<spell>> spellsByLevel = new ArrayList<>();
            for (int i = 0; i < SPELL_MAX_LEVEL; i++){
                ArrayList<spell> tempLevelSpells = new ArrayList<>(); 
                
                if (i>=chosenLowestLevel && i <= chosenHighestLevel){
                    for (spell tSpell : allSpells){
                        if (tSpell.filterSpell(chosen_class, i)){
                            tempLevelSpells.add(tSpell);
                        }
                    }
                }

                spellsByLevel.add(tempLevelSpells);
            }

            for (ArrayList<spell> tsp:spellsByLevel){
                System.out.println(tsp);
            }


            //close necessary readers and buffers.
            reader.close();
            inputScanner.close();

        } 
        catch (FileNotFoundException e){
            System.out.println("mcsussy");

        }
        catch(IOException e){
            
        }
    }
}