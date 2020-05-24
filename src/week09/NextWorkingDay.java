package week09;

import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        if (temporal.get(ChronoField.DAY_OF_WEEK) < 5) {
            return temporal.plus(Period.ofDays(1));
        } else {
            return temporal.plus(8 - temporal.get(ChronoField.DAY_OF_WEEK), ChronoUnit.DAYS);
        }

    }
}
