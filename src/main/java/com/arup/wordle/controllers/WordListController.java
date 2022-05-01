package com.arup.wordle.controllers;

import com.arup.wordle.LetterPlacements;
import com.arup.wordle.models.WordleResponse;
import com.arup.wordle.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WordListController {
    @Autowired
    private WordService wordService;

    @GetMapping(value = "/word-of-the-day" )
    public ResponseEntity<WordleResponse> wordOfTheDay() {
        String word = wordService.getWordOfTheDay();
        WordleResponse response = new WordleResponse();
        response.setValue(Base64.getEncoder().encodeToString(word.getBytes(StandardCharsets.UTF_8)));
        response.setValid(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetWordOfTheDay() {
        wordService.renewWordOfTheDay();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/submit")
    public ResponseEntity<WordleResponse> submitWord(@RequestParam("input") String input) {
        WordleResponse response = new WordleResponse();
        response.setValue(input);
        response.setValid(true);
        if(wordService.isValidWord(input)) {
            List<LetterPlacements> placements = wordService.checkWord(input);
            response.setLetterPlacements(placements);
            response.setMatch(wordService.isMatch(placements));
            return  ResponseEntity.ok(response);
        }
        response.setValid(false);
        return ResponseEntity.badRequest().body(response);
    }
}
