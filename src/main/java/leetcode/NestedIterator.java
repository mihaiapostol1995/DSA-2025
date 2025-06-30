package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList<>();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        list.addAll(addAll(nestedList));
    }

    private List<Integer> addAll(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        for (var nested : nestedList) {
            if (nested.isInteger()) {
                list.add(nested.getInteger());
            } else {
                list.addAll(addAll(nested.getList()));
            }
        }
        return list;
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}