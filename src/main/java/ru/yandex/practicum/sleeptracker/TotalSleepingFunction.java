package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class TotalSleepingFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Список сессий сна, пока что, пуст", 0);
        }

        int count = sleepingSessions.size();
        return new SleepAnalysisResult("Общее количество сессий сна", count);
    }
}
