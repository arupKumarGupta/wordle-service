package com.arup.wordle.services;

import com.arup.wordle.LetterPlacements;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordleEngine {
    public List<LetterPlacements> evaluateWord(String input, String target) {
        List<LetterPlacements> placements = new ArrayList<>(target.length());
        for(int i = 0; i < 5; i ++) {
            placements.add(null);
        }
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(char c : target.toCharArray()) {
            int freq = frequencyMap.getOrDefault(c, 0) ;
            frequencyMap.put(c, freq + 1);
        }

        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(target.charAt(i) == c) {
                placements.set(i,LetterPlacements.CORRECT);
                frequencyMap.put(c, frequencyMap.get(c) - 1);
            }
        }
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(placements.get(i) == LetterPlacements.CORRECT) {
                continue;
            }
            if(!frequencyMap.containsKey(c) || frequencyMap.get(c) == 0) {
                placements.set(i,LetterPlacements.NOT_IN_WORD);
            } else {
                placements.set(i,LetterPlacements.WRONG_PLACE);
                frequencyMap.put(c, frequencyMap.get(c) - 1);
            }
        }

        return placements;
    }
}
