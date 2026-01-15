package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SleepDurationMax implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long maxDuration = sleepingSessions.stream()
                .mapToLong(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .max()
                .orElse(0);

        return new SleepAnalysisResult("Максимальная продолжительность сна (мин)", maxDuration);
    }
}
