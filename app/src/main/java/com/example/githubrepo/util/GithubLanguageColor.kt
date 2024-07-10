package com.example.githubrepo.util

import androidx.annotation.ColorInt
import com.example.githubrepo.R

object LanguageColor {

    private fun languageMap(): Map<String, LanguageInfo> {
        val colorResources = mapOf(
            "Java" to R.color.java_color,
            "Python" to R.color.python_color,
            "JavaScript" to R.color.javascript_color,
            "C++" to R.color.cpp_color,
            "C#" to R.color.csharp_color,
            "PHP" to R.color.php_color,
            "Ruby" to R.color.ruby_color,
            "Swift" to R.color.swift_color,
            "Go" to R.color.go_color,
            "Kotlin" to R.color.kotlin_color,
            "TypeScript" to R.color.typescript_color,
            "C" to R.color.c_color,
            "Shell" to R.color.shell_color,
            "Rust" to R.color.rust_color,
            "Scala" to R.color.scala_color,
            "Objective-C" to R.color.objectivec_color,
            "Perl" to R.color.perl_color,
            "R" to R.color.r_color,
            "Dart" to R.color.dart_color,
            "Assembly" to R.color.assembly_color
        )
        return colorResources.mapValues { (language, colorResId) ->
            LanguageInfo(colorResId, language)
        }
    }

    fun getColor(language: String?): Int = languageMap()[language]?.color ?: R.color.kotlin_color

    data class LanguageInfo(
        @ColorInt val color: Int,
        val language: String
    )
}

