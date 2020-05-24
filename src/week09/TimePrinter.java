package week09;

import java.time.*;
import java.time.format.DateTimeFormatter;

// Implementation of Chapter 12 of JAVA IN ACTION
public class TimePrinter {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println("The date of today: " + today.toString());

        LocalDate labourDay = LocalDate.of(2020, 5, 1);
        System.out.println("The date of labour day: " + labourDay.toString());

        LocalTime now = LocalTime.now();
        System.out.println("The time of now: " + now.toString());

        LocalTime eightOClock = LocalTime.of(8, 0);
        System.out.println("The time of Eight o'clock: " + eightOClock.toString());

        LocalDateTime currentDateAndTime = LocalDateTime.now();
        System.out.println("The Current date and time: " + currentDateAndTime.toString());

        // Duration是以秒和纳秒为单位来衡量时间间隔，因此不能用于LocalDate类型的比较
        Duration durationBetweenEightOClockAndNow = Duration.between(eightOClock, now);
        System.out.println("The duration between eight o'clock and now: " + durationBetweenEightOClockAndNow.toMinutes() +
                " minute(s)");

        // Period是以年、月、日来衡量时间间隔，因此可以用来比较LocalDate类型
        Period periodBetweenLabourDayAndToday = Period.between(labourDay, today);
        System.out.println("The period between labour day and today: " + periodBetweenLabourDayAndToday.getDays() + " day(s)");

        // 通过LocalDate类型的withAttribute方法可以修改年、月、日中的某一项
        // 返回的是修改后的日期，不会对原日期的值产生影响
        LocalDate thisDayOfLastYear = today.withYear(today.getYear() - 1);
        // 如果月份为1月，那么下面这行代码的返回值将不符合预期，需要做额外的判断或者是使用下面提到的的minus方法才能确保结果正确
        LocalDate thisDayOfLastMonth = today.withMonth(today.getMonthValue() - 1);
        // 如果日期为1号，那么下面这行代码的返回值将不符合预期
        LocalDate yesterday = today.withDayOfMonth(today.getDayOfMonth() - 1);
        System.out.println("This day of last year: " + thisDayOfLastYear.toString());
        System.out.println("This day of last month: " + thisDayOfLastMonth.toString());
        System.out.println("Yesterday: " + yesterday.toString());

        LocalDate newYearsDay = LocalDate.of(2020, 1, 1);
        // 使用minusAttribute方法可以计算相对日期
        System.out.println("The day of last month of New Year's Day: " + newYearsDay.minusMonths(1).toString());
        System.out.println("Yesterday of New Year's day: " + newYearsDay.minusDays(1).toString());

        // 计算当前日期的下一工作日
        System.out.println("Next working day: " + new NextWorkingDay().adjustInto(today));

        // 使用DateTimeFormatter提供的静态工厂方法格式化日期
        System.out.println("BASIC_ISO_DATE formatted date: " + today.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_LOCAL_DATE formatted date: " + today.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // 使用自定义的格式对日期进行格式化
        DateTimeFormatter customizedFormatter = DateTimeFormatter.ofPattern("yyyy~MM~dd HH-mm-ss");
        System.out.println("Current time formatted by customized formatter: " + currentDateAndTime.format(customizedFormatter));
    }
}
