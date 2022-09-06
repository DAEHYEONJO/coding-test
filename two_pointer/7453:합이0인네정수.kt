package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = LongArray(n){0L}
    val b = LongArray(n){0L}
    val c = LongArray(n){0L}
    val d = LongArray(n){0L}
    val lim = n*n
    repeat(n){ i ->
        val st = StringTokenizer(readLine())
        a[i] = st.nextToken().toLong()
        b[i] = st.nextToken().toLong()
        c[i] = st.nextToken().toLong()
        d[i] = st.nextToken().toLong()
    }

    val ab = LongArray(lim){0L}
    val cd = LongArray(lim){0L}

    var index = 0
    repeat(n){ i ->
        repeat(n){ j ->
            ab[index] = a[i]+b[j]
            cd[index++] = c[i]+d[j]
        }
    }

    ab.sort()
    cd.sort()

    // target보다 최초로 크거나 같은 index를 return.
    fun lowerBound(target: Long): Int{
        var s = 0
        var e = lim
        while(s<e){
            val m = (s+e)/2
            when{
                cd[m]<target -> s = m + 1
                cd[m]>=target -> e = m
            }
        }
        return s
    }

    //target보다 최초로 큰 index return
    fun upperBound(target: Long):Int{
        var s = 0
        var e = lim
        while(s<e){
            val m = (s+e)/2
            when{
                cd[m]>target -> e = m
                cd[m]<=target -> s = m + 1
            }
        }
        return s
    }


    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var ans = 0L
        for(i in 0 until lim){
            ans+=(upperBound(-ab[i]) - lowerBound(-ab[i])).toLong()
        }
        w.write("$ans")
        w.flush()

    }

}
