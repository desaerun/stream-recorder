package net.desaerun.streamrecorder.ffmpeg.config;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecordingSchedule implements Serializable {
    private final Map<DayOfWeek, Boolean[]> schedule = new HashMap<>(7);

    public RecordingSchedule() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            schedule.put(dayOfWeek, new Boolean[24]);
        }
    }

    public RecordingSchedule enableForDay(DayOfWeek day) {
        Arrays.fill(schedule.get(day), true);

        return this;
    }

    public RecordingSchedule disableForDay(DayOfWeek day) {
        Arrays.fill(schedule.get(day), false);

        return this;
    }

    public RecordingSchedule enableForRange(DayOfWeek startDay, int startHour, DayOfWeek endDay, int endHour) {
        boolean isEnabledDay = false;
        for (Map.Entry<DayOfWeek, Boolean[]> dayOfWeekEntry : schedule.entrySet()) {
            DayOfWeek dayOfWeek = dayOfWeekEntry.getKey();
            if (dayOfWeek == startDay) {
                isEnabledDay = true;
            }
            if (isEnabledDay) {
                boolean isEnabledHour = false;
                for (int i = 0; i < 24; i++) {
                    if (i == startHour) {
                        isEnabledHour = true;
                    }

                    if (isEnabledHour) {
                        dayOfWeekEntry.getValue()[i] = true;
                    }

                    if (i == endHour) {
                        isEnabledHour = false;
                    }
                }
            }

            if (dayOfWeek == endDay) {
                isEnabledDay = false;
            }
        }

        return this;
    }

    public RecordingSchedule disableForRange(DayOfWeek startDay, int startHour, DayOfWeek endDay, int endHour) {
        boolean isDisabledDay = false;
        for (Map.Entry<DayOfWeek, Boolean[]> dayOfWeekEntry : schedule.entrySet()) {
            DayOfWeek dayOfWeek = dayOfWeekEntry.getKey();
            if (dayOfWeek == startDay) {
                isDisabledDay = true;
            }

            if (isDisabledDay) {
                boolean isDisabledHour = false;
                for (int i = 0; i < 24; i++) {
                    if (i == startHour) {
                        isDisabledHour = true;
                    }

                    if (isDisabledHour) {
                        dayOfWeekEntry.getValue()[i] = false;
                    }

                    if (i == endHour) {
                        isDisabledHour = false;
                    }
                }
            }

            if (dayOfWeek == endDay) {
                isDisabledDay = false;
            }
        }

        return this;
    }

    public RecordingSchedule weekdays() {
        for (Map.Entry<DayOfWeek, Boolean[]> scheduleByDay : schedule.entrySet()) {
            DayOfWeek dayOfWeek = scheduleByDay.getKey();
            switch (dayOfWeek) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                    Boolean[] toggles = scheduleByDay.getValue();
                    Arrays.fill(toggles, true);
                }
            }
        }

        return this;
    }

    public RecordingSchedule weekdaysOnly() {
        for (Map.Entry<DayOfWeek, Boolean[]> scheduleByDay : schedule.entrySet()) {
            DayOfWeek dayOfWeek = scheduleByDay.getKey();
            Boolean[] toggles = scheduleByDay.getValue();
            switch (dayOfWeek) {
                case SUNDAY, SATURDAY -> {
                    Arrays.fill(toggles, false);
                }
                default -> {
                    Arrays.fill(toggles, true);
                }
            }
        }

        return this;
    }

    public RecordingSchedule weekends() {
        for (Map.Entry<DayOfWeek, Boolean[]> scheduleByDay : schedule.entrySet()) {
            DayOfWeek dayOfWeek = scheduleByDay.getKey();
            switch (dayOfWeek) {
                case SATURDAY, SUNDAY -> {
                    Boolean[] toggles = scheduleByDay.getValue();
                    Arrays.fill(toggles, true);
                }
            }
        }

        return this;
    }

    public RecordingSchedule weekendsOnly() {
        for (Map.Entry<DayOfWeek, Boolean[]> scheduleByDay : schedule.entrySet()) {
            DayOfWeek dayOfWeek = scheduleByDay.getKey();
            Boolean[] toggles = scheduleByDay.getValue();
            switch (dayOfWeek) {
                case SUNDAY, SATURDAY -> {
                    Arrays.fill(toggles, true);
                }
                default -> {
                    Arrays.fill(toggles, false);
                }
            }
        }

        return this;
    }

}
