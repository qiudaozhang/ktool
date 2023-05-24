package top.daozhang.ktool

object MapTool {

    @JvmStatic
    fun main(args: Array<String>) {

        val data = mutableMapOf(
            "age" to 18 ,
            10 to "好强大的感觉"
        )

        val d2 = mapStart2StringAny(data)
        println(d2)
//
//        val d2 = mapOf<Any,Any?>(
//            "age" to 28,
//            "name" to "范冰冰"
//        )
//
//        val r = mapMerge(data.toMutableMap(),d2.toMutableMap())
//        println(r)
    }


    /**
     * map的合并处理
     * @param data
     * @param append
     * @return
     */
    @JvmStatic
    fun mapMerge(data: MutableMap<Any, Any?>, append: MutableMap<Any, Any?>): MutableMap<Any, Any?> {
        append.forEach { (t, u) -> data[t] = u }
        return data
    }


    @JvmStatic
    fun mutableMapKey2String(data: MutableMap<Any, Any?>): MutableMap<String, Any?> {
        return data.map { (k, v) ->
            k.toString() to v
        }.toMap().toMutableMap()
    }

    @JvmStatic
    fun mapKey2String(data: Map<Any, Any?>): Map<String, Any?> {
        return data.map { (k, v) ->
            k.toString() to v
        }.toMap().toMutableMap()
    }

    @JvmStatic
    fun mapStart2StringAny(data: Map<*, *>): Map<String, Any?> {
        return data.map { (k, v) ->
            k.toString() to v
        }.toMap().toMutableMap()
    }

}