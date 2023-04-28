package top.daozhang.ktool

import java.nio.ByteBuffer

object ArrayTool {



    fun byte2hex(v:Byte):String{
        return v.toUByte().toString(16).padStart(2,'0')
//        return v.toString(16).padStart(Int.SIZE_BYTES,'0')
    }

    fun bytes2hex(v:ByteArray):String{
        // 1个byte 8个位 16进制换算是2个符号
//        return v.asUByteArray().joinToString(""){it.toString(16).padStart(2,'0')}
//        v.asUByteArray().map {
//            byte2hex(it.toByte())
//        }
        return v.map { byte2hex(it) }.reduce{a,b->a+b}
//        return v.asUByteArray().joinToString(""){it.toString(16).padStart(2,'0')}
    }


    @JvmStatic
    fun byte01String(b: Byte): String {
        // 保留8位数字
//        val s = Integer.toBinaryString(b.toInt()).padStart(Int.SIZE_BYTES, '0')
        val s = Integer.toBinaryString(b.toInt()).padStart(8, '0')
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

        val a = 255
        val arr = int2array(a)
//        println(array2int(arr.drop(2).toByteArray()))
//        println(bytes2hex(arr))

//        println(byte2hex(24))
//        println(byte2hex(-23))

        println(bytes2hex(byteArrayOf(2,53,43,21)))

//        println(byte01String(1))
//        println(byte01String(0))
//        println(byte01String(-1))
//
//        arr.forEach {
//            println(it)
//            println(byte01String(it))
//            println(byte2hex(it))
//        }
    }
}