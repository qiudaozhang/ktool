package top.daozhang.ktool

import java.io.File

object FileTool {


    /**
     * 重命名某个目录下的所有文件
     *
     * @param dir 目录
     * @param start 起始编号
     */
    @JvmStatic
    fun renameAll(dir: String, start: Int) {
        val f = File(dir)
        if (f.exists()) {
            if (f.isDirectory) {
                val files = f.listFiles()
                var i = start
                val s = File.separator
                files.forEach { file ->
                    run {
                        val name = file.name
                        val lastDot = name.lastIndexOf(".")
                        val suffix = name.drop(lastDot)
                        val newName = "${i}${suffix}"
                        val fullNewName = "${dir}${s}${newName}"
//                        println(fullNewName)
                        file.renameTo(File(fullNewName))
                        i += 1
                    }
                }
            }
        }

    }


    /**
     * 重命名文件并且移动
     *
     * @param oldName
     * @param newName
     */
    @JvmStatic
    fun renameAndMove(oldName: String, newName: String) {
        val f = File(oldName)
        if (f.exists()) {
            f.renameTo(File(newName))
        } else {
            throw RuntimeException("old file is not exists")
        }
    }

    /**
     * 重命名文件
     *
     * @param oldName 旧的文件名（全路径）
     * @param newName 新的文件名，只需要文件名称，重命名后还在当前目录
     */
    @JvmStatic
    fun rename(oldName: String, newName: String) {
        val f = File(oldName)
        if (f.exists()) {
            val pp = f.parentFile.absolutePath
            val slash = File.separator
            val newPath = "${pp}${slash}${newName}"
            f.renameTo(File(newPath))
        } else {
            throw RuntimeException("old file is not exists")
        }
    }


}