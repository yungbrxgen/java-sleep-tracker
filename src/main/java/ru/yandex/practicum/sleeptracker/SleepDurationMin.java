package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.time.Duration;
import java.util.function.Function;

public class SleepDurationMin implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long minDuration = sleepingSessions.stream()
                .mapToLong(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .min()
                .orElse(0);

        return new SleepAnalysisResult("Минимальная продолжительность сна (мин)", minDuration);
    }
}
