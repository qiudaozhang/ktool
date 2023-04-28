package top.daozhang.ktool

import java.nio.ByteBuffer

object ArrayTool {


    @JvmStatic
    fun byte01String(b: Byte): String {
        // 保留8位数字
        val s = Integer.toBinaryString(b.toInt()).padStart(Int.SIZE_BITS, '0')
        return s.takeLast(8)
    }


    @JvmStatic
    fun int2array(v: Int): ByteArray {
        return ByteBuffer.allocate(4).putInt(v).array()
    }


    @JvmStatic
    fun array2int(v: ByteArray): Int {

        val size = v.size
        if(size > 4){
            throw RuntimeException("byte array has too many element")
        }
        if (v.size != 4) {
            // 需要补足缺失
            val need0 = 4 - v.size
            val bs = mutableListOf<Byte>()

            for (i in 0 until need0) {
                bs += 0
            }
            v.forEach { bs += it }
            return ByteBuffer.wrap(bs.toByteArray()).getInt()
        }
        val wp = ByteBuffer.wrap(v)
        return wp.getInt()
    }


    @JvmStatic
    fun <T> union(vararg list: List<T>): List<T> {
        return list.reduce { a, b -> a + b }
    }

    @JvmStatic
    fun unionArray(vararg bs: ByteArray): ByteArray {
        return bs.map { it.toList() }.reduce { a, b -> a + b }.toTypedArray().toByteArray()
    }

    @JvmStatic
    fun main(args: Array<String>) {

        val a = 3282
        val arr = int2array(a)
        println(array2int(arr.drop(2).toByteArray()))
    }
}