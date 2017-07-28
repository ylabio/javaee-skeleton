package ru.volnenko.project.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dto.ProjectRecord;

import javax.persistence.*;
import java.util.*;

@Entity(name = Project.ENTITY_NAME)
@Table(name = Project.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = AttributeConst.ID)
public class Project extends AbstractEntity {

    @NotNull
    public static final String ENTITY_NAME = "Project";

    @NotNull
    public static final String RELATION_NAME = "project";

    @NotNull
    public static final String TABLE_NAME = TABLE_PREFIX + ENTITY_NAME;

    @NotNull
    public static List<ProjectRecord> toListRecord(@Nullable final Collection<Project> projects) {
        if (projects == null || projects.isEmpty()) {
            return Collections.emptyList();
        }
        @NotNull final List<ProjectRecord> result = new ArrayList<>();
        for (@Nullable final Project project: projects) {
            if (project == null) {
                continue;
            }
            result.add(new ProjectRecord(project));
        }
        return result;
    }

    @Nullable
    private String name = "";

    @Nullable
    private String description = "";

    @Nullable
    private Date dateBegin;

    @Nullable
    private Date dateEnd;

    @OneToMany(mappedBy = Project.RELATION_NAME, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Task> tasks = new ArrayList<>();

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

    @NotNull
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(@NotNull List<Task> tasks) {
        this.tasks = tasks;
    }

}
