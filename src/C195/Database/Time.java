package C195.Database;

import java.time.*;
import java.util.TimeZone;

public class Time {
    static LocalDate localdate = LocalDate.now();
    static ZoneId estID = ZoneId.of("America/New_York");
    static ZoneId localZone = ZoneId.of(TimeZone.getDefault().getID());

    /**
     * This method returns the start of business hours
     * @return business hours
     */
    public static LocalTime getLocalStartTime(){
        LocalTime startTime = LocalTime.of( 8, 0 );
        ZonedDateTime startEst = ZonedDateTime.of(localdate, startTime, estID);
        ZonedDateTime startLocal = startEst.withZoneSameInstant(localZone);

        return startLocal.toLocalTime();
    }

    /**
     * The method returns the end of business hours
     * @return business hours
     */
    public static LocalTime getLocalEndTime(){
        LocalTime endTime = LocalTime.of(22, 0 );
        ZonedDateTime endEst = ZonedDateTime.of(localdate, endTime, estID);
        ZonedDateTime endLocal = endEst.withZoneSameInstant(localZone);

        return endLocal.toLocalTime();
    }

}
