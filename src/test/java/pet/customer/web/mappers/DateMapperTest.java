package pet.customer.web.mappers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DateMapperTest {

    @Autowired
    DateMapper dateMapper;

    @Test
    void asOffsetDateTime() {
        Timestamp ts  = Timestamp.valueOf(LocalDateTime.now());
        OffsetDateTime odt = OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);

        OffsetDateTime offsetDateTime = dateMapper.asOffsetDateTime(ts);
        Assert.assertEquals(odt, offsetDateTime);
    }

    @Test
    void asTimestamp() {
        OffsetDateTime odt = OffsetDateTime.now();
        Timestamp ts = Timestamp.valueOf(odt.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());

        Timestamp timestamp = dateMapper.asTimestamp(odt);
        assertEquals(ts,timestamp);
    }
}