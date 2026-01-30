package me.nurbu.gravity;

import java.util.Objects;

public class RegionManager {
    private final String id;
    private final int priority;

    RegionManager(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionManager)) return false;
        RegionManager temp = (RegionManager) o;
        return temp.id.equals(this.id) && this.priority == temp.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priority);
    }
}
