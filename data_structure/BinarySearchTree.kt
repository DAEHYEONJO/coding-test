package data_structure

data class Node(var key: Int, var left: Node? = null, var right: Node? = null)

fun search(root: Node, target: Int): Node?{
    var p: Node? = root

    while (p!=null){
        p = when {
            p.key == target -> return p
            p.key < target -> p.right
            else -> p.left
        }
    }
    return null
}

fun insert(root: Node?, target: Int): Node? {
    var p: Node? = root
    var parent: Node? = null

    while (p!=null){
        parent = p
        p = when {
            p.key == target -> return p
            p.key < target -> p.right
            else -> p.left
        }
    }

    val newNode = Node(target)
    if (parent!=null){
        if (parent.key < target) parent.right = newNode
        else if (parent.key > target) parent.left = newNode
    }
    return newNode
}

//delete의 return 값은 root node이다. 왜냐하면 root node가 delete 되는 경우, 밖에서 갖고 있는 root node 주소는 의미가 없기 때문이다.
fun delete(root: Node?, target: Int): Node? {
    var p: Node? = root
    var parent: Node? = null

    while (p!=null && p.key != target){
        parent = p
        p = when {
            p.key < target -> p.right
            else -> p.left
        }
    }

    if (p == null) {
        println("delete 불가능")
    }else{
        if (p.left==null && p.right==null){
            if (parent==null){
                return null
            }else{
                if (parent.left == p) parent.left = null
                else parent.right = null
            }
        }else if (p.left == null || p.right == null){
            val child = p.left ?: p.right

            if (parent == null){
                root?.run {
                    key = child!!.key
                    left = child.left
                    right = child.right
                }
            }else{
                if (parent.left == p) parent.left = child
                else parent.right = child
            }
        }else {
            var rightSubMinNode = p.right
            var rightSubMinNodeParent = p

            while (rightSubMinNode?.left != null){
                rightSubMinNodeParent = rightSubMinNode
                rightSubMinNode = rightSubMinNode.left
            }

            p.key = rightSubMinNode!!.key
            if (rightSubMinNodeParent?.left == rightSubMinNode){
                rightSubMinNodeParent.left = rightSubMinNode.right
            }else{
                rightSubMinNodeParent?.right = rightSubMinNode.right
            }
            p = rightSubMinNode
        }
        p = null
    }
    return root
}

fun inorderTraversal(root: Node?){
    if (root == null) return
    inorderTraversal(root.left)
    print("${root.key} ")
    inorderTraversal(root.right)
}

fun preorderTraversal(root: Node?){
    if (root == null) return
    print("${root.key} ")
    preorderTraversal(root.left)
    preorderTraversal(root.right)
}
//10 6 4 8 12 15 14 20

fun postorderTraversal(root: Node?){
    if (root == null) return
    postorderTraversal(root.left)
    postorderTraversal(root.right)
    print("${root.key} ")
}
//4 8 6 14 20 15 12 10


var i = 0
val arr = IntArray(8)
fun checkBSTInorderTraversal(root: Node?, arr: IntArray){
    if (root == null) return
    checkBSTInorderTraversal(root.left, arr)
    arr[i++] = root.key
    checkBSTInorderTraversal(root.right, arr)
}

fun checkBST(arr:IntArray):Boolean{
    for (i in 0 until arr.size-1){
        if (arr[i]>=arr[i+1]) return false
    }
    return true
}

var value: Int = Int.MIN_VALUE
fun checkBSTInorderTraversalNoArr(root: Node?):Boolean{
    if (root == null) return true
    if (!checkBSTInorderTraversalNoArr(root.left)) return false
    if (root.key < value){
        return false
    }
    value = root.key
    if (!checkBSTInorderTraversalNoArr(root.right)) return false
    return true
}

fun checkBST(root: Node?, lowerBoundExist: Boolean,
             upperBoundExist: Boolean, lowerBound: Int, upperBound: Int): Boolean{
    if (root == null) return true

    if (lowerBoundExist && root.key <= lowerBound) return false
    if (upperBoundExist && root.key >= upperBound) return false

    return checkBST(root.left, lowerBoundExist, true, lowerBound, root.key)
            && checkBST(root.right, true, upperBoundExist, root.key, upperBound)
}

fun main() {
    var root = insert(null, 10)
    insert(root, 6)
    insert(root, 4)
    insert(root, 8)
    insert(root, 12)
    insert(root, 15)
    insert(root, 14)
    insert(root, 20)
    println()
    inorderTraversal(root)
    println()

    //          6
    //      4       12
    //    3   5       65
    //  1
    println()
    preorderTraversal(root)
    println()

    println()
    postorderTraversal(root)
    println()


    checkBSTInorderTraversal(root, arr)
    arr.forEach{
        println(it)
    }
    println(checkBST(arr))
    println(checkBSTInorderTraversalNoArr(root))
    println(checkBST(root, false, false, 0, 0))

}

