package ru.volnenko.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.volnenko.project.entity.Project;
import ru.volnenko.project.entity.Task;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRecord extends AbstractEntityRecord {

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date dateBegin;

    @Nullable
    private Date dateEnd;

    @Nullable
    private String projectId;

    public TaskRecord() {
    }

    public TaskRecord(@NotNull final Task task) {
        super(task);
        name = task.getName();
        description = task.getDescription();
        dateBegin = task.getDateBegin();
        dateEnd = task.getDateEnd();

        @Nullable final Project project = task.getProject();
        if (project != null) {
            projectId = project.getId();
        }
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

    @Nullable
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(@Nullable final String projectId) {
        this.projectId = projectId;
    }

}
