package ru.volnenko.project.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dto.TaskRecord;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = Task.ENTITY_NAME)
@Table(name = Task.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = AttributeConst.ID)
public class Task extends AbstractEntity {

    @NotNull
    public static final String ENTITY_NAME = "Task";

    @NotNull
    public static final String RELATION_NAME = "task";

    @NotNull
    public static final String TABLE_NAME = TABLE_PREFIX + ENTITY_NAME;

    @NotNull
    public static List<TaskRecord> toListRecord(@Nullable final Collection<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return null;
        }
        @NotNull final List<TaskRecord> result = new ArrayList<>();
        for (@Nullable final Task task: tasks) {
            if (task == null) {
                continue;
            }
            result.add(new TaskRecord(task));
        }
        return result;
    }

    @Nullable
    @Column(nullable = true)
    private String name;

    @Nullable
    @Column(nullable = true)
    private String description;

    @Nullable
    @Column(nullable = true)
    private Date dateBegin;

    @Nullable
    @Column(nullable = true)
    private Date dateEnd;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
