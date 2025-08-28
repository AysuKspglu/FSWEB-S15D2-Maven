import org.example.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;       // ← eklendi
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ResultAnalyzer.class)
public class MainTest {

    Task task1;
    Task task2;
    Task task3;
    TaskData taskData;
    Set<Task> taskSet1;
    Set<Task> taskSet2;
    Set<Task> taskSet3;

    @BeforeEach
    void setUp() {
        task1 = new Task("Java Collections", "Write List Interface",
                "Ann", Status.IN_QUEUE, Priority.LOW);
        task2 = new Task("Java Collections", "Write Set Interface",
                "Ann", Status.ASSIGNED, Priority.MED);
        task3 = new Task("Java Collections", "Write Map Interface",
                "Bob", Status.IN_QUEUE, Priority.HIGH);

        taskSet1 = new HashSet<>();
        taskSet1.add(task1);
        taskSet2 = new HashSet<>();
        taskSet2.add(task2);       // ← düzeltildi (önceden taskSet1.add(task2) yazıyordu)
        taskSet3 = new HashSet<>();
        taskSet3.add(task3);       // ← düzeltildi (önceden taskSet1.add(task3) yazıyordu)

        taskData = new TaskData(taskSet1, taskSet2, taskSet3, new HashSet<>());
    }

    @DisplayName("Task sınıfı doğru access modifiers sahip mi")
    @Test
    public void testTaskAccessModifiers() throws NoSuchFieldException {
        Field assigneeFields = task1.getClass().getDeclaredField("assignee");
        Field descriptionsFields = task1.getClass().getDeclaredField("description");
        Field projectFields = task1.getClass().getDeclaredField("project");
        Field priorityFields = task1.getClass().getDeclaredField("priority");
        Field statusFields = task1.getClass().getDeclaredField("status");

        assertEquals(2, assigneeFields.getModifiers());
        assertEquals(2, descriptionsFields.getModifiers());
        assertEquals(2, projectFields.getModifiers());
        assertEquals(2, priorityFields.getModifiers());
        assertEquals(2, statusFields.getModifiers());
    }

    @DisplayName("Task sınıfı doğru typelara sahip mi")
    @Test
    public void testTaskTypes() {
        assertThat(task1.getAssignee(), instanceOf(String.class));
        assertThat(task1.getDescription(), instanceOf(String.class));
        assertThat(task1.getPriority(), instanceOf(Priority.class));
        assertThat(task1.getProject(), instanceOf(String.class));
        assertThat(task1.getStatus(), instanceOf(Status.class));
    }

    @DisplayName("TaskData sınıfı doğru access modifiers sahip mi")
    @Test
    public void testTaskDataAccessModifiers() throws NoSuchFieldException {
        Field annTasksField = taskData.getClass().getDeclaredField("annsTasks");
        Field bobTasksField = taskData.getClass().getDeclaredField("bobsTasks");
        Field carolTasksField = taskData.getClass().getDeclaredField("carolsTasks");
        Field unassignedTasksField = taskData.getClass().getDeclaredField("unassignedTasks");

        assertEquals(2, annTasksField.getModifiers());
        assertEquals(2, bobTasksField.getModifiers());
        assertEquals(2, carolTasksField.getModifiers());
        assertEquals(2, unassignedTasksField.getModifiers());
    }

    @DisplayName("TaskData getTasks method doğru çalışıyor mu ?")
    @Test
    public void testGetTasksMethod() {
        assertEquals(taskSet1, taskData.getTasks("ann"));
        assertEquals(taskSet2, taskData.getTasks("bob"));
        assertEquals(taskSet3, taskData.getTasks("carol"));
    }

    @DisplayName("TaskData getUnion method doğru çalışıyor mu ?")
    @Test
    public void testGetUnionMethod() {
        Set<Task> ts1 = new HashSet<>();
        ts1.add(task1);
        Set<Task> ts2 = new HashSet<>();
        ts2.add(task2);

        Set<Task> totals = taskData.getUnion(ts1, ts2);
        assertEquals(2, totals.size());
    }

    @DisplayName("TaskData getIntersection() method doğru çalışıyor mu ?")
    @Test
    public void testGetIntersectMethod() {
        Set<Task> ts1 = new HashSet<>();
        ts1.add(task1);
        ts1.add(task2);
        Set<Task> ts2 = new HashSet<>();
        ts2.add(task2);

        Set<Task> intersections = taskData.getIntersection(ts1, ts2);

        for (Task task : intersections) {
            assertEquals(task2, task);
        }
        assertEquals(1, intersections.size());
    }

    @DisplayName("TaskData getDifferences() method doğru çalışıyor mu ?")
    @Test
    public void testGetDifferenceMethod() {
        Set<Task> ts1 = new HashSet<>();
        ts1.add(task1);
        ts1.add(task2);
        Set<Task> ts2 = new HashSet<>();
        ts2.add(task2);

        Set<Task> differences = taskData.getDifferences(ts1, ts2);

        for (Task task : differences) {
            assertEquals(task1, task);
        }
        assertEquals(1, differences.size());
    }

    @DisplayName("findUniqueWords doğru çalışıyor mu ?")
    @Test
    public void testFindUniqueWordsMethod() {
        assertEquals(143, StringSet.findUniqueWords().size());

        List<String> results = StringSet.findUniqueWords().stream().collect(Collectors.toList());
        assertEquals("a", results.get(0));
        assertEquals("wrote", results.get(results.size() - 1));
    }
}
