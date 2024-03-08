package map;

public class Pair <K,V>{
    private K key;
    private V value;
    private boolean deleted;


    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
        deleted = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString(){
        return key.toString() + "," + value.toString();
    }
}
