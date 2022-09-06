package tree

import java.io.*
import java.util.*


fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        while(true){
            val st = StringTokenizer(readLine())
            val n = st.nextToken().toInt()
            val k = st.nextToken().toInt()
            if (n == 0 && k == 0) break
            val nodes = readLine().split(" ").map{it.toInt()}.toIntArray()
            val root = nodes[0]
            val parents = IntArray(n){-1}//child, parents
            if (n <= 2 || k == root){
                w.write("0\n")
                continue
            }
            var index = -1
            var kParent = -1
            for(i in 1 until nodes.size){
                if (nodes[i]-nodes[i-1]==1){
                    parents[i] = index
                }else{
                    parents[i] = ++index
                }
                if (nodes[i] == k) {
                    kParent = index
                }
            }
            var ans = 0
            for(i in 1 until n){
                if(parents[kParent]==parents[parents[i]] && parents[i]!=kParent) ans++
            }
            w.write("$ans\n")
        }
        w.flush()
        this.close()
    }
}