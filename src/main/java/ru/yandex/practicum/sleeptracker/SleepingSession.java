package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime start;
    private LocalDateTime end;
    private Quality type;

    public SleepingSession(LocalDateTime start, LocalDateTime end, Quality type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
