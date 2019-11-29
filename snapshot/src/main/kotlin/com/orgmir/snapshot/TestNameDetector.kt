package com.orgmir.snapshot

import java.lang.reflect.Method

object TestNameDetector {
    private val TEST_ANNOTATION_REGEX = Regex("org.junit(?s)(.*).Test")

    fun getTestStackElement(): StackTraceElement? =
        Thread.currentThread().stackTrace
            .toList()
            .firstOrNull {
                try {
                    val traceClass = Class.forName(it.className)
                    val method = traceClass.getMethod(it.methodName)
                    isTestMethod(method)
                } catch (e: Exception) {
                    false
                }
            }

    private fun isTestMethod(method: Method) = method.annotations.any {
        it.annotationClass.qualifiedName?.matches(TEST_ANNOTATION_REGEX) ?: false
    }
}