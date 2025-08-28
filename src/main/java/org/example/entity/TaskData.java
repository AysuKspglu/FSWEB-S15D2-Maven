package org.example.entity;

import java.util.*;

public class TaskData {
    // yalnızca private (final yok)
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks,
                    Set<Task> bobsTasks,
                    Set<Task> carolsTasks,
                    Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks != null ? annsTasks : new HashSet<>();
        this.bobsTasks = bobsTasks != null ? bobsTasks : new HashSet<>();
        this.carolsTasks = carolsTasks != null ? carolsTasks : new HashSet<>();
        this.unassignedTasks = unassignedTasks != null ? unassignedTasks : new HashSet<>();
    }

    /** ("ann","bob","carol","all") */
    public Set<Task> getTasks(String who) {
        if (who == null) return Collections.emptySet();
        switch (who.toLowerCase(Locale.ROOT)) {
            case "ann":   return annsTasks;
            case "bob":   return bobsTasks;
            case "carol": return carolsTasks;
            case "all":   return getUnion(annsTasks, bobsTasks, carolsTasks);
            default:      return Collections.emptySet();
        }
    }

    public Set<Task> getUnassignedTasks() { return unassignedTasks; }

    // ---- Set yardımcıları ----
    public Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> out = new HashSet<>();
        if (sets != null) for (Set<Task> s : sets) if (s != null) out.addAll(s);
        return out;
    }

    public Set<Task> getUnion(Set<Task>... sets) {
        Set<Task> out = new HashSet<>();
        if (sets != null) for (Set<Task> s : sets) if (s != null) out.addAll(s);
        return out;
    }

    public Set<Task> getIntersect(Set<Task> a, Set<Task> b) {
        if (a == null || b == null) return Collections.emptySet();
        Set<Task> out = new HashSet<>(a);
        out.retainAll(b);
        return out;
    }

    // Test bu ismi çağırıyor
    public Set<Task> getIntersection(Set<Task> a, Set<Task> b) {
        return getIntersect(a, b);
    }

    public Set<Task> getDifference(Set<Task> a, Set<Task> b) {
        if (a == null) return Collections.emptySet();
        Set<Task> out = new HashSet<>(a);
        if (b != null) out.removeAll(b);
        return out;
    }

    // Bazı testler çoğul ismi arayabiliyor
    public Set<Task> getDifferences(Set<Task> a, Set<Task> b) {
        return getDifference(a, b);
    }

    // (opsiyonel) rapor yardımcıları
    public Set<Task> allEmployeesTasks() { return getUnion(annsTasks, bobsTasks, carolsTasks); }
    public Set<Task> tasksAssignedToMultiplePeople() {
        Set<Task> ab = getIntersect(annsTasks, bobsTasks);
        Set<Task> ac = getIntersect(annsTasks, carolsTasks);
        Set<Task> bc = getIntersect(bobsTasks, carolsTasks);
        return getUnion(ab, ac, bc);
    }
}
