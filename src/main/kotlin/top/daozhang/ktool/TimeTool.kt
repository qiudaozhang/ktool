package top.daozhang.ktool

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.DAYS
import java.time.temporal.ChronoUnit.MONTHS
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * 时间工具
 */
object TimeTool {




    @JvmStatic
    fun main(args: Array<String>) {
        // 2023-5-24 10:31:23
        val fmt  = "yyyy-M-dd HH:mm:ss"
        val one = str2ldt("2023-5-24 09:53:04",fmt)
//        val two = LocalDateTime.now()
        val two = str2ldt("2023-5-24 16:26:28",fmt)
        val sec = secondDiff(one,two)
        val minute = sec/60
        println(minute)

    }



    fun secondDiff(one:LocalDateTime,two:LocalDateTime):Long{
        val between = Duration.between(one, two)
        return Math.abs(between.seconds)
    }


    /**
     * 时间范围内的所有天
     *
     * @param begin
     * @param end
     * @return
     */
    @JvmStatic
    fun days(begin:LocalDate, end:LocalDate):List<LocalDate>{
        checkBeginEndTime(begin,end)
        val days = dayDiff(begin,end)
        return (0 until days+1).map {
            begin.plusDays(it)!!
        }
    }


    private fun checkBeginEndTime(begin:LocalDate,end:LocalDate){
        if(begin.isAfter(end)){
            throw RuntimeException("begin time should less than end time")
        }
    }

    /**
     * 获取指定日期范围的月第一日集合，比如2022-05-03 2023-05-03 ，其中2022 05 01 也会包含，如果不希望有，可以获取后丢弃第一条数据
     *
     * @param begin
     * @param end
     * @return
     */
    @JvmStatic
    fun months(begin:LocalDate,end:LocalDate):List<LocalDate>{
        checkBeginEndTime(begin,end)
        val step = monthDiff(begin,end)
        val monthBeginDay = monthBegin(begin)
        return (0 until step+1).map {
            monthBeginDay.plusMonths(it)!!
        }
    }



    /**
     * 两个日期之间的天数差值
     * two-one的天数差，如果one比two的时间更靠近现在则为负数
     *
     * @param one
     * @param two
     * @return
     */
    @JvmStatic
    fun dayDiff(one:LocalDate,two:LocalDate):Long{
        return DAYS.between(one,two)
    }

    /**
     * 两个日期之间的月份差值
     *
     * @param one
     * @param two
     * @return
     */
    @JvmStatic
    fun monthDiff(one:LocalDate,two:LocalDate):Long{
        return MONTHS.between(one,two)
    }


    /**
     * 当前是第几周
     *
     * @return
     */
    @JvmStatic
    fun nowWeek(): Int {
        val now = LocalDate.now()
        val wf = WeekFields.of(Locale.getDefault())
        return now.get(wf.weekOfYear())
    }

    /**
     * 一年当中第几周的第一天
     *
     * @param w
     * @return
     */
    @JvmStatic
    fun weekFirstDay(w:Int):LocalDateTime{
        if(w < 1 || w > 53){
            throw RuntimeException("week param illegal,only accept 1 to 53")
        }
        val now = LocalDate.now()
        val wf = WeekFields.of(Locale.getDefault())
        val thisWeekBegin = now.minusDays(now.dayOfWeek.value.toLong()-1)
        val weekNumber = now.get(wf.weekOfYear())
        val weekAdd = w-weekNumber
        return thisWeekBegin.plusWeeks(weekAdd.toLong()).atStartOfDay()
    }

    /**
     * 一年当中第几周的最后一天
     *
     * @param w
     * @return
     */
    @JvmStatic
    fun weekLastDay(w:Int):LocalDateTime{
        return weekFirstDay(w).plusWeeks(1).minusSeconds(1)
    }


    @JvmStatic
    fun todayBegin():LocalDateTime{
        return LocalDate.now().atStartOfDay()
    }


    @JvmStatic
    fun todayEnd():LocalDateTime{
        return todayBegin().plusDays(1).minusSeconds(1)
    }


    @JvmStatic
    fun monthBegin():LocalDateTime{
        val begin = todayBegin()
        return begin.minusDays(begin.dayOfMonth.toLong()-1)
    }

    @JvmStatic
    fun monthBegin(date:LocalDate):LocalDate{
        return date.minusDays(date.dayOfMonth.toLong()-1)
    }

    @JvmStatic
    fun monthEnd():LocalDateTime{
        return monthBegin().plusMonths(1).minusSeconds(1)
    }

    @JvmStatic
    fun yearBegin():LocalDateTime{
        val b = monthBegin()
        return b.minusMonths(b.monthValue.toLong()-1)
    }

    @JvmStatic
    fun yearEnd():LocalDateTime{
        return yearBegin().plusYears(1).minusSeconds(1)
    }

    @JvmStatic
    fun ldt2Str(ldt: LocalDateTime, format: String = "yyyy-MM-dd HH:mm:ss"): String {
        val fmt = DateTimeFormatter.ofPattern(format)
        return ldt.format(fmt)
    }


    @JvmStatic
    fun str2ldt(str: String, format: String = "yyyy-MM-dd HH:mm:ss"): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(format)
        return LocalDateTime.parse(str, formatter)
    }





}