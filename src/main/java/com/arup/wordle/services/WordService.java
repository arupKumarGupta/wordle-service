package com.arup.wordle.services;

import com.arup.wordle.LetterPlacements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WordService {
    public static String wordOfTheDay;
    @Autowired
    private WordRepoService wordEngine;

    @Autowired
    private WordleEngine wordleEngine;

    WordService(@Autowired WordRepoService wordEngine) {
        this.wordEngine = wordEngine;
        wordOfTheDay = wordEngine.getWordFromDictionary();
    }

    public void renewWordOfTheDay() {
        wordOfTheDay = wordEngine.getWordFromDictionary();
    }



    public String getWordOfTheDay() {
        return wordOfTheDay;
    }

    public boolean isValidWord(String word) {
        return wordEngine.isWordValid(word);

    }

    public List<LetterPlacements> checkWord(String word) {
       if(word.equalsIgnoreCase(wordOfTheDay)) {
           return Arrays.asList(LetterPlacements.CORRECT,LetterPlacements.CORRECT,LetterPlacements.CORRECT,LetterPlacements.CORRECT,LetterPlacements.CORRECT);
       }
       return wordleEngine.evaluateWord(word, wordOfTheDay);
    }

    public boolean isMatch(List<LetterPlacements> placements) {
        return placements.stream().filter(p -> p == LetterPlacements.CORRECT).count() == wordOfTheDay.length();
    }

}
