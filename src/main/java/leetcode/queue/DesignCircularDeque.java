package leetcode.queue;

class DesignCircularDeque {
    public static void main(String[] args) {

    }


}

// GOOD problem! smart solution
// 0 1 2 3 4
// 0 is the rear, 4 is the front!
class MyCircularDeque {

    int[] arr;
    int capacity;
    int front = 0;
    int rear = -1;
    int size;

    public MyCircularDeque(int k) {
        capacity = k;
        arr = new int[capacity];
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (capacity + front - 1) % capacity;
        arr[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear + capacity - 1) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : arr[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : arr[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}