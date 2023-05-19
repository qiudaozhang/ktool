package top.daozhang.ktool

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

/**
 * 时间工具
 */
object TimeTool {
    @JvmStatic
    fun main(args: Array<String>) {
        println(ldt2Str(LocalDateTime.now()))
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





}