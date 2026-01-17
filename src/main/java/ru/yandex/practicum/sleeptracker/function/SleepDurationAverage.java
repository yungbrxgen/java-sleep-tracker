package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.time.Duration;
import java.util.function.Function;

public class SleepDurationAverage implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        double averageDuration = sleepingSessions.stream()
                .mapToLong(s -> Duration.between(s.getStart(), s.getEnd()).toMinutes())
                .average()
                .orElse(0);


        Long average = (long) averageDuration;


        return new SleepAnalysisResult("Средняя продолжительность сна (мин)", average);
    }
}
