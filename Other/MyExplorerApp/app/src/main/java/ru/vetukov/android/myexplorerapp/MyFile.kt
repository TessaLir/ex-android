package ru.vetukov.android.myexplorerapp

open class MyFile(val name: String, val path: String? = null) {
    fun getFullPath() = "$path/$name"
}