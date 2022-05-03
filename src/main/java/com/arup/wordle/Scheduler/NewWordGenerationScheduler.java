package com.arup.wordle.Scheduler;

import com.arup.wordle.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewWordGenerationScheduler {

    @Autowired
    private WordService wordService;

    @Scheduled(cron="0 0 0 * * *")
    public void generateNewWordOfTheDay() {
        wordService.renewWordOfTheDay();
    }
}
