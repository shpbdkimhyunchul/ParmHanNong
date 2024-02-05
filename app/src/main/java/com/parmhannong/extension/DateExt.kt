package com.parmhannong

import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜 포맷 변경
 */
fun Long.fromLongToDateFormat(format: String): String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val formatter = SimpleDateFormat(format, Locale.ENGLISH)

    return formatter.format(calendar.time)
}

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : Long
 */
inline val String.fromyyyyMMddTHHmmssToLong: Long
    get() {
        return this.replace("T", " ").replace(".000", "").let {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH) to it
        }.let {
            it.first.parse(it.second)
        }.let {
            it.time
        }
    }

inline val String.fromddMMyyyyToLong: Long
    get() {
        return SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).let {
            it.parse(this)
        }.let {
            it.time
        }
    }

inline val String.fromyyyyMMddToLong: Long
    get() {
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).let {
            it.parse(this)
        }.let {
            it.time
        }
    }

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : MM/dd/yyyy (EEE) hh:mm:ss
 */
inline val String.fromyyyyMMddThhmmssToddMMyyyyEEEHHmmss: String
    get() {
        return this.let {
            it.fromyyyyMMddTHHmmssToLong
        }.let {
            it.fromLongToDateFormat("dd/MM/yyyy(EEE) HH:mm:ss")
        }
    }

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : MM/dd/yyyy (EEE) hh:mm:ss
 */
inline val String.fromyyyyMMddThhmmssToddMMyyyyEEE: String
    get() {
        return this.let {
            it.fromyyyyMMddTHHmmssToLong
        }.let {
            it.fromLongToDateFormat("dd/MM/yyyy(EEE)")
        }
    }

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : MM/dd/yyyy (EEE) hh:mm:ss
 */
inline val String.fromyyyyMMddThhmmssTotoddMMyyyyHHmm: String
    get() {
        return this.let {
            it.fromyyyyMMddTHHmmssToLong
        }.let {
            it.fromLongToDateFormat("dd/MM/yyyy HH:mm")
        }
    }

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : dd/MM/yyyy (EEE) hh:mm:ss
 */
inline val String.fromyyyyMMddTotoddMMyyyyEEE: String
    get() {
        return this.let {
            it.fromyyyyMMddToLong
        }.let {
            it.fromLongToDateFormat("dd/MM/yyyy(EEE)")
        }
    }

/**
 * from : yyyy-MM-ddThh:mm:ss
 * to : dd/MM/yyyy (EEE) hh:mm a
 */
inline val String.fromyyyyMMddTotoddMMyyyyEEEhhmma: String
    get() {
        return this.let {
            it.fromyyyyMMddTHHmmssToLong
        }.let {
            it.fromLongToDateFormat("dd/MM/yyyy (EEE) hh:mm a")
        }
    }

inline  val Long.toddHHmmss: String
    get() {
        return this.let {
            it.fromLongToDateFormat("d'Days' HH:mm:ss")
        }

    }

inline  val Long.toddMMyyyy: String
    get() {
        return this.let {
            it.fromLongToDateFormat("ddMMyyyy")
        }
    }

inline  val Long.toddMMyyyyType1: String
    get() {
        return this.let {
            it.fromLongToDateFormat("dd/MM/yyyy")
        }
    }

inline  val Long.toyyyyMMdd: String
    get() {
        return this.let {
            it.fromLongToDateFormat("yyyyMMdd")
        }
    }

inline  val Long.toyyyyMMddType1: String
    get() {
        return this.let {
            it.fromLongToDateFormat("yyyy/MM/dd")
        }
    }

inline  val Long.toyyyyMMddType2: String
    get() {
        return this.let {
            it.fromLongToDateFormat("yyyy-MM-dd")
        }
    }

inline  val Long.toddMMyyyyHHmmype1: String
    get() {
        return this.let {
            it.fromLongToDateFormat("ddMMyyyy HH:mm")
        }
    }
inline  val Long.toddMMyyyyHHmmType1: String
    get() {
        return this.let {
            it.fromLongToDateFormat("dd/MM/yyyy HH:mm")
        }
    }

fun Long.nMonthAgoToddMMyyyy(month: Int): String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.MONTH, month)
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    return formatter.format(calendar.time)
}

fun Long.nMonthAgoToLong(month: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.MONTH, -month)
    return calendar.timeInMillis
}

fun Long.nDayAgoToLong(day: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.DAY_OF_MONTH, -day)
    return calendar.timeInMillis
}

fun Long.nMonthAfterToLong(month: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.MONTH, month)
    return calendar.timeInMillis
}

fun Long.nDayAfter(day: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.add(Calendar.DAY_OF_MONTH, day)
    return calendar.timeInMillis
}
