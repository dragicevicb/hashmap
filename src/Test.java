import map.CustomHashMap;
import map.Pair;

public class Test {
    public static void test1(){
        CustomHashMap<Integer, String> map = new CustomHashMap<>(null);
        System.out.println("Putting new values");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        System.out.println("Number of elements in map after put: " + map.getSize());
        System.out.println(map);

        System.out.println("Deleting some values");
        map.delete(1);
        map.delete(3);
        map.delete(9); //should do nothing
        System.out.println("Number of elements in map after delete: " + map.getSize());
        System.out.println(map);
    }

    public static void test2(){
        CustomHashMap<Integer, String> map = new CustomHashMap<>(null);

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");

        System.out.println(map.get(1));
        System.out.println(map.get(3));
        System.out.println(map.get(5));
    }

    public static void test3(){
        CustomHashMap<String, Integer> map = new CustomHashMap<>(null);
        //All have hash 0 for size 2^4
        map.put("AaAa", 10);
        map.put("BBBB", 11);
        map.put("AaBB", 12);
        map.put("BBAa", 13);
        System.out.println(map);

        //Testing get after collision
        System.out.println(map.get("AaBB"));
        System.out.println(map.get("BBAa"));

        //Testing delete after collison
        map.delete("AaAa");
        map.delete("AaBB");

        System.out.println(map);
    }

    public static void test4(){
        CustomHashMap<Integer, String> map = new CustomHashMap<>(2);
        System.out.println(map.getTableSize());

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        System.out.println(map.getTableSize());

        //Testing put after resize
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        System.out.println(map);

        //Testing get after resize
        System.out.println(map.get(1) + " should be: one");
        System.out.println(map.get(4) + " should be: four");

        //Testing delete after resize
        map.delete(2);
        map.delete(6);
        System.out.println(map);
    }

    public static void test5(){
        CustomHashMap<String, Integer> map = new CustomHashMap<>(2);

        map.put("AaAaAa", 1);
        map.put("AaAaBB", 2);
        System.out.println(map);

        map.put("AaBBAa", 3);
        map.put("BBAaAa", 4);
        map.put("BBAaBB", 5);
        System.out.println(map);

        //Testing get
        System.out.println(map.get("BBAaAa") + " should be: 4");
        System.out.println(map.get("AaBBAa") + " should be: 3");

        //Testing delete
        map.delete("AaAaAa");
        map.delete("AaBBAa");
        System.out.println(map);
        System.out.println(map.get("AaAaAa") + " should be: null");
    }

    public static void test6(){
        CustomHashMap<String, Integer> map = new CustomHashMap<>(null);
        map.put("AaAaAa", 1);
        map.put("AaAaBB", 2);
        map.put("AaBBAa", 3);
        map.put("BBAaAa", 4);
        map.put("BBAaBB", 5);
        map.put("AaAa", 10);
        map.put("BBBB", 11);
        map.put("AaBB", 12);
        map.put("BBAa", 13);

        System.out.println(map);
    }

    public static void main(String[] args) {
        System.out.println("Test 1 - put i delete");
        test1();
        System.out.println("Test 2 - get");
        test2();
        System.out.println("Test 3 - collisions");
        test3();
        System.out.println("Test 4 - resize");
        test4();
        System.out.println("Test 5 - collisions and resize");
        test5();
        System.out.println("Test 6 - iterator");
        test6();
    }
}
