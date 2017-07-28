package ru.volnenko.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.entity.Project;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectRecord extends AbstractEntityRecord {

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateBegin;

    @Nullable
    private Date dateEnd;

    public ProjectRecord() {
    }

    public ProjectRecord(@NotNull final Project project) {
        super(project);
        name = project.getName();
        description = project.getDescription();
        dateBegin = project.getDateBegin();
        dateEnd = project.getDateEnd();
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable final String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    @Nullable
    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(@Nullable final Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Nullable
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(@Nullable final Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
