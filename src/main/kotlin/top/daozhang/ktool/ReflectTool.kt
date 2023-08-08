package top.daozhang.ktool

import User
import java.lang.reflect.Modifier

object ReflectTool {

    fun findNonFinalField(c:Class<*>){
        val fields = c.declaredFields

        fields.forEach {
            if(it.modifiers == Modifier.FINAL){
                println("final修饰不处理")
            } else {
                println(it)
                println(it.modifiers)
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        findNonFinalField(User::class.java)
    }
}