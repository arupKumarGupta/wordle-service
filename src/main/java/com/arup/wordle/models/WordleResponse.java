package com.arup.wordle.models;

import com.arup.wordle.LetterPlacements;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class WordleResponse {
    private boolean isMatch;
    private String value;
    private boolean isValid;
    private List<LetterPlacements> letterPlacements;
}
