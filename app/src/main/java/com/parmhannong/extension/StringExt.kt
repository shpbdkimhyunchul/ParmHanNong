package com.parmhannong

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Html
import android.util.Patterns
import android.util.TypedValue
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


/**
 *  한글 구분
 */
inline val String?.isHangul : Boolean get() = Pattern.matches("^[ㄱ-ㅎ가-힣0-9]*\$", this)

/**
 *  영문 구분
 */
inline val String?.isEnglish : Boolean get() = Pattern.matches("^[a-zA-Z\\s]+\$", this)

/**
 *  숫자 구분
 */
inline val String?.isNumeric : Boolean get() =  Pattern.matches("-?\\d+(\\.\\d+)?", this)



/**
 * 이메일 local-part 길이 체크
 */
inline val String.toEmailLength: Int
    get() {
        if (this.isNullOrEmpty()) return 0

        val parts = this.split("@")
        if (parts.isEmpty()) return 0

        return parts[0].length
    }

inline val String.isEmail: Boolean get() = Patterns.EMAIL_ADDRESS.matcher(this).matches() 

/**
 * 이메일 유효성 검사
 * @return 0:성공, 1:미입력, 2:3자 미만, 3:이메일 형식 오류
 */
inline val String.checkEmail: Int
    get() {
        return when {
            this.isNullOrEmpty() -> 2
            this.toEmailLength < 3 -> 2
            !this.isEmail -> 3
            else -> 0
        }
    }

/**
 * 카운트 타임 포맷
 */
inline val Long.secondToString: String 
get() {
    val minute = (this / 60).toInt()
    val second = (this % 60).toInt()
    var div = ": "
    if (second < 10) {
        div = ": 0"
    }
    return "0$minute $div$second"
}

inline val Int.secondToString: String 
get(){
    val minute = (this / 60)
    val second = (this % 60)
    var div = ": "
    if (second < 10) {
        div = ": 0"
    }
    return "0$minute $div$second"
}

inline val String.toNumberFormat: String
get() {
    val number = Integer.parseInt(this)
    return NumberFormat.getNumberInstance(Locale("lo", "LA", "")).format(number)
}

inline val String.addDecimal: String
get() {
    return if (this.indexOf(".")>0) {
        val sIndex = this.indexOf(".")
        if (this.length - sIndex == 2) {
            this+"0"
        } else {
            this
        }
    } else {
        "$this.00"
    }
}

/**
 * 소수점 제거
 */
inline val Double.removeDecimalPoint: String
    get(){
        return String.format("%.0f", this)
    }


/**
 * 숫자에 천단위마다 콤마 넣기
 *
 * @param num Double
 * @return String
 */
