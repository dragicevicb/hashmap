package map;

import java.util.Iterator;

public class CustomHashMap <K,V> implements Iterable<Pair<K,V>>{
    private Pair<K,V>[] map;
    //Argumenti za odredjivanje resize-a
    int tableSize;
    int size;
    double threshold = 0.7;
    //Argumenti za kvadratno trazenje indeksa
    int c1 = 1;
    int c2 = 2;

    public CustomHashMap(Integer initialSize) {
        if(initialSize  != null) {
            this.tableSize = (int) Math.pow(2, initialSize);
            this.map = (Pair<K,V>[]) new Pair[tableSize];
        }else{
            this.tableSize = (int) Math.pow(2, 4);
            this.map = (Pair<K,V>[]) new Pair[tableSize];
        }
        this.size = 0;
    }

    public void put(K key, V value){
        int index = Math.abs(key.hashCode()%tableSize);
        int i = 0;

        while(map[index] != null && !map[index].getKey().equals(key) && !map[index].isDeleted()){
            i++;
            index = (index + c1 * i + c2 * i * i) % tableSize;
        }

        if(map[index] == null){
            size++;
        }

        map[index] = new Pair<>(key, value);

        if (1.0 * size/tableSize >= threshold){
            resize();
        }
    }

    public V get(K key){
        int index = Math.abs(key.hashCode()%tableSize);
        int i = 0;
        while (map[index] != null){
            if(!map[index].isDeleted() && map[index].getKey().equals(key)){
                return map[index].getValue();
            }
            i++;
            index = (index + c1 * i + c2 * i * i) % tableSize;
        }

        return null;
    }

    public void delete(K key){
        int index = Math.abs(key.hashCode()%tableSize);
        int i = 0;
        while (map[index] != null){
            if(map[index].getKey().equals(key) && !map[index].isDeleted()){
                map[index].setDeleted(true);
                size--;
                return;
            }
            i++;
            index = (index + c1 * i + c2 * i * i) % tableSize;
        }
    }

    public void resize(){
        tableSize = tableSize * 2;
        Pair<K, V>[] newMap = (Pair<K, V>[]) new Pair[tableSize];
        for (Pair pair : map){
            if(pair != null && !pair.isDeleted()){
                int index = Math.abs(pair.getKey().hashCode() % tableSize);
                int i = 0;
                while (newMap[index] != null){
                    i++;
                    index = (index + c1 * i + c2 * i * i) % tableSize;
                }

                newMap[index] = pair;
            }
        }

        map = newMap;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        int first = 1;
        for(Pair pair : this){
            if (first == 0){
                sb.append(",\n");
            }
            sb.append(pair.getKey()).append("=").append(pair.getValue());
            first = 0;
        }
        sb.append("\n}");

        return sb.toString();
    }
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new CustomIterator<>(this);
    }

    public int getTableSize() {
        return tableSize;
    }

    public int getSize() {
        return size;
    }

    public Pair<K, V>[] getMap() {
        return map;
    }
}
