package org.example;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("A", "B");
        myHashMap.put("E", "F");
        myHashMap.put("H", "P");
        myHashMap.put("P", "2");
        myHashMap.put("1",  "G");
        myHashMap.put("2", "6");
        myHashMap.put("3", "2");
        myHashMap.put("4", "4");
        myHashMap.put("1",  "H");

        System.out.println(myHashMap);
        System.out.println(myHashMap.get("A"));
        myHashMap.remove("2");
        System.out.println(myHashMap.containsKey("2"));
        System.out.println(myHashMap);
        System.out.println(myHashMap.size());
    }
}