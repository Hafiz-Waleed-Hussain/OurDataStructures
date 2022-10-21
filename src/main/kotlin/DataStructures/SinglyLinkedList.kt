package DataStructures

fun main() {


    val list = SinglyLinkedList<Int>()
    repeat(10) {
        list.addLast(it)
    }
    println(list)


    list.clear()
    println(list)

    repeat(10) {
        list.addLast(it)
    }
    println(list)

    list.clear()
    println(list)

}

/*
-------------------------------------------------- ------------------------------------------------------
Coding Interview Preparation | SinglyLinkedList addFirst and size methods Implementation | 2
--------------------------------------------------------------------------------------------------------
 */

data class SinglyLinkedListNode<T>(val value: T, var nextNode: SinglyLinkedListNode<T>? = null)

class SinglyLinkedList<T> {


    private var head: SinglyLinkedListNode<T>? = null
    private var tail: SinglyLinkedListNode<T>? = null

    var size: Int = 0
        private set

    fun addFirst(value: T) {
        head = SinglyLinkedListNode(value, head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList addLast method Implementation | 3
    --------------------------------------------------------------------------------------------------------
     */

    fun addLast(value: T) {
        if (head == null) {
            addFirst(value)
            return
        }
        tail?.nextNode = SinglyLinkedListNode(value)
        tail = tail?.nextNode
        size++
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList add at specific index method Implementation | 4
    --------------------------------------------------------------------------------------------------------
     */

    fun add(index: Int, value: T) {
        checkPositionIndex(index)
        if (index == size) {
            addLast(value)
        } else {
            val node = getNode(index)
            node.nextNode = SinglyLinkedListNode(value, node.nextNode)
            size++
        }
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList remove First method Implementation | 5
    --------------------------------------------------------------------------------------------------------
     */

    fun removeFirst(): T? {
        if (!isEmpty()) size--
        val value: T? = head?.value
        val temp = head
        head = head?.nextNode
        temp?.nextNode = null

        if (isEmpty()) {
            tail = null
        }
        return value
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList remove Last method Implementation | 6
    --------------------------------------------------------------------------------------------------------
     */

    fun removeLast(): T? {
        head ?: return null
        if (head == tail) {
            return removeFirst()
        }
        size--
        val node = getNode(size - 1)
        val value = tail?.value
        tail = node
        node.nextNode = null
        return value
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList remove At method Implementation | 7
    --------------------------------------------------------------------------------------------------------
     */


    fun removeAt(index: Int): T? {
        if (index < 0 || index >= size) return null
        if (index == 0) return removeFirst()
        if (index == size - 1) return removeLast()
        val node = getNode(index - 1)
        val indexNode = node.nextNode
        val value = indexNode?.value

        node.nextNode = indexNode?.nextNode
//        node.nextNode = node.nextNode?.nextNode
        indexNode?.nextNode = null
        return value
    }


    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | SinglyLinkedList clear method Implementation | 8
    --------------------------------------------------------------------------------------------------------
     */

    fun clear(){
        size = 0
        head = null
        tail = null
    }







    fun isEmpty(): Boolean {
        return size == 0
    }


    private fun getNode(index: Int): SinglyLinkedListNode<T> {
        checkElementIndex(index)
        var count = 0
        var current = head
        while (index > count) {
            count++
            current = current?.nextNode
        }
        return current!!
    }

    private fun checkPositionIndex(index: Int) {
        if (!isPositionIndexValid(index)) {
            throw IndexOutOfBoundsException(outOfBoundMessage(index))
        }
    }

    private fun checkElementIndex(index: Int) {
        if (!isElementIndexValid(index)) {
            throw IndexOutOfBoundsException(outOfBoundMessage(index))
        }
    }

    private fun isElementIndexValid(index: Int) = index >= 0 && index < size

    private fun isPositionIndexValid(index: Int) = index >= 0 && index <= size


    private fun outOfBoundMessage(index: Int): String {
        return "index: $index, size: $size"
    }


    override fun toString(): String {
        return head.toString()
    }

}

