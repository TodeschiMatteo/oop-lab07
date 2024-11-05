package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY("January", 31),
        FEBRUARY("February", 28),
        MARCH("March", 31),
        APRIL("April", 30),
        MAY("May", 31),
        JUNE("June", 30),
        JULY("July", 31),
        AUGUST("August", 31),
        SEPTEMBER("September", 30),
        OCTOBER("October", 31),
        NOVEMBER("November", 30),
        DECEMBER("December", 31);

        private final String monthName;
        private final int monthDays;

        Month(String monthName, int monthDays) {
            this.monthName = monthName;
            this.monthDays = monthDays;
        }

        public String getMonthName() {
            return monthName;
        }

        public int getMonthDays() {
            return monthDays;
        }

        public static Month fromString(String name){ 
            Month searched = null;
            for (final Month month: Month.values()) {
                var tolowerName = month.toString().toLowerCase();
                if(tolowerName.startsWith(name.toLowerCase())){
                    if(searched == null){
                        searched = month;
                    } else {
                        throw new IllegalArgumentException("Abiguity detected");
                    }
                }
            }
            if (searched == null){
                throw new IllegalArgumentException("Month not detected");
            }
            return searched;
        }
    }
        

    @Override
    public Comparator<String> sortByDays(){
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                final var m1 = Month.fromString(s1);
                final var m2 = Month.fromString(s2);
                return Integer.compare(m1.monthDays, m2.monthDays);
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                return Month.fromString(s1).compareTo(Month.fromString(s2));
            }
        };
    }
}

