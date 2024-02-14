package com.example.githubuserapp.helper

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object JsonConverter {
    fun readJSONFromAssets(context: Context, path: String): String {
        return try {
            val file = context.assets.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.use { lines ->
                lines.forEachLine {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()
            jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}