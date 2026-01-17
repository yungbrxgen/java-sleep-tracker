package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {

    private SleepingSession createSession(String start, String end, Quality quality) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        return new SleepingSession(
                LocalDateTime.parse(start, formatter),
                LocalDateTime.parse(end, formatter),
                quality
        );
    }

    @Test
    public void shouldBeReturnMaxDuration() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD),
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD),
                createSession("03.12.25 23:00", "04.12.25 05:40", Quality.NORMAL)
        );

        SleepDurationMax durationMax = new SleepDurationMax();
        SleepAnalysisResult result = durationMax.apply(sessions);
        String expected = "Максимальная продолжительность сна (мин): 500";
        assertEquals(expected, result.toString());
    }

    @Test
    public void shouldBeReturnMaxDurationIfOnlyOneSession() {
        List<SleepingSession> sessions = List.of(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD)
        );

        SleepDurationMax durationMax = new SleepDurationMax();
        SleepAnalysisResult result = durationMax.apply(sessions);
        String expected = "Максимальная продолжительность сна (мин): 500";
        assertEquals(expected, result.toString());
    }

    @Test
    public void shouldBeReturnMinDuration() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD),
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD),
                createSession("03.12.25 23:00", "04.12.25 05:40", Quality.NORMAL)
        );

        SleepDurationMin durationMin = new SleepDurationMin();
        SleepAnalysisResult result = durationMin.apply(sessions);
        String expected = "Минимальная продолжительность сна (мин): 300";
        assertEquals(expected, result.toString());
    }

    @Test
    public void shouldBeReturnMinDurationIfOnlyOneSession() {
        List<SleepingSession> sessions = List.of(
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD)
        );

        SleepDurationMin durationMin = new SleepDurationMin();
        SleepAnalysisResult result = durationMin.apply(sessions);
        String expected = "Минимальная продолжительность сна (мин): 300";
        assertEquals(expected, result.toString());
    }

    @Test
    public void totalSleepingFunctionTest() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD),
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD),
                createSession("03.12.25 23:00", "04.12.25 05:40", Quality.NORMAL)
        );

        TotalSleepingFunction totalSleepingFunction = new TotalSleepingFunction();
        SleepAnalysisResult result = totalSleepingFunction.apply(sessions);
        String expected = "Общее количество сессий сна: 3";
        assertEquals(expected, result.toString());
    }

    @Test
    public void totalSleepingFunctionEmptyList() {
        List<SleepingSession> sessions = Arrays.asList();

        TotalSleepingFunction totalSleepingFunction = new TotalSleepingFunction();
        SleepAnalysisResult result = totalSleepingFunction.apply(sessions);
        String expected = "Список сессий сна, пока что, пуст: 0";
        assertEquals(expected, result.toString());
    }

    @Test
    public void sleepDurationAverageTest() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD),
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD),
                createSession("03.12.25 23:00", "04.12.25 05:40", Quality.NORMAL)
        );

        SleepDurationAverage sleepDurationAverage = new SleepDurationAverage();
        SleepAnalysisResult result = sleepDurationAverage.apply(sessions);
        String expected = "Средняя продолжительность сна (мин): 400";
        assertEquals(expected, result.toString());
    }

    @Test
    public void sleepDurationAverageEmptyList() {
        List<SleepingSession> sessions = List.of();

        SleepDurationAverage sleepDurationAverage = new SleepDurationAverage();
        SleepAnalysisResult result = sleepDurationAverage.apply(sessions);
        String expected = "Средняя продолжительность сна (мин): 0";
        assertEquals(expected, result.toString());
    }

    @Test
    public void badSleepingSessionsCountTest() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 09:20", Quality.BAD),
                createSession("03.12.25 07:00", "03.12.25 13:20", Quality.BAD),
                createSession("04.12.25 11:00", "04.12.25 16:40", Quality.NORMAL)
        );

        BadSleepingSessions badSleepingSessions = new BadSleepingSessions();
        SleepAnalysisResult result = badSleepingSessions.apply(sessions);
        String expected = "Количество бессонных ночей: 2";
        assertEquals(expected, result.toString());
    }

    @Test
    public void badSleepingSessionsCountIsNull() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:00", "02.12.25 07:20", Quality.GOOD),
                createSession("02.12.25 23:00", "03.12.25 04:00", Quality.BAD),
                createSession("03.12.25 23:00", "04.12.25 05:40", Quality.NORMAL)
        );

        BadSleepingSessions badSleepingSessions = new BadSleepingSessions();
        SleepAnalysisResult result = badSleepingSessions.apply(sessions);
        String expected = "Количество бессонных ночей: 0";
        assertEquals(expected, result.toString());
    }

    @Test
    public void badSleepingSessionsDifferentMonths() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("31.12.25 23:00", "01.01.26 09:20", Quality.BAD),
                createSession("02.01.26 07:00", "02.01.26 13:20", Quality.BAD),
                createSession("03.01.26 11:00", "03.01.26 16:40", Quality.NORMAL)
        );

        BadSleepingSessions badSleepingSessions = new BadSleepingSessions();
        SleepAnalysisResult result = badSleepingSessions.apply(sessions);
        String expected = "Количество бессонных ночей: 2";
        assertEquals(expected, result.toString());
    }

    @Test
    public void badSleepingSessionsEmptyFile() {
        List<SleepingSession> sessions = List.of();

        BadSleepingSessions badSleepingSessions = new BadSleepingSessions();
        SleepAnalysisResult result = badSleepingSessions.apply(sessions);
        String expected = "Количество бессонных ночей: 0";
        assertEquals(expected, result.toString());
    }



    @Test
    public void sleepingChronotypeTestOwl() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:30", "02.12.25 09:20", Quality.GOOD),
                createSession("02.12.25 23:50", "03.12.25 10:00", Quality.GOOD)
        );

        SleepingChronotype sleepingChronotype = new SleepingChronotype();
        SleepAnalysisResult result = sleepingChronotype.apply(sessions);
        String expected = "Ваш хронотип: Сова";
        assertEquals(expected, result.toString());

    }

    @Test
    public void sleepingChronotypePigeonIfEvenly() {
        List<SleepingSession> sessions = Arrays.asList(
                createSession("01.12.25 23:30", "02.12.25 09:20", Quality.GOOD),
                createSession("02.12.25 23:50", "03.12.25 10:00", Quality.NORMAL),
                createSession("03.12.25 22:30", "04.12.25 06:20", Quality.GOOD),
                createSession("04.12.25 22:00", "05.12.25 06:50", Quality.BAD)
        );

        SleepingChronotype sleepingChronotype = new SleepingChronotype();
        SleepAnalysisResult result = sleepingChronotype.apply(sessions);
        String expected = "Ваш хронотип: Голубь";
        assertEquals(expected, result.toString());

    }
}