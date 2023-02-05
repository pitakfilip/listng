package sk.fmfi.listng.infrastructure.common;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1050059245481261988L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    /**
     * Returns short "toString" that should contain entity type and id (and optionally most
     * important attributes) - mostly for meaningful logging/audit purposes.
     * Does not replace {@link #toString()} and should be as short as possible/reasonable.
     * <b>Does NOT mean {@code id.toString()}</b>, which is more for debug/trace logging.
     */
    public String idString() {
        return getClass().getSimpleName() + '(' + getId() + ')';
    }

    public final int hashCode() {
        return Objects.hashCode(getId());
    }

    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        return Objects.equals(getId(), ((BaseEntity) obj).getId());
    }

}
