package top.daozhang.ktool

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 时间工具
 */
object TimeTool {


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
    fun main(args: Array<String>) {
//        println(monthBegin())
        println(yearEnd())
    }



}