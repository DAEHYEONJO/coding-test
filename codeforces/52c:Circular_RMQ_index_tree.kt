package codeforces

import java.io.*
import java.util.*


data class TNode(
    var mn: Long = 0L,
    var upd: Long = 0L,
    var left: Int = 0,
    var right: Int = 0
)

val tree = Array(200005 * 4) { TNode() }
lateinit var a : LongArray
fun build(node: Int, lf: Int, rg: Int): Long{
    tree[node].run {
        left = lf
        right = rg
    }
    if (lf == rg){
        // case leaf
        tree[node].mn = a[lf]
    }else{
        // case internal
        val mid = ((lf.toLong() + rg.toLong())/2).toInt()
        val lr = build(2*node, lf, mid)
        val rr = build(2*node+1, mid+1, rg)
        tree[node].mn = minOf(lr, rr)
    }
    return tree[node].mn + tree[node].upd
}

fun query(node: Int, lf: Int, rg: Int): Long{
    if (lf > rg) return Long.MAX_VALUE
    if (tree[node].left >= lf && tree[node].right <= rg){
        return tree[node].mn + tree[node].upd
    }else{
        val mid = ((tree[node].left.toLong() + tree[node].right.toLong())/2).toInt()
        if (rg <= mid){
            return query(node*2, lf, rg) + tree[node].upd
        }else if (lf > mid){
            return query(node*2+1, lf, rg)  + tree[node].upd
        }else{
            val l = query(node*2, lf, rg)
            val r = query(node*2+1, lf, rg)
            return minOf(l, r) + tree[node].upd
        }
    }
}

fun update(node: Int, lf: Int, rg: Int, v: Long){
    if (tree[node].left >= lf && tree[node].right <= rg){
        tree[node].upd += v
    }else{
        val mid = ((tree[node].left.toLong() + tree[node].right.toLong())/2).toInt()
        if (rg <= mid){
            update(node*2, lf, rg, v)
        }else if (lf > mid){
            update(node*2+1, lf, rg, v)
        }else{
            update(node*2, lf, rg, v)
            update(node*2+1, lf, rg, v)
        }
        tree[node].mn = minOf(tree[node*2].mn + tree[node*2].upd, tree[node*2+1].mn + tree[node*2+1].upd)
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toLong()
    a = LongArray(200005)
    var st = StringTokenizer(readLine())
    repeat(n.toInt()) { i ->
        a[i + 1] = st.nextToken().toLong()
    }
    val m = readLine().toLong()
    build(1, 1, n.toInt())

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(m.toInt()) {
            st = StringTokenizer(readLine())
            val l = st.nextToken().toLong()
            val r = st.nextToken().toLong()
            if (st.hasMoreTokens()) { // update
                val v = st.nextToken().toLong()
                if (l <= r){
                    update(1, l.toInt(), r.toInt(), v)
                }else{
                    update(1, l.toInt(), n.toInt(), v)
                    update(1, 1, r.toInt(), v)
                }

            } else { // query min value
                if (l<=r){
                    w.write("${query(1, l.toInt(), r.toInt())}\n")
                }else{
                    w.write("${minOf(query(1, l.toInt(), n.toInt()),query(1, 1, r.toInt()))}\n")
                }
            }
        }
        w.flush()
        w.close()
    }
    close()

}