package DataStructures;

import java.util.Arrays;

public class OurArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private Object[] elementData;

    private int size;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public OurArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public OurArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureExplicitCapacity(int minCapacity) {
//        modCount++;
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }


    /*
        --------------------------------------------------------------------------------------------------------
        Coding Interview Preparation | OurArrayList Add at Index (Insert) and remove at index Implementation | 4
        --------------------------------------------------------------------------------------------------------

     */

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }


    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    public E remove(int index) {
        rangeCheck(index);

//        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


    E elementData(int index) {
        return (E) elementData[index];
    }


    /*
        --------------------------------------------------------------------------------------------------------
        Coding Interview Preparation | OurArrayList remove given object Implementation | 5
        --------------------------------------------------------------------------------------------------------

     */

    public boolean myRemove(Object o) {
        for (int index = 0; index < size; index++) {
            if (isNullAndFound(o, index) || isNotNullAndFound(o, index)) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    private boolean isNullAndFound(Object o, int index) {
        return o == null && elementData[index] == null;
    }

    private boolean isNotNullAndFound(Object o, int index) {
        return o != null && o.equals(elementData[index]);
    }





    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
//        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }


    /*
        --------------------------------------------------------------------------------------------------------
        Coding Interview Preparation | OurArrayList set, get and clear methods Implementation | 6
        --------------------------------------------------------------------------------------------------------

     */

    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public void clear() {
//        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }


    /*
        --------------------------------------------------------------------------------------------------------
        Coding Interview Preparation | OurArrayList indexOf, lastIndexOf, contains, size and isEmpty methods Implementation | 7
        --------------------------------------------------------------------------------------------------------

     */


    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    /**
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     * @param dest    the destination array.
     * @param destPos starting position in the destination data.
     * @param length  the number of array elements to be copied.
     * @throws IndexOutOfBoundsException if copying would cause
     *                                   access of data outside array bounds.
     * @throws ArrayStoreException       if an element in the <code>src</code>
     *                                   array could not be stored into the <code>dest</code> array
     *                                   because of a type mismatch.
     * @throws NullPointerException      if either <code>src</code> or
     *                                   <code>dest</code> is <code>null</code>.
     */
    public static native void arraycopy(Object src, int srcPos,
                                        Object dest, int destPos,
                                        int length);

}
