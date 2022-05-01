package com.arup.wordle.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordRepoService {

    private String alphabets = "abcdefghijklmnopqrstuvwxyz";
    private final Map<String, List<String>> wordsMap = new HashMap<>();
    WordRepoService() {
        ObjectMapper mapper = new ObjectMapper();
        MapType type = mapper.getTypeFactory().constructMapType(
                Map.class, String.class, Object.class);
        try {
            Map<String, List<String>> data = mapper.readValue(new FileReader("src/main/resources/data/five-letters.json"), type);
            wordsMap.putAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getWordFromDictionary() {
        List<String> words =  wordsMap.get(alphabets.charAt( (int)((Math.random() * 100)) % 26) + "");
        return words.get((int)(Math.random() * 100) % words.size());
    }

    public boolean isWordValid(String word) {
        List<String> searchSpace = wordsMap.get(word.charAt(0) + "");
        int start = 0;
        int end = searchSpace.size() - 1;
        while(start <= end) {
            int mid = start + (end - start) /2;
            if(searchSpace.get(mid).equals(word)) {
                return true;
            }
            if(searchSpace.get(mid).compareToIgnoreCase(word) > 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
