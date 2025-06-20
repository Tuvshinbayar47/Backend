package ToDoApp;
import java.io.*;
import java.util.*;

public class TodoApp {
    private static final String FILE_NAME = "tasks.txt";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📋 TODO APP MENU");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // eat newline

            switch (choice) {
                case 1 -> addTask(scanner);
                case 2 -> listTasks();
                case 3 -> markTaskDone(scanner);
                case 4 -> deleteTask(scanner);
                case 5 -> {
                    saveTasks();
                    System.out.println("Bye! 🫡");
                    return;
                }
                default -> System.out.println("❌ Invalid option!");
            }
        }
    }

    static class Task {
        String title;
        boolean done;

        Task(String title) {
            this.title = title;
            this.done = false;
        }

        @Override
        public String toString() {
            return (done ? "[✔]" : "[ ]") + " " + title;
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        tasks.add(new Task(name));
        System.out.println("✅ Task added!");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("😴 No tasks yet.");
        } else {
            System.out.println("📃 Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void markTaskDone(Scanner scanner) {
        listTasks();
        System.out.print("Enter task number to mark as done: ");
        int index = scanner.nextInt() - 1;
        if (isValid(index)) {
            tasks.get(index).done = true;
            System.out.println("🎉 Task marked as done!");
        } else {
            System.out.println("❌ Invalid task number.");
        }
    }

    private static void deleteTask(Scanner scanner) {
        listTasks();
        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt() - 1;
        if (isValid(index)) {
            tasks.remove(index);
            System.out.println("🗑️ Task deleted.");
        } else {
            System.out.println("❌ Invalid task number.");
        }
    }

    private static boolean isValid(int index) {
        return index >= 0 && index < tasks.size();
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.done + ";" + task.title);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Could not save tasks.");
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);
                Task task = new Task(parts[1]);
                task.done = Boolean.parseBoolean(parts[0]);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Could not load tasks.");
        }
    }
}
