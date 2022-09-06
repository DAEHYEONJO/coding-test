package greedy

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    //멀티탭에 n개 코드 꽂기
    //다음에 꽂을 코드가 이미 꽂혀 있는 경우 ok
    //다음에 꽂을 코드가 꽂혀있지 않는 경우 꽂혀 있는 것 중 앞으로 안 쓸 코드 있는지 찾기
    //앞으로 안 쓸 코드가 없으면 이미 꽂혀 있는 코드 중 제일 마지막에 꽂아야할 코드를 뺀다
    //왜나하면 제일 마지막에 꽂아야할 코드 이전에 다른 코드들을 꽂아야 하면
    //해당 코드들은 멀티탭에 꽂혀 있으면 교체해줄 필요가 없기 때문이다.


    val (n, k ) = readLine().split(" ").map{it.toInt()}
    val list = readLine().split(" ").map { it. toInt() }.toList()

    var count = 0

    val multiTab = HashSet<Int>()

    out@for (i in 0 until k){
        if (multiTab.contains(list[i])){
            continue@out // 이미 꽂혀 있으므로 pass
        }
        if (multiTab.size<n){
            // 멀티탭 비어있으므로 그냥 꽂기
            multiTab.add(list[i])
            continue@out
        }

        /*var value = 0
        for (j in i+1 until k){
            if (multiTab.contains(list[j])){
                //list[j]가 멀티탭에 있단 얘기이므로 추후에 또 등장한단 말이다
                value = list[j]
            }else{
                //틀린이유 1) 이거는 멀티탭에 list[j]가 안꽂혀 있다는 것이다. 멀티탭에 꽂혀있는 원소가 추후에 등장하는 리스트에 안나온단 얘기가 아니라;
                value = list[j]
                break
            }
        }*/

        var removeValue = 0
        var index = -1
        var laterConsents = list.subList(i+1, k)//틀린이유 2) 이걸 set으로 만들고 contains를 해버려갖고 나중에 등장하는 콘센트 판단 못했음
        for (consent in multiTab){
            if (laterConsents.contains(consent)){
                var consentIndex = laterConsents.indexOf(consent)
                if (consentIndex>index){
                    // 젤 나중에 등장하는 콘센트 골라주기
                    index = consentIndex
                    removeValue = consent
                }
            }else{
                // 이 콘센트는 나중에 등장 안함
                removeValue = consent
                break
            }
        }
        multiTab.remove(removeValue)
        multiTab.add(list[i])
        count++

    }

    println(count)

}