import org.example.entity.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Task t1 = new Task("ProjA","Login page", "ann", Status.ASSIGNED, Priority.HIGH);
        Task t2 = new Task("ProjA","API auth", "bob", Status.IN_PROGRESS, Priority.MED);
        Task t3 = new Task("ProjB","DB schema", "carol", Status.IN_QUEUE, Priority.HIGH);
        Task t4 = new Task("ProjC","Refactor", "ann", Status.ASSIGNED, Priority.LOW);
        Task t5 = new Task("ProjA","API auth", "carol", Status.ASSIGNED, Priority.MED);
        Task t6 = new Task("ProjX","Write docs", null, Status.IN_QUEUE, Priority.MED);

        Set<Task> ann  = new HashSet<>(Arrays.asList(t1, t4));
        Set<Task> bob  = new HashSet<>(Collections.singletonList(t2));
        Set<Task> carol= new HashSet<>(Arrays.asList(t3, t5));
        Set<Task> unassigned = new HashSet<>(Collections.singletonList(t6));

        TaskData data = new TaskData(ann, bob, carol, unassigned);

        System.out.println("1) Tüm çalışanların taskları:");
        System.out.println(data.allEmployeesTasks());

        System.out.println("\n2) Ann: " + data.getTasks("ann"));
        System.out.println("Bob: " + data.getTasks("bob"));
        System.out.println("Carol: " + data.getTasks("carol"));

        System.out.println("\n3) Atanmamış tasklar:");
        System.out.println(data.getUnassignedTasks());

        System.out.println("\n4) Birden fazla çalışana atanmış tasklar:");
        System.out.println(data.tasksAssignedToMultiplePeople());

        System.out.println("\n--- StringSet ---");
        List<String> uniqueWords = StringSet.findUniqueWords();
        System.out.println("Unique kelime sayısı: " + uniqueWords.size());
        System.out.println("İlk 20 kelime: " + uniqueWords.subList(0, 20));
    }
}
