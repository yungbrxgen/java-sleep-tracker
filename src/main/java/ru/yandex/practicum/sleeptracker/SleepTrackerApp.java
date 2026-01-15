package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleepTrackerApp {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private final List<Function<List<SleepingSession>, SleepAnalysisResult>> analysisFunctions = List.of(
            new TotalSleepingFunction(),
            new SleepDurationMax(),
            new SleepDurationMin(),
            new SleepDurationAverage(),
            new BadSleepingSessions(),
            new SleepingChronotype()
    );

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Ошибка: Не указан путь к файлу.");
            return;
        }

        String path = args[0];
        SleepTrackerApp app = new SleepTrackerApp();
        List<SleepingSession> sessions = app.loadSession(args[0]);

        app.analysisFunctions.stream()
                .map(function -> function.apply(sessions))
                .forEach((System.out::println));
    }
<<<<<<< HEAD

    List<SleepingSession> loadSession(String path) {

        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        String[] splitLine = line.split(";");
                        LocalDateTime start = LocalDateTime.parse(splitLine[0], FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(splitLine[1], FORMATTER);
                        Quality type = Quality.valueOf(splitLine[2]);
                        return new SleepingSession(start, end, type);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return Collections.emptyList();
        }
=======
        List<SleepingSession> loadSession(String path) {

            try (Stream<String> lines = Files.lines(Path.of(path))) {
                 return lines
                         .filter(line -> !line.isBlank())
                .map(line -> {
                 String[] splitLine = line.split(";");
                     LocalDateTime start = LocalDateTime.parse(splitLine[0], FORMATTER);
                     LocalDateTime end  = LocalDateTime.parse(splitLine[1], FORMATTER);
                     Quality type = Quality.valueOf(splitLine[2]);
                     return new SleepingSession(start, end, type);
                }).collect(Collectors.toList());
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
                return Collections.emptyList();
            }
>>>>>>> 9ec989a85540a4400da84abb01b64fc97a899b4f
    }
}