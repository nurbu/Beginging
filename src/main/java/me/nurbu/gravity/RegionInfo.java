package me.nurbu.gravity;

import java.util.Objects;

// Simplify the Regions given through ApplicableSet
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

    // Change equals to check actual value of object instead of just Object reference.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionInfo)) return false;
        RegionInfo tempRegion = (RegionInfo) o;
        return tempRegion.id.equals(this.id) && this.priority == tempRegion.priority;
    }

    // Change hashcode to match with just id and priority.
    @Override
    public int hashCode() {
        return Objects.hash(id, priority);
    }


}
