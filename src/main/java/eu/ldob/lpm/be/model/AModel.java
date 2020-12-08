package eu.ldob.lpm.be.model;

import java.util.Objects;

public abstract class AModel<T> {

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AssignedProjectModel that = (AssignedProjectModel) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
