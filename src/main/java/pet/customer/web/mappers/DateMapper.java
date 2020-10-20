package pet.customer.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by
 * @author ArtemKhodotovych on 2020-10-19.
 * @version 1.0
 *
 * date mapper impl
 */
@Component
public class DateMapper {

    /**
     * map to OffsetDateTime
     * @see DateMapper#asOffsetDateTime(Timestamp)
     * @param ts - timestamp
     * @return OffsetDateTime
     */
    public OffsetDateTime asOffsetDateTime(Timestamp ts){
        if (ts != null){
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    /**
     * map to OffsetDateTime
     * @see DateMapper#asTimestamp(OffsetDateTime)
     * @param offsetDateTime - date time offset
     * @return OffsetDateTime
     */
    public Timestamp asTimestamp(OffsetDateTime offsetDateTime){
        if(offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }

}
