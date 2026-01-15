package ru.yandex.practicum.sleeptracker;

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

<<<<<<< HEAD
        Long average = (long) averageDuration;
=======
        Long average = (long)averageDuration;
>>>>>>> 9ec989a85540a4400da84abb01b64fc97a899b4f
        return new SleepAnalysisResult("Средняя продолжительность сна (мин)", average);
    }
}
