package ru.volnenko.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.entity.AbstractEntity;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntityRecord extends AbstractDTO {

    @Nullable
    private String id;

    @Nullable
    private Date created;

    @Nullable
    private Date updated;

    public AbstractEntityRecord() {
    }

    protected AbstractEntityRecord(@NotNull final AbstractEntity entity) {
        id = entity.getId();
        created = entity.getCreated();
        updated = entity.getUpdated();
    }

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

    public void setCreated(@Nullable final Date created) {
        this.created = created;
    }

    @Nullable
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(@Nullable final Date updated) {
        this.updated = updated;
    }

}
