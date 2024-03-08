package map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomIterator <K,V> implements Iterator<Pair<K, V>> {
    private final CustomHashMap<K,V> map;
    private int curr = -1;
    private int visited = 0;

    public CustomIterator(CustomHashMap<K,V> map) {
        this.map = map;
        find();
    }

    @Override
    public boolean hasNext() {
        return map.getSize() > visited;
    }

    @Override
    public Pair<K, V> next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }

        Pair<K,V> pair = map.getMap()[curr];
        visited++;
        find();
        return  pair;
    }

    public void find(){
        for(int i = curr + 1; i < map.getMap().length; i++){
            if(map.getMap()[i] != null && !map.getMap()[i].isDeleted()){
                curr = i;
                return;
            }
        }

        curr = - 1;
    }
}
