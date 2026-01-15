package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SleepingChronotype implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Map<String, Long> counts = sleepingSessions.stream()
                .filter(this::isNightSession)
                .map(this::determineType)
                .collect(Collectors.groupingBy(type -> type, Collectors.counting()));

        long owls = counts.getOrDefault("Сова", 0L);
        long larks = counts.getOrDefault("Жаворонок", 0L);
        long pigeons = counts.getOrDefault("Голубь", 0L);

        String result;
        if (owls > pigeons && owls >= larks) {
            result = "Сова";
        } else if (larks > pigeons && pigeons > owls) {
            result = "Жаворонок";
        } else {
            result = "Голубь";
        }

        return new SleepAnalysisResult("Ваш хронотип", result);
    }

    private boolean isNightSession(SleepingSession s) {
        LocalDate dateOfWakeUp = s.getEnd().toLocalDate();
        LocalDateTime nightStart = dateOfWakeUp.atStartOfDay();
        LocalDateTime nightEnd = dateOfWakeUp.atTime(6, 0);

        return s.getStart().isBefore(nightEnd) && s.getEnd().isAfter(nightStart);
    }

    private String determineType(SleepingSession s) {
        LocalTime bedTime = s.getStart().toLocalTime();
        LocalTime wakeTime = s.getEnd().toLocalTime();

        if (bedTime.isAfter(LocalTime.of(23, 0)) && wakeTime.isAfter(LocalTime.of(9, 0))) {
            return "Сова";
<<<<<<< HEAD
        } else if (bedTime.isBefore(LocalTime.of(22, 0)) && wakeTime.isBefore(LocalTime.of(7, 0))) {
=======
        } else if (bedTime.isBefore(LocalTime.of(22,0)) && wakeTime.isBefore(LocalTime.of(7,0))) {
>>>>>>> 9ec989a85540a4400da84abb01b64fc97a899b4f
            return "Жаворонок";
        } else {
            return "Голубь";
        }
    }
}
