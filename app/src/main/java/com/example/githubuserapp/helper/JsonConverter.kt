package com.example.githubuserapp

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object JsonConverter {
    fun ReadJSONFromAssets(context: Context, path: String): String {
        try {
            val file = context.assets.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.use { lines ->
                lines.forEachLine {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()
            return jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}