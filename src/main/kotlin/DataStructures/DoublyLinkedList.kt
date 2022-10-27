package DataStructures

import java.lang.IndexOutOfBoundsException

fun main() {
    val list = DoublyLinkedList<Int>()
    list.addLast(0)
    list.addLast(1)
    list.addLast(2)
    list.addLast(3)
    list.addLast(4)
    list.addLast(5)

    list.add(100, 0)
    println(list)

}

data class Node<T>(val value: T, var nextNode: Node<T>? = null, var prevNode: Node<T>? = null) {
    override fun toString(): String {
        return buildString {
            if (prevNode == null) append("NULL ") else append("PN=(${prevNode?.value}) ")
            append("$value ")
            if (nextNode == null) append("NULL ") else append("NN=NOT NULL\n${nextNode}")
        }
    }
}

class DoublyLinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size: Int = 0
        private set

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | DoublyLinkedList addFirst method Implementation | 3
    --------------------------------------------------------------------------------------------------------
     */

    fun addFirst(value: T) {
        head = Node(value, head)
        head?.nextNode?.prevNode = head
        if (isEmpty()) {
            tail = head
        }
        size++
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | DoublyLinkedList addLast method Implementation | 5
    --------------------------------------------------------------------------------------------------------
     */

    fun addLast(value: T) {
        if (isEmpty()) {
            return addFirst(value)
        }

        val node = Node(value, null, tail)
        tail?.nextNode = node
        tail = node
        size++
    }

    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | DoublyLinkedList add At Index method Implementation | 6
    --------------------------------------------------------------------------------------------------------
     */

    fun add(value: T, index: Int) {
        checkIndexPosition(index)
        if (index == 0) {
            return addFirst(value)
        }
        if (index == size) {
            return addLast(value)
        }
        size++

        val current = getNode(index - 1)
        val node = Node(value)
        node.nextNode = current?.nextNode
        node.prevNode = current
        current?.nextNode?.prevNode = node
        current?.nextNode = node
    }


    /*
    --------------------------------------------------------------------------------------------------------
    Coding Interview Preparation | DoublyLinkedList Node and toString method Implementation | 2
    --------------------------------------------------------------------------------------------------------
     */
    override fun toString(): String {
        return "[$head]"
    }


    fun isEmpty(): Boolean {
        return size == 0
    }

    private fun getNode(index: Int): Node<T>? {
        var current = head
        var count = 0
        while (count < index) {
            current = current?.nextNode
            count++
        }
        return current
    }

    private fun checkIndexPosition(index: Int) {
        if (!isIndexPositionValid(index)) throw IndexOutOfBoundsException(exceptionMessage(index))
    }

    private fun isIndexPositionValid(index: Int): Boolean {
        return index >= 0 && index <= size
    }

    private fun exceptionMessage(index: Int): String {
        return "Index $index is invalid : $size"
    }

}