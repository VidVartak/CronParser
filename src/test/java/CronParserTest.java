import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CronParserTest {
    @Test
    void test1() {
        Assertions.assertEquals("minute         0 15 30 45\n" +
                "hour           0\n" +
                "day of month   1 15\n" +
                "month          1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week    1 2 3 4 5\n" +
                "command        /usr/bin/find blah blah blah\n" , CronParser.parseCron("*/15 0 1,15 * 1-5 /usr/bin/find blah blah blah"));
    }

    @Test
    void test2() {
        Assertions.assertEquals("minute         0 10 20 30 40 50\n" +
                "hour           0\n" +
                "day of month   1 15\n" +
                "month          1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week    1 2 3 4 5\n" +
                "command        /usr/bin/find blah blah blah\n" , CronParser.parseCron("*/10 0 1,15 * 1-5 /usr/bin/find blah blah blah"));
    }

    @Test
    void test3() {
        Assertions.assertThrows(RuntimeException.class, ()->CronParser.parseCron("*/10 0 1,15 * 1-15 /usr/bin/find blah blah blah"));
    }

}
