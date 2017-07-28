package ru.volnenko.project.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dto.ProjectRecord;
import ru.volnenko.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.util.List;

@Stateless
public class ProjectDAO extends AbstractDAO {

    @NotNull
    public Project toEntity(@NotNull final ProjectRecord record) {
        @NotNull final Project project = new Project();
        project.setId(record.getId());
        project.setName(record.getName());
        project.setDescription(record.getDescription());
        project.setDateBegin(record.getDateBegin());
        project.setDateEnd(record.getDateEnd());
        return project;
    }

    @Nullable
    public Project persistProject(@Nullable final Project project) {
        if (project == null) {
            return null;
        }
        em.persist(project);
        return project;
    }

    @Nullable
    public Project persistProjectRecord(@Nullable final ProjectRecord record) {
        if (record == null) {
            return null;
        }
        @NotNull final Project project = toEntity(record);
        return persistProject(project);
    }

    @Nullable
    public Project mergeProjectRecord(@Nullable final ProjectRecord record) {
        if (record == null) {
            return null;
        }
        @NotNull final Project project = toEntity(record);
        return mergeProject(project);
    }

    @Nullable
    public Project mergeProject(@Nullable final Project project) {
        if (project == null) {
            return null;
        }
        return em.merge(project);
    }

    @NotNull
    public Long getCountProject() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Long> query = builder.createQuery(Long.class);
        @NotNull final Root<Project> root = query.from(Project.class);
        query.select(builder.count(root));
        return em.createQuery(query).getSingleResult();
    }

    @NotNull
    public List<String> getListProjectId() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<String> query = builder.createQuery(String.class);
        @NotNull final Root<Project> root = query.from(Project.class);
        query.multiselect(root.get(AttributeConst.ID));
        return em.createQuery(query).getResultList();
    }

    @NotNull
    public List<Project> getListProject() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Project> query = builder.createQuery(Project.class);
        @NotNull final Root<Project> root = query.from(Project.class);
        query.orderBy(builder.desc(root.get(AttributeConst.CREATED)));
        return em.createQuery(query).getResultList();
    }

    @Nullable
    public Project getProjectById(@Nullable final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        @NotNull final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        @NotNull final Root<Project> root = criteriaQuery.from(Project.class);

        @NotNull final Path<String> pathProjectId = root.get(AttributeConst.ID);
        @NotNull final ParameterExpression<String> parameterId = criteriaBuilder.parameter(String.class, AttributeConst.ID);
        @NotNull final Predicate predicateById = criteriaBuilder.equal(pathProjectId, parameterId);
        criteriaQuery.where(predicateById);

        @NotNull final TypedQuery<Project> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter(AttributeConst.ID, id);
        typedQuery.setMaxResults(1);
        return getEntity(typedQuery);
    }

    @Nullable
    public Project removeProjectById(@Nullable final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        @Nullable final Project project = getProjectById(id);
        if (project == null) {
            return null;
        }
        em.remove(project);
        return project;
    }

}
