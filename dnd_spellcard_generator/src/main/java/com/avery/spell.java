package com.avery;

import java.util.ArrayList;

import com.avery.spell_Attributes.magic_school;
import com.avery.spell_Attributes.player_class;

public class spell {
    public String name;
    public int level;
    public ArrayList<player_class> classes;
    public magic_school school;
    public String casting_time;
    public String range;
    public String components;
    public boolean hasMaterialCom;
    public String materialCom;
    public String duration;
    public String spellDescription;


    public spell(String name){
        this.name = name;
    }
    
}
