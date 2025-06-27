package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> list;
    Integer peek;

    public PeekingIterator(Iterator<Integer> iterator) {
        list = iterator;
        if (list.hasNext()) {
            peek = list.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        var temp = peek;
        if (list.hasNext()) {
            peek = list.next();
        } else {
            peek = null;
        }
        return temp;
    }

    @Override
    public boolean hasNext() {
        return peek != null;
    }
}