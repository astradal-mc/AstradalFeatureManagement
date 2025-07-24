package net.astradal.astradalFeatureManagement.placeholders.date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LunarCalendar {
    private final LocalDate startDate;

    private static final String[] PHASE_NAMES = {
        "Irasmath",   // Full Moon (0)
        "Aldtsva",    // Waning Gibbous (1)
        "Hyanath",    // Last Quarter (2)
        "Vetiveeya",  // Waning Crescent (3)
        "Ahyaadym",   // New Moon (4)
        "Vavatisma",  // Waxing Crescent (5)
        "Kshemourn",  // First Quarter (6)
        "Yavadr"      // Waxing Gibbous (7)
    };

    private static final String[] MONTH_NAMES = {
        "Asahanãth Avareti", // 0 Full Moon - Waning
        "Asahanãth Avareti", // 1
        "Asahanãth Avareti", // 2
        "Asahanãth Avareti", // 3
        "Asahanãth Avareti", // 4 New Moon
        "Sahanãth Avareti",  // 5 Waxing Crescent
        "Sahanãth Avareti",  // 6
        "Sahanãth Avareti",  // 7
    };

    private static final int STARTING_PHASE = 5; // Waxing Crescent

    public LunarCalendar(String startDateStr) {
        this.startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
    }

    public int getDaysSinceStart() {
        return (int) ChronoUnit.DAYS.between(startDate, LocalDate.now());
    }

    public int getCurrentPhase() {
        int dayOffset = getDaysSinceStart();
        return (STARTING_PHASE + dayOffset) % 8;
    }

    public int getCurrentYear() {
        int dayOffset = getDaysSinceStart();
        return (STARTING_PHASE + dayOffset) / 8 + 1;
    }

    public String getCurrentDateString() {
        int phase = getCurrentPhase();
        int year = getCurrentYear();
        return "Year " + year + ", " + MONTH_NAMES[phase] + " - " + PHASE_NAMES[phase];
    }
}
