import java.util.HashMap;

class LFUCache {

    private static class Node {
        final int key;
        int value;
        int frequency;
        Node prev;
        Node next;

        Node(int key, int value){
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }
    private static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList(){
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void add(Node node){
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;

            size++;
        }

        void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast(){
            if (size == 0) {
                return null;
            }

            Node node = tail.prev;
            remove(node);

            return node;
        }
    }

    private int capacity;
    private int minFrequency;

    private HashMap<Integer, Node> nodes;
    private HashMap<Integer, DoublyLinkedList> frequencyMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;

        nodes = new HashMap<>();
        frequencyMap = new HashMap<>();
    }

    public int get(int key){

        if (!nodes.containsKey(key)){
            return -1;
        }

        Node node = nodes.get(key);
        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value){

        if (capacity == 0){
            return;
        }

        if (nodes.containsKey(key)){
            Node node = nodes.get(key);
            node.value = value;

            updateFrequency(node);
            return;
        }

        if (nodes.size() == capacity){

            DoublyLinkedList list =
                frequencyMap.get(minFrequency);

            Node removed = list.removeLast();
            nodes.remove(removed.key);
        }

        Node newNode = new Node(key, value);

        nodes.put(key, newNode);

        frequencyMap
            .computeIfAbsent(1, k -> new DoublyLinkedList())
            .add(newNode);

        minFrequency = 1;
    }

    private void updateFrequency(Node node){

        int oldFrequency = node.frequency;

        DoublyLinkedList oldList =
            frequencyMap.get(oldFrequency);

        oldList.remove(node);

        if (oldFrequency == minFrequency && oldList.size == 0){
            minFrequency++;
        }

        node.frequency++;

        frequencyMap
            .computeIfAbsent(node.frequency,
                k -> new DoublyLinkedList())
            .add(node);
    }
}