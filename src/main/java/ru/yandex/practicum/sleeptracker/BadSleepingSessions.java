package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

public class BadSleepingSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        LocalDateTime firstSession = sleepingSessions.stream()
                .map(SleepingSession::getStart)
                .min(LocalDateTime::compareTo)
                .get();

        LocalDateTime lastSession = sleepingSessions.stream()
                .map(SleepingSession::getEnd)
                .max(LocalDateTime::compareTo)
                .get();

        LocalDate firstNightDate;
        if (firstSession.getHour() < 12) {
            firstNightDate = firstSession.toLocalDate();
        } else {
            firstNightDate = firstSession.toLocalDate().plusDays(1);
        }

        LocalDate lastNightDate = lastSession.toLocalDate();

        long totalDaysToCheck = ChronoUnit.DAYS.between(firstNightDate, lastNightDate) + 1;

        long sleeplessCount = LongStream.range(0, totalDaysToCheck)
                .mapToObj(i -> firstNightDate.plusDays(i))
                .filter(currentDate -> {
                        LocalDateTime nightStart = currentDate.atStartOfDay();
                        LocalDateTime nightEnd = currentDate.atTime(6, 0);
                        boolean hasNightSleep = sleepingSessions.stream()
                                .anyMatch(session ->
                                session.getStart().isBefore(nightEnd) && session.getEnd().isAfter(nightStart)
                                );
                        return  !hasNightSleep;
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessCount);
    }
}
