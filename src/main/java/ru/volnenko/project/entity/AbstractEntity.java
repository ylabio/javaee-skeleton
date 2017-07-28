package ru.volnenko.project.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @NotNull
    @Access(AccessType.PROPERTY)
    public static final String TABLE_PREFIX = "ais_";

    @Id
    @Nullable
    private String id = UUID.randomUUID().toString();

    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable final String id) {
        this.id = id;
    }

    @Nullable
    public Date getCreated() {
        return created;
    }

    public void setCreated(@Nullable Date created) {
        this.created = created;
    }

    @Nullable
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(@Nullable Date updated) {
        this.updated = updated;
    }

    @PrePersist
    private void prePersist() {
        created = new Date();
        updated = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        if (created == null) {
            created = new Date();
        }
        updated = new Date();
    }

}
