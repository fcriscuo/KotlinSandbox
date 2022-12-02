package org.batteryparkdev.kotlindev.arrow.io

interface Refined<in T> {
    abstract fun isValid(value: T) : Boolean
}