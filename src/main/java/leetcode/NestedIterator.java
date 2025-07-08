package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    public NestedInteger() {
        list = new ArrayList<>();
    }

    public NestedInteger(int value) {
        this.value = value;
    }

    public boolean isInteger() {
        return value != null;
    }

    public Integer getInteger() {
        return value;
    }

    public void add(NestedInteger ni) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(ni);
    }

    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public String toString() {
        if (isInteger()) {
            return String.valueOf(value);
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
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