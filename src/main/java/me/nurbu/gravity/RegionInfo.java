package me.nurbu.gravity;

import java.util.Objects;

public class RegionInfo {
    private final String id;
    private final int priority;

    RegionInfo(String id, int priority) {
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
        if (!(o instanceof RegionInfo)) return false;
        RegionInfo temp = (RegionInfo) o;
        return temp.id.equals(this.id) && this.priority == temp.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, priority);
    }


}
