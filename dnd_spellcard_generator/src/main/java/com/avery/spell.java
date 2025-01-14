package com.avery;

import java.util.ArrayList;

import com.avery.spell_Attributes.magic_school;
import com.avery.spell_Attributes.char_class;

public class spell {
    public String name;
    public int level;
    public ArrayList<char_class> classes;
    public magic_school school;
    public String casting_time;
    public String range;
    public String components;
    public boolean hasMaterialCom;
    public String materialCom;
    public String duration;
    public String spellDescription;


    public spell(ArrayList<String> rawSpell){
        //name of the spell
        this.name = utils.removeTags(rawSpell.get(0));

        //line 2 contains the level, school of magic and classes
        String line2 = utils.removeTags(rawSpell.get(1));
        if (line2.contains("Cantrip")){
            this.level = 0;
        }
        else {
            this.level = Integer.parseInt(line2.substring(6, 7));
        }

        for (magic_school ms : magic_school.values()){
            if (line2.contains(utils.titleCase(ms.name()))){
                this.school = ms;
            }
        }

        this.classes = new ArrayList<>();

        for (char_class cclass : char_class.values()){
            if (line2.contains(utils.titleCase(cclass.name()))){
                this.classes.add(cclass);
            }
        }

        //castingtime, range, duration;
        this.casting_time = utils.removeTags(rawSpell.get(3)).substring(14);
        this.range = utils.removeTags(rawSpell.get(4)).substring(7);
        this.duration = utils.removeTags(rawSpell.get(6)).substring(10);

        //components of the spell. little extra is needed if material components are a thing.
        String compLine = utils.removeTags(rawSpell.get(5));
        this.materialCom = "";
        this.hasMaterialCom = compLine.contains("M");
        if (!this.hasMaterialCom){
            this.components = compLine.substring(12);
        }else{
            this.components = compLine.substring(12, compLine.indexOf("(")-1);
            this.materialCom = compLine.substring(compLine.indexOf("(")+1,compLine.indexOf(")"));
        }

        //description:
        this.spellDescription = String.join("<br>",rawSpell.subList(8,rawSpell.size()));


        
    }

    @Override
    public String toString(){
        return this.name+"\n"+
        this.level+"\n"+
        this.classes+"\n"+
        this.school+"\n"+
        this.casting_time+"\n"+
        this.range+"\n"+
        this.components+"\n"+
        this.hasMaterialCom+"\n"+
        this.materialCom+"\n"+
        this.duration+"\n"+
        this.spellDescription+"\n";
    }

}
