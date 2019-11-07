package ru.vetukov.android.myandroidapp

open class MyFolder(name: String, val path: String? = null) : MyFile(name) {
    fun getFullPath() = "$path/$name"
}