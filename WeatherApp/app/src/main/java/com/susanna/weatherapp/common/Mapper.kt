package com.susanna.weatherapp.common

interface Mapper<From, To> {
    fun mapFrom(from: From): To
}