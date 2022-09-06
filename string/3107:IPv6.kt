package string

import java.io.*

fun getIpString(str: String): String {

    if (str.isEmpty()) return ""

    return str.split(":").joinToString(separator = ":") {
        if (it.isEmpty()) ""
        else {
            val size = it.length
            val prefixZeroCount = 4 - size
            val sb = StringBuilder()
            repeat(prefixZeroCount) {
                sb.append("0")
            }
            sb.append(it)
        }
    }
}

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    //println(getIpString("0:"))
    val compressedIp = readLine().trim()
    if (compressedIp.contains("::")) {
        // ::으로 split
        val doubleColonSplit = compressedIp.split("::")
        val frontStr = doubleColonSplit.first()
        val backStr = doubleColonSplit.last()

        // ::기준 앞 뒤 ip 문자열 받아오기
        var frontIpString = getIpString(frontStr)
        var backIpString = getIpString(backStr)

        // 앞, 뒤에 들어간 숫자 개수 파악
        val noneMidGroupNumCount = frontIpString.filter { it!=':' }.count() + backIpString.filter { it!=':' }.count()

        // 가운데 ip 문자열에 들어갈 0000 그룹수 파악
        val midZeroGroupCount = (32-noneMidGroupNumCount)/4

        // 가운데 ip 문자열 만들어주기
        val midIpString = ArrayList<String>().apply {
            repeat(midZeroGroupCount) {
                add("0000")
            }
        }.joinToString(separator = ":")

        // 앞, 뒤 ip string 에 콜론 없으면 콜론 앞뒤로 붙여주기
        if (frontIpString.isNotEmpty() && frontIpString.last()!=':'){
            frontIpString = "$frontIpString:"
        }
        if (backIpString.isNotEmpty()&&backIpString.first()!=':'){
            backIpString = ":$backIpString"
        }
        println("$frontIpString$midIpString$backIpString")

    } else {
        println(getIpString(compressedIp))
    }
}
