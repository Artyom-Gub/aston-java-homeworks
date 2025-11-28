package www.aston.com.task1;

import www.aston.com.task1.components.UserHashMap;

public class Main {
    public static void main(String[] args) {
        UserHashMap userHashMap = new UserHashMap();

        userHashMap.put("Иван", 24);
        userHashMap.put("Артём", 25);
        userHashMap.put("Маша", 24);
        System.out.println(userHashMap.get("Иван"));

        userHashMap.remove("Маша");
        System.out.println(userHashMap.get("Маша"));

        userHashMap.put("Иван", 28);
        System.out.println(userHashMap.get("Иван"));
    }
}