package top.daozhang.ktool

import kotlin.random.Random

/**
 * 字符串工具
 */
object StrTool {


    @JvmStatic
    fun isEmpty(s: String?): Boolean {
        if (s == null) {
            return true
        }
        return s.trim() == ""
    }


    @JvmStatic
    fun isNotEmpty(s: String?): Boolean {
        return !isEmpty(s)
    }


    /**
     * 是否满足通配符写法的匹配
     *
     * @param target    目标字符串       eg:/a/b
     * @param base      比较基准        eg:/a/☆
     * @return
     */
    @JvmStatic
    fun wildMatch(target: String, base: String): Boolean {
        if (target == base) {
            return true
        }
        // 双星只需要出现在最后
        if (base.contains("**")) {
            if (target.startsWith(base.dropLast(2))) {
                return true
            }
        } else {
            if (base.contains("*")) {
                val baseWords = base.split("/")
                val targetWords = target.split("/")
                if (baseWords.size != targetWords.size) {
                    return false
                }
                return baseWords.zip(targetWords).all { it.first == it.second || it.first == "*" }
            }
        }
        return false
    }


    /**
     * 下划线风格转驼峰
     *
     * @param s eg love_is_good => loveIsGood
     * @return
     */
    @JvmStatic
    fun underscore2camel(s: String): String {
        val words = s.split("_")
        val end = words.drop(1).map { it.replaceFirstChar { a -> a.uppercaseChar() } }
        val start = words.take(1)
        return start.union(end).joinToString("")
    }


    /**
     * 驼峰转下划线风格
     *
     * @param s
     * @return
     */
    @JvmStatic
    fun camel2underscore(s: String): String {
        val upIdx = mutableListOf<Int>()
        s.forEachIndexed { index, c ->
            if (c.isUpperCase()) {
                upIdx += index
            }
        }
        // 是

        val words = splitWithIndexes(s, upIdx)
        return words.joinToString("_") { it.lowercase() }
    }


    @JvmStatic
    fun splitWithIndexes(s: String, idx: List<Int>): List<String> {
        var pre = 0
        val words = mutableListOf<String>()
        if (idx.isEmpty()) {
            words += s
            return words
        }
        idx.forEach { i ->
            run {
                val w = s.drop(pre).take(i - pre)
                words += w
                pre = i
            }
        }
        // 尾巴的词要处理
        words += s.drop(idx.takeLast(1)[0])
        return words
    }

    /**
     * 26个小写字母
     *
     * @return
     */
    @JvmStatic
    fun letter26(): String {
        val start = 97
        val sb = StringBuilder()
        for (i in start..start + 25) {
            sb.append(i.toChar().toString())
        }
        return sb.toString()
    }

    /**
     * 26个大写字母
     *
     * @return
     */
    @JvmStatic
    fun capital26(): String {
        return letter26().uppercase()
    }

    /**
     * 10个数字字符串
     *
     * @return
     */

    @JvmStatic
    fun num10(): String {
        val start = 0
        val sb = StringBuilder()
        for (i in start..start + 9) {
            sb.append(i.toString())
        }
        return sb.toString()
    }


    /**
     * 随机字符串，仅仅包含字母
     * @param len
     * @return
     */
    @JvmStatic
    fun randomAlpha(len: Int = 6): String {
        val w = "${letter26()}${capital26()}"
        return random(w, len)
    }


    /**
     * 指定输入源中随机获取指定长度的随机内容
     *
     * @param input
     * @param len
     * @return
     */
    @JvmStatic
    fun random(input: String, len: Int): String {
        val length = input.length
        val sb = StringBuilder()
        for (i in 1..len) {
            val ri = Random.nextInt(0, length)
            sb.append(input[ri])
        }
        return sb.toString()
    }

    /**
     * 随机字符串包含字母与数字
     * @param len
     * @return
     */

    @JvmStatic
    fun randomAlphaNum(len: Int = 6): String {
        val w = "${letter26()}${capital26()}${num10()}"
        return random(w, len)
    }



}