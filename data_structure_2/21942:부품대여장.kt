package data_structure_2

import java.io.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        val (n, times, pay) = readLine().split(" ")
        val N = n.toInt()
        val PAY = pay.toLong()
        val time = times.split("/")
        val days = time[0].toInt()
        val (hours, minutes) = time[1].split(":").map{it.toInt()}
        val possibleMin = (days*24*60 + hours*60 + minutes).toLong()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")


        val hashMap = HashMap<String, HashMap<String, String>>()
        val resultMap = HashMap<String, Long>()
        val sb = StringBuilder()
        repeat(N){
            sb.clear()
            val line = readLine().split(" ")
            val dateStr = sb.append("${line[0]} ").append(line[1])
            val product = line[2]
            val name = line[3]
            if (hashMap[name]?.get(product) != null){
                val productMap = hashMap[name]
                val fromDate = productMap!![product]
                val diff = getTimeDiffSeconds(simpleDateFormat.parse(fromDate), simpleDateFormat.parse(dateStr.toString()))
                if (diff > possibleMin) {
                    resultMap.run {
                        put(name, getOrDefault(name, 0L) + ((diff - possibleMin) * PAY))
                    }
                }
                hashMap[name]!!.remove(product)
            }
            else{
                hashMap.run{
                    put(name, getOrDefault(name, HashMap<String,String>()).apply{put(product, dateStr.toString())})
                }
            }
        }
        if (resultMap.isEmpty()) w.write("-1")
        else{
            resultMap.toList().sortedBy { it.first }.forEach {
                w.write("${it.first} ${it.second}\n")
            }
        }
        w.flush()
    }
}

fun getTimeDiffSeconds(start: Date, end: Date):Long{
    return (end.time - start.time)/(60L*1000L)
}
