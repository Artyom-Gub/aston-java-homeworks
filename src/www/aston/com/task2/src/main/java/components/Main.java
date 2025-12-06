package www.aston.com.task2.src.main.java.components;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Создать | 2. Прочесть все | " +
                    "3. Прочесть по ID | 4. Обновить | 5. Удалить | 0. Выйти");
            System.out.print("Выбор: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": create(); break;
                case "2": readAll(); break;
                case "3": readById(); break;
                case "4": update(); break;
                case "5": delete(); break;
                case "0": return;
                default: System.out.println("Неправильный пункт списка");
            }
        }
    }

    private static void create() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        Integer age = Integer.parseInt(scanner.nextLine());

        User user = new User(name, email, age);
        userDao.create(user);
        System.out.println("Пользователь создан: " + user);
    }

    private static void readAll() {
        userDao.findAll().forEach(System.out::println);
    }

    private static void readById() {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        userDao.findById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Не найдено")
        );
    }

    private static void update() {
        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        userDao.findById(id).ifPresent(user -> {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                user.setName(name);
            }

            System.out.print("Email: ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                user.setEmail(email);
            }

            System.out.print("Age: ");
            String ageStr = scanner.nextLine();
            if (!ageStr.isEmpty()) {
                user.setAge(Integer.parseInt(ageStr));
            }

            userDao.update(user);
            System.out.println("Обновленный пользователь: " + user);
        });
    }

    private static void delete() {
        System.out.print("ID: ");

        Long id = Long.parseLong(scanner.nextLine());
        userDao.delete(id);

        System.out.println("Пользователь удален");
    }
}