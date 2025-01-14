package com.avery;

import java.util.ArrayList;

public class utils {
    static String removeTags(String taggedString){
        char[] chars = taggedString.toCharArray();
        String result = "";
        int depth = 0;
        for (int i = 0;i < chars.length;i++){
            if (chars[i]=='<'){
                depth++;
            }
            else if (chars[i]=='>'){
                depth--;
            }
            else if (depth == 0) {
                result = result + chars[i];
            }
        }
        
        return result;
    } 
}
