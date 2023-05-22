package top.daozhang.ktool

import top.daozhang.ktool.pojo.FileMeta
import top.daozhang.ktool.pojo.FileTimeMeta
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.LocalDateTime
import java.time.ZoneId

object FileTool {


    enum class FileSortType {
        CREATE_TIME,
        UPDATE_TIME,
        NAME,
        SIZE,
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val data = sortFile("C:\\Users\\dao\\Downloads", FileSortType.SIZE, false)
        data.forEach { println(it) }
    }

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
                var i = start
                val s = File.separator
                f.listFiles()?.forEach { file ->
                    run {
                        val name = file.name
                        val lastDot = name.lastIndexOf(".")
                        val suffix = name.drop(lastDot)
                        val newName = "${i}${suffix}"
                        val fullNewName = "${dir}${s}${newName}"
                        file.renameTo(File(fullNewName))
                        i += 1
                    }
                }
            }
        }

    }

    @JvmStatic
    fun sortCreatTime(path: String, asc: Boolean = true): MutableList<FileTimeMeta> {
        val f = File(path)
        val metas = mutableListOf<FileTimeMeta>()
        if (f.exists()) {
            if (f.isDirectory) {
                f.listFiles()?.forEach { file ->
                    run {
                        val attr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                        val ct = attr.creationTime()
                        val ldt = LocalDateTime.ofInstant(ct.toInstant(), ZoneId.systemDefault())
                        val meta = FileTimeMeta(file.name, ldt)
                        metas.add(meta)
                    }
                }
            }
        }
        if (asc) {
            metas.sortBy { it.createTime }
        } else {
            metas.sortByDescending { it.createTime }
        }
        return metas
    }

    @JvmStatic
    fun sortFile(path: String, sortType: FileSortType, asc: Boolean = true): List<FileMeta> {
        val f = File(path)
        val metas = mutableListOf<FileMeta>()
        if (f.exists()) {
            if (f.isDirectory) {
                f.listFiles()?.forEach { file ->
                    run {
                        val m = FileMeta()
                        m.name = file.name
                        when (sortType) {
                            FileSortType.CREATE_TIME -> {
                                val attr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                                val ct = attr.creationTime()
                                val ldt = LocalDateTime.ofInstant(ct.toInstant(), ZoneId.systemDefault())
                                m.createTime = ldt
                            }
                            FileSortType.UPDATE_TIME -> {
                                val attr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                                val ct = attr.lastModifiedTime()
                                val ldt = LocalDateTime.ofInstant(ct.toInstant(), ZoneId.systemDefault())
                                m.updateTime = ldt
                            }
                            FileSortType.SIZE -> {
                                val attr = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                                val size = attr.size()
                                m.size = size
                            }

                            else -> {}
                        }

                        metas.add(m)
                    }
                }
            }
        }
        when (sortType) {
            FileSortType.CREATE_TIME -> {
                if (asc) {
                    metas.sortBy { it.createTime }
                } else {
                    metas.sortByDescending { it.createTime }
                }
            }

            FileSortType.UPDATE_TIME -> {
                if (asc) {
                    metas.sortedBy { it.updateTime }
                } else {
                    metas.sortByDescending { it.updateTime }
                }
            }

            FileSortType.SIZE -> {
                if (asc) {
                    metas.sortBy { it.size }
                } else {
                    metas.sortByDescending { it.size }
                }
            }

            else -> {}
        }
        return metas.toList()

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