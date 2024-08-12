package vn.unigap.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


public class Common {

    // Convert string into a set
    public static Set<Long> convertStringToSet(String str) {
        return Arrays.stream(str.split("[-_, ]"))
                .filter(s -> !s.isEmpty())      // Convert fields type
                .map(Long::parseLong)           // to implement into
                .collect(Collectors.toSet());
    }

    // Get current local time and convert into Date type
    public static Date currentDateTime() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
    }

    // Convert Date to String with date's format
    public static String formatBirthday(Date birthday) {
        LocalDate localDateBirthday = birthday.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDateBirthday);
    }

}
