package sorting


val unsortedArr = intArrayOf(8, 5, 6, 2, 1, 9, 8, 7, 4, 3)
val sortedArr = IntArray(unsortedArr.size)

fun main() {
    mergeSort(unsortedArr, 0, unsortedArr.size-1)
    println("\nresult unsortedArr\n")
    unsortedArr.forEach {
        print("$it ")
    }
    println()

    println("\nresult sortedArr\n")
    sortedArr.forEach {
        print("$it ")
    }
    println()
}

fun mergeSort(arr: IntArray, left: Int, right: Int) {


    //0 ~ 9 가 0,4,9 호출(3) 0~4, 5~9 merge
    //5 ~ 9 가 5,7,9 호출(3) 5~7, 8~9 merge
    //8 ~ 9 가 8,8,9 호출(3) 8과 9 merge
    //8 ~ 9 가 9 ~ 9 호출(2) if 문 탈락
    //8 ~ 9 가 8 ~ 8 호출(1) if 문 탈락
    //5 ~ 9 가 8 ~ 9 호출(2)
    //5 ~ 7 이 5,6,7 호출(3) 5~6배열과 7배열 merge
    //5 ~ 7 이 7 ~ 7 호출(2) 하지만 if 문에 탈락
    //5 ~ 6 이 5,5,6 호출(3) 5배열과 6배열은 각 각 1개 원소 이므로 merge
    //5 ~ 6 이 6 ~ 6 호출(2) 하지만 if 문에 탈락
    //5 ~ 6 이 5 ~ 5 호출(1) 하지만 if 문에 탈락
    //5 ~ 7 이 5 ~ 6 호출(1)
    //5 ~ 9 가 5 ~ 7 호출(1)
    //0 ~ 9 가 5 ~ 9 호출(2)

    //0 ~ 4 가 0,2,4 호출(3) 0~2배열과 3~4배열 각 각 sorting 되어 있으므로 merge
    //3 ~ 4 가 3,3,4 호출(3) 3배열과 4배열은 원소 1개 씩이므로 각 각 sorting 되어 있다고 할 수 있다. 따라서 sorting 된 두 배열을 merge
    //3 ~ 4 가 4 ~ 4 호출(2)하지만 if 문 탈락
    //3 ~ 4 가 3 ~ 3 호출(1)하지만 if 문 탈락
    //0 ~ 4 가 3 ~ 4 호출(2)
    //0 ~ 2 가 0,1,2 호출(3) 0~1은 sorting 되어 있고, 0~1 배열과, 2 배열을 merge
    //0 ~ 2 가 2 ~ 2 호출(2)하지만 if 문에 탈락
    //0 ~ 1 이 0,0,1 호출(3) -> 0 ~ 1 sorting
    //0 ~ 1 이 1 ~ 1 호출(2)하지만 if 문에 탈락
    //0 ~ 1 이 0 ~ 0 호출(1)하지만 if 문에 탈락
    //0 ~ 2 가 0 ~ 1 호출(1)
    //0 ~ 4 가 0 ~ 2 호출(1)
    //0 ~ 9 가 0 ~ 4 호출(1)
    //0 ~ 9 호출
    if(left>=right) println("if문 탈락 mergeSort left: $left right: $right")

    if (left < right) {
        val mid = (left + right).div(2)
        println(" mergeSort left: $left mid: $mid right: $right")

        mergeSort(arr, left, mid)//1
        mergeSort(arr, mid + 1, right)//2
        merge(arr, left, mid, right)//3

    }
}

fun merge(arr: IntArray, left: Int, mid: Int, right: Int){
    println(" merge left: $left mid: $mid right: $right")
    var i = left
    var j = mid+1
    var k = left

    while (i<=mid && j<=right){
        if (arr[i]<=arr[j]){
            sortedArr[k] = arr[i++]
        }else{
            sortedArr[k] = arr[j++]
        }
        k++
    }

    if (i > mid){
        for (index in j .. right){
            sortedArr[k++] = arr[index]
        }
    }else{
        for (index in i .. mid){
            sortedArr[k++] = arr[index]
        }
    }

    println("---------sortedArr----------")
    sortedArr.forEach{
        print("$it ")
    }


    for (index in left .. right) {
        unsortedArr[index] = sortedArr[index]
    }

    println("\n---------unsortedArr----------")
    unsortedArr.forEach{
        print("$it ")
    }
    println("\t\n")
}