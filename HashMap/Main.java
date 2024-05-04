package HashMap;


class CustomHashMap<K, V> {
    private int INITIAL_CAP = (1<<4);
    private int MAX_CAP = (1<<30);
    private int current_max_size = INITIAL_CAP;
    private double load_factor = 0.5;
    private int key_count = 0;

    class Node<K, V> {
        K key;
        V value;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

    }

    Node table[];

    int getNearestPowerOfTwo(int required) {
        int curr = (1<<4);
        while((curr <= (1<<30)) && curr < required)
            curr = curr<<1;
        return curr;
    }

    CustomHashMap() {
        table = new Node[INITIAL_CAP];
    }
    CustomHashMap(int initial_size, int load_factor) {
        this.load_factor = load_factor;
        int initial_cap = getNearestPowerOfTwo(initial_size);
        table = new Node[initial_cap];
        current_max_size = initial_cap;
    }

    void recomputeHashTable() {
        System.out.println("Recomputing the hash table");
        int new_size = getNearestPowerOfTwo(current_max_size+1);
        Node newtable[] = new Node[new_size];
        for(int i=0;i<current_max_size;i++) {
            Node currentLevelIt = table[i];
            while(currentLevelIt != null) {
                insert(newtable, (K)currentLevelIt.key, (V)currentLevelIt.value, new_size);
                currentLevelIt = currentLevelIt.next;
            }
        }
        table = newtable;
        current_max_size = new_size;
    }

    void insert(Node table[], K key, V value, int current_max_size) {
        int hashcode = key.hashCode()%current_max_size;
        Node node = table[hashcode];
        Node newNode = new Node(key, value);
        if(node == null) {
            table[hashcode] = newNode;
        } else {
            Node iterator = node;
            while(iterator != null) {
                if(iterator.key.equals(key)) {
                    key_count--;
                    iterator.value = value;
                    return;
                }
                node = iterator;
                iterator = iterator.next;
            }
            node.next = newNode;
        }
    }

    void put(K key, V value) {
        System.out.println("Trying to insert: " + value);
        System.out.println("key_count: " + key_count);
        System.out.println("lf*max_size: " + load_factor*current_max_size);
        key_count++;
        if(key_count >= load_factor*current_max_size)
            recomputeHashTable();
        insert(table, key, value, current_max_size);
    }

    V get(K key) {
        int hashcode = key.hashCode()%current_max_size;
        Node node = table[hashcode];

        while(node != null) {
            if(node.key.equals(key)) {
                return (V) node.value;
            }
            node = node.next;
        }

        return null;
    }

    void print() {
        for(int i=0;i<current_max_size;i++) {
            System.out.print("For index: " + i);
            Node iterator = table[i];
            while(iterator != null) {
                System.out.print("\t{" + iterator.key + " , "  + iterator.value + "}\t");
                iterator = iterator.next;
            }
            System.out.println();
        }
    }

}


class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}


public class Main {
    public static void main(String[] args) {
//        CustomHashMap<Integer, String> map = new CustomHashMap<>();
//        for(int i=1;i<=16;i++) {
//            String curr_string = Integer.toString(i);
//            map.put(i, curr_string);
//        }
//        map.print();
//        System.out.println(map.get(2));
//        System.out.println(map.get(5));

        Person p1 = new Person("Alec");
        Person p2 = new Person("Alec");
        //hash codes of p1 and p2 are the same.
        //example of collision on the basis of keys.
        CustomHashMap<Person, String> map = new CustomHashMap<>();
        map.put(p1, "P1 person");
        map.put(p2, "P2 person");
        map.print();
        System.out.println(map.get(p2));
    }
}
