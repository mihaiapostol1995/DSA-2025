package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class InsertDeleteGetRandomO1 {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomizedSet.insert(1); // Returns false as 2 does not exist in the set.
        randomizedSet.remove(0); // Inserts 2 to the set, returns true. Set now contains [1,2].
        randomizedSet.insert(2); // getRandom() should return either 1 or 2 randomly.
        randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
        randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

    }
}

class RandomizedSet {

    List<Integer> list;
    Map<Integer, Integer> map;
    Random rand;

    public RandomizedSet() {
        list = new LinkedList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        var last = list.getLast();

        list.set(index, last);
        map.put(last, index);
        list.set(list.size() - 1, val);

        map.remove(val);
        list.removeLast();

        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