inline val String.toStringNumberFormat: String
    get() {
        var number = "0"
        try {
            val df = DecimalFormat("#,###.##")
            df.roundingMode = RoundingMode.UP
            number = df.format(this.toDouble())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return number.addDecimal
    }

inline val Double.toStringNumberFormat: String 
get() {
    var number = "0"
    try {
        val df = DecimalFormat("#,###.##")
        df.roundingMode = RoundingMode.UP
        number = df.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return number.addDecimal
}

inline val Float.toStringNumberFormat: String 
get() {
    var number = "0"
    try {
        val df = DecimalFormat("#,###.##")
        df.roundingMode = RoundingMode.UP
        number = df.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return number.addDecimal
}

inline val Long.toStringNumberFormat: String 
get(){
    var number = "0"
    try {
        val df = DecimalFormat("#,###.##")
        df.roundingMode = RoundingMode.UP
        number = df.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return number.addDecimal
}

inline val Int.toStringNumberFormat: String 
get(){
    var number = "0"
    try {
        val df = DecimalFormat("#,###.##")
        df.roundingMode = RoundingMode.UP
        number = df.format(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return number.addDecimal
}


/**
 * 숫자에 천단위마다 콤마 넣기 + 화폐 단위
 *
 * @param num Double
 * @return String
 */
inline val Int.toStringNumberFormatWithCurreny: String 
    get() = this.toDouble().toStringNumberFormat + "원"

inline val Double.toStringNumberFormatWithCurreny: String 
    get() = this.toStringNumberFormat + "원"

inline val Float.toStringNumberFormatWithCurreny: String
    get() = this.toStringNumberFormat + "원"

inline val String.toStringNumberFormatWithCurreny: String 
    get() = this.toDouble().toStringNumberFormat + "원"

inline val Long.toStringNumberFormatWithCurreny: String
    get() = this.toStringNumberFormat + "원"

inline val String.replaceCurrency: String
    get() = this.replace("원", "").replace(",", "")


/**
 * 숫자에 천단위마다 콤마 넣기 + 화폐 단위 (만원단위)
 *
 * @param num Double
 * @return String
 */
inline val Double.toStringNumberFormatWithCurreny10000: String
get() {
    val unit = 10000
    if (this > 0 && this % unit == 0.0) {
        try {
            val df = DecimalFormat("#")
            df.roundingMode = RoundingMode.UP
            val number = df.format((this.toInt() / unit).toLong())
            return NumberFormat.getNumberInstance(Locale.KOREA).format(number.toInt()) + "만원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return this.toStringNumberFormatWithCurreny
}

inline val Int.toCurrency: String
get() {
    var returnCurrency = 0
    val builder = StringBuilder()
    if (this >= 10000) {
        builder.append(this / 10000)
            .append("만")
        returnCurrency = this % 10000
    }

    if (this >= 1000) {
        builder.append(this / 1000).append("천")
        returnCurrency = this % 1000
    }

    if (returnCurrency > 0 || builder.isEmpty()) {
        builder.append(returnCurrency)
    }
    builder.append("원")
    return builder.toString()
}



/**
 * 할인금액 0보다 클경우 - 붙이기 + 원
 *
 * @param amount
 * @return
 */
inline val Double.toAddDisCountMinusAmountUnit: String
get() {
    if (0 < this) {
        try {
            return "-" + this.toStringNumberFormat + "원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0원"
}

inline val Float.toAddDisCountMinusAmountUnit: String
get() {
    if (0 < this) {
        try {
            return "-" + this.toStringNumberFormat + "원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0원"
}

inline val Long.toAddMinusAmountUnit: String
get() {
    if (0 < this) {
        try {
            return "-" + this.toStringNumberFormat + "원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0원"
}

/**
 * 할인금액 0보다 클경우 + 붙이기 + 원
 *
 * @param amount
 * @return
 */
inline val Float.toAddDisCountPlusAmountUnit: String
get() {
    if (0 < this) {
        try {
            return this.toStringNumberFormat + "원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0원"
}

inline val Long.toAddPlusAmountUnit: String
get() {
    if (0 < this) {
        try {
            return "+" + this.toStringNumberFormat + "원"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return "0원"
}


/**
 * Date Format 변경 - yyMM -> MM/yy
 *
 * @param strDate 카드 유효기간 타입
 * @return
 */
inline val String.toDateFormatExpireDate: String
get() {
    val inputPattern = "yyMM"
    val outputPattern = "MM/yy"

    var date = Date()
    try {
        val format = SimpleDateFormat(inputPattern)
        date = format.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val calendar = Calendar.getInstance()
    calendar.time = date
    val formatter = SimpleDateFormat(outputPattern)

    return formatter.format(calendar.time)
}



/**
 * 실물카드 번호 Masking (1234-56**-****-9876)
 */
inline val String?.getStringMaskingCardNumber: String
get() {
    return this?.let {cardNum ->
        if (cardNum.length == 16) {
            val result = StringBuilder(cardNum)
            result.replace(6, 12, "******")
            val regData = "(\\d{4})(\\S{4})(\\S{4})(\\d{4})"
            return result.toString().replace(regData.toRegex(), "$1-$2-$3-$4")
        }else{
            ""
        }
    }?: run { "" }
}

/**************************************************
 * 카드 번호
 */
inline val String?.getStringCardNumber: String
get() {
    return this?.let { cardNum ->
        if (cardNum.length == 16) {
            val regData = "(\\d{4})(\\d{4})(\\d{4})(\\d{4})"
            return cardNum.replace(regData.toRegex(), "$1-$2-$3-$4")
        }else{
            ""
        }
    }?: run{ "" }
}


/**************************************************
 * 신용카드 번호
 */
inline val String?.toDashCreditCard: String
get() {
    return this?.let { cardNum ->

        if(cardNum.length<=2) return@let cardNum

        if(cardNum.startsWith("37")){
            //AMEX 4-6-5
            return when{
                cardNum.length<=4 -> cardNum
                cardNum.length<=10 -> cardNum.substring(0,4) + " " + cardNum.substring(4, cardNum.length)
                cardNum.length<=16 -> cardNum.substring(0,4) + " " + cardNum.substring(4, 10) + " " + cardNum.substring(10, cardNum.length)
                else -> cardNum.substring(0,4) + " " + cardNum.substring(4, 10) + " " + cardNum.substring(10, cardNum.length)
            }
        }

        return when{
            cardNum.length <=4 -> cardNum
            cardNum.length <=8 -> cardNum.substring(0,4) + " " + cardNum.substring(4, cardNum.length)
            cardNum.length <=12 -> cardNum.substring(0,4) + " " + cardNum.substring(4, 8) + " " + cardNum.substring(8, cardNum.length)
            cardNum.length <=16 -> cardNum.substring(0,4) + " " + cardNum.substring(4, 8) + " " + cardNum.substring(8, 12) + " " + cardNum.substring(12, cardNum.length)
            else -> cardNum.substring(0,4) + " " + cardNum.substring(4, 8) + " " + cardNum.substring(8, 12) + " " + cardNum.substring(12, cardNum.length)
        }
    }?: run{""}
}


/**************************************************
 * MM/YY 입력시 Dash
 */
inline val String?.toSlashMMYY: String
get() {
    return this?.let { cardNum ->
        return when{
            cardNum.length<=2 -> cardNum
            else -> cardNum.substring(0,2) + "/" + cardNum.substring(2, cardNum.length)
        }
    }?: run{""}
}

/**
 * MM/YY 유요기간 유효성 체크
 */
inline val String?.isValidDateMMYY: Boolean
get() {
    return this?.let { validDate ->
        if(validDate.toStripSlash.length != 4){
            return false
        }

        try{
            val split = validDate.split("/")
            split[0].toInt() in 1..12
        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }?: run{false}
}


/**************************************************
 * 주민번호 입력시 Space
 */
inline val String?.toDashSocial: String
get() {
    return this?.let { cardNum ->
        return when{
            cardNum.length<=6-> cardNum
            else -> cardNum.substring(0,6) + " " + cardNum.substring(6, cardNum.length)
        }
    }?: run{""}
}


/**************************************************
 * 생년월일 입력시 Space
 */
inline val String?.toDashBirth: String
get() {
    return this?.let { cardNum ->
        return when{
            cardNum.length<=4 -> cardNum
            cardNum.length<=6 -> cardNum.substring(0,4) + "." + cardNum.substring(4, cardNum.length)
            else -> cardNum.substring(0,4) + "." + cardNum.substring(4, 6)+ "." + cardNum.substring(6, cardNum.length)
        }
    }?: run{""}
}



/**
 * 휴대폰번호 인지 체크
 */
inline val String.isMobileNumber: Boolean
get() {
    return (this.startsWith("010")
            || this.startsWith("011")
            || this.startsWith("016")
            || this.startsWith("017")
            || this.startsWith("018")
            || this.startsWith("019"))
}

/**************************************************
 * 마스킹 정책
 **************************************************/
/**
 * 계좌번호: 앞 4번째부터 3자리 ex)430-***1-1111
 *
 * @param bankAccount 은행 계좌
 * @return String
 */
inline val String.toMaskingBankAccount: String
get() {
    if (this.length > 6) {
        val result = StringBuilder(this)
        result.setCharAt(3, '*')
        result.setCharAt(4, '*')
        result.setCharAt(5, '*')
        return result.toString()
    }
    return this
}


/**
 * 유저이름 마스킹
 *
 * @param bankAccount 은행 계좌
 * @return String
 */
inline val String.toMaskingUserName: String
get() {
    if (this.length > 6) {
        val result = StringBuilder(this)
        result.setCharAt(3, '*')
        result.setCharAt(4, '*')
        result.setCharAt(5, '*')
        return result.toString()
    }
    return this
}



/**
 * 휴대폰번호: 뒤에서부터 4자리 ex)010-1234-***
 *
 * @param number 휴대폰 번호
 * @return String
 */
inline val String.toMaskingMobileNumber: String
get() {
    if (this.length > 4) {
        val result = StringBuilder(this)
        for (i in this.length - 1 downTo this.length - 4) {
            result.setCharAt(i, '*')
        }
        if (result.length == 11) {
            return result.substring(0, 3) + "-" + result.substring(3, 7) + "-" + result.substring(
                7,
                11
            )
        } else if (result.length == 10) {
            return result.substring(0, 3) + "-" + result.substring(3, 6) + "-" + result.substring(
                6,
                10
            )
        } else if(result.startsWith("+82")){
            if (result.length == 14) {
                return result.substring(0, 3) + "-" + result.substring(3, 6) + "-" + result.substring(6, 10) + "-" + result.substring(
                    10,
                    result.length
                )
            } else if (result.length == 13) {
                return result.substring(0, 3) + "-" + result.substring(3, 6) + "-" + result.substring(6, 9) + "-" + result.substring(
                    9,
                    result.length
                )
            }
        }
        return result.toString()
    }
    return this
}

/**
 * 입력받은 번호를 "-"추가하여 전화번호 포맷으로 전달.
 *
 * @param num 번호
 * @return 전화번호 포맷
 */
inline val String.toTelNumber: String
get() {

    //숫자인지 체크
    if (!this.isNumber) {
        return this
    }

    var result = ""
    if (this.startsWith("011") || this.startsWith("016") || this.startsWith("017") || this.startsWith( "018") || this.startsWith( "019" )) {
        //휴대폰 번호
        val regData = "(\\d{3})(\\d{3})(\\d{4})"
        result = this.replace(regData.toRegex(), "$1-$2-$3")
    } else if (this.length in 2..9) {
        //일반전화
        result = if (this.substring(0, 2) == "02") {
            //지역번호 서울
            val regData = "(\\d{2})(\\d{3,4})(\\d{4})"
            this.replace(regData.toRegex(), "$1-$2-$3")
        } else {
            //1588-0000
            val halfCnt = this.length / 2
            this.substring(0, halfCnt) + "-" + this.substring(halfCnt)
        }
    } else if (this.length in 1..11) {
        //휴대폰 및 세자리 지역번호
        result = if (this.substring(0, 2) == "02") {
            //지역번호 서울
            val regData = "(\\d{2})(\\d{3,4})(\\d{4})"
            this.replace(regData.toRegex(), "$1-$2-$3")
        } else {
            //휴대폰 번호
            val regData = "(\\d{3})(\\d{3,4})(\\d{4})"
            this.replace(regData.toRegex(), "$1-$2-$3")
        }
    } else if (this.isNotEmpty() && 11 < this.length) {
        //안심번호 및 다른번호
        val head = this.substring(0, 4)
        val mid = this.substring(4, 7)
        val end = this.substring(7)
        result = "$head-$mid-$end"
    }
    return result
}

inline val String.isNumber: Boolean get() = !this.isNullOrEmpty() && this.matches("^[0-9]*$".toRegex())

inline val String.toChangeNumber: Double
get() {
    var changeNumber = if(this.replace("[^0-9-]".toRegex(), "").isNullOrEmpty()){
        "0"
    }else{
        this.replace("[^0-9-]".toRegex(), "")
    }

    return changeNumber.toDouble()  //"12345678"
}

inline val String.toChangeNumberString: String
get() {
    var changeNumber = if(this.replace("[^0-9-]".toRegex(), "").isNullOrEmpty()){
        "0"
    }else{
        this.replace("[^0-9-]".toRegex(), "")
    }

    return changeNumber  //"12345678"
}

/**
 *  평생 계좌 확인 - 01로 시작하는 10,11자
 */
inline val String.checkNumberAccount: Boolean
get() {
    if (this.startsWith("01")) {
        if (this.length == 11 || this.length == 10) return true
    }
    return false
}

/**
 * 휴대폰번호: 뒤에서부터 4자리 ex)010-1234-***
 *
 * @param number 휴대폰 번호
 * @return String
 */
inline val String.toStringMaskingMobileNumber: String
get() {
    if (this.length > 4) {
        val result = StringBuilder(this)
        for (i in this.length - 1 downTo this.length - 4) {
            result.setCharAt(i, '*')
        }
        if (result.length == 11) {
            return result.substring(0, 3) + "-" + result.substring(3, 7) + "-" + result.substring(
                7,
                11
            )
        } else if (result.length == 10) {
            return result.substring(0, 3) + "-" + result.substring(3, 6) + "-" + result.substring(
                6,
                10
            )
        }
        return result.toString()
    }
    return this
}

inline val String?.nullCheckToSpace: String get() = this ?: ""

inline val String?.toStripCurrency: String
get() {//123456789
    return this?.let {
        it.replace("[^0-9-]".toRegex(), "")
    }?: ""
}

inline val String?.toStripCardNum: String
get() {
    return this?.let {
        var cardNum = it.replace("-".toRegex(), "")
        cardNum.replace(" ".toRegex(), "")
    }?: ""
}

inline val String?.toStripDashCardNum: String
get() {
    return this?.let {
        it.replace("-".toRegex(), "")
    }?: ""
}

inline val String?.toStripSpace: String
get() {
    return this?.let {
        it.replace(" ".toRegex(), "")
    }?: ""
}

inline val String?.toStripSlash: String
get() {
    return this?.let {
        it.replace("/".toRegex(), "")
    }?: ""
}

inline val String?.toStripComma: String
get() {
    return this?.let {
        it.replace("[\\.]".toRegex(), "")
    }?: ""
}

inline val String?.toStripPlus: String
get() {
    return this?.let {
        it.replace("[\\+]".toRegex(), "")
    }?: ""
}




/**
 * 오늘 날짜
 *
 * @return 지정 날짜 포맷
 */
inline val String.getDateFormatToday: String
get() {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    val formatter = SimpleDateFormat(this)

    return formatter.format(calendar.time)
}

/**
 * 숫자 앞에 1자리 일때 앞에 0 붙이기
 */
inline val Int?.to02D: String
get() {
    return this?.let {
        String.format("%02d",it)
    }?: ""
}

inline val Long?.to02D: String
    get() {
        return this?.let {
            String.format("%02d",it)
        }?: ""
    }

inline val String?.to02D: String
get() {
    return this?.let {
        String.format("%02d",it.toInt())
    }?: ""
}

/**
 * 이름(국문): 성명 중 두번쨰 자리
 * 이름(영문): 성명 중 앞 5자를 제외한 나머지
 *
 * @return String
 */
inline val String.getStringMaskingName: String
get() {
    val input = this.replace(" ", "")
    val eng = Pattern.matches("^[a-zA-Z]*$", input)
    if (eng) {
        if (input.length > 5) {
            val result = StringBuilder(input)
            for (i in 5 until input.length) {
                result.setCharAt(i, '*')
            }
            return result.toString()
        }
    } else {
        if (input.length > 1) {
            val result = StringBuilder(input)

            // support Emoji charater
            if (/*result.length() > 1 &&*/ Character.isSurrogatePair(result[0], result[1])) {
                if (result.length > 3 && Character.isSurrogatePair(result[2], result[3])) {
                    result.deleteCharAt(3)
                }
                result.setCharAt(2, '*')
            } else {
                result.setCharAt(1, '*')
            }
            return result.toString()
        }
    }

    return this
}


/**
 * 아이디(이메일주소) : ID중 앞 2자리를 제외한 나머지 *처리함
 * 마스킹 처리 화면 : 아이디찾기, 회원정보조회
 * 전체 아이디 노출 : (입력화면)로그인 등
 *
 * @param email 이메일 주소
 * @return
 */
inline val String.getStringMaskingEmail: String
get() {
    if (this.length > 2) {
        val value = this.split("\\@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (value.size == 2 && value[0].length > 2) {
            val result = StringBuilder(value[0])
            for (i in 2 until result.length) {
                result.setCharAt(i, '*')
            }
            return result.toString() + "@" + value[1]
        }
    }
    return this
}

inline val String.getTelNumber: String
get() {
    //숫자인지 체크
    if (!this.isNumber) {
        return this
    }

    var result = ""
    if (startsWith("011") || startsWith("016") || startsWith("017") || startsWith("018") || startsWith( "019" ) ) {
        //휴대폰 번호
        val regData = "(\\d{3})(\\d{3})(\\d{4})"
        result = replace(regData.toRegex(), "$1-$2-$3")
    } else if (length in 2..9) {
        //일반전화
        result = if (substring(0, 2) == "02") {
            //지역번호 서울
            val regData = "(\\d{2})(\\d{3,4})(\\d{4})"
            replace(regData.toRegex(), "$1-$2-$3")
        } else {
            //1588-0000
            val halfCnt = length / 2
            substring(0, halfCnt) + "-" + substring(halfCnt)
        }
    } else if (length in 1..11) {
        //휴대폰 및 세자리 지역번호
        result = if (substring(0, 2) == "02") {
            //지역번호 서울
            val regData = "(\\d{2})(\\d{3,4})(\\d{4})"
            replace(regData.toRegex(), "$1-$2-$3")
        } else {
            //휴대폰 번호
            val regData = "(\\d{3})(\\d{3,4})(\\d{4})"
            replace(regData.toRegex(), "$1-$2-$3")
        }
    } else if (0 < length && 11 < length) {
        //안심번호 및 다른번호
        val head = substring(0, 4)
        val mid = substring(4, 7)
        val end = substring(7)
        result = "$head-$mid-$end"
    }
    return result
}

fun Float.toDpToPixel(context: Context): Int{
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics).toInt()
}

fun TextView.toTextfromHtml(format: String?, vararg args: Any?) {
    format?.let {
        text = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            @Suppress("DEPRECATION")
            Html.fromHtml(String.format(it, *args))
        } else {
            Html.fromHtml(String.format(it, *args), Html.FROM_HTML_MODE_LEGACY)
        }
    }
}



inline val String?.isEqualsY: Boolean
get() {
    this?.let {
        return "Y" == it
    }
    return false
}

inline fun String.copyClipBoard(context: Context){
    val clipData = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipData.setPrimaryClip(ClipData.newPlainText("", this))
}