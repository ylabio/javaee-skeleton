package ru.volnenko.project.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dto.TaskRecord;
import ru.volnenko.project.entity.Project;
import ru.volnenko.project.entity.Task;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.util.Collections;
import java.util.List;

@Stateless
public class TaskDAO extends AbstractDAO {

    @Inject
    private ProjectDAO projectDAO;

    @NotNull
    public Task toEntity(@NotNull final TaskRecord record) {
        @NotNull final Task task = new Task();
        task.setId(record.getId());
        task.setName(record.getName());
        task.setDescription(record.getDescription());
        task.setDateBegin(record.getDateBegin());
        task.setDateEnd(record.getDateEnd());
        task.setProject(projectDAO.getProjectById(record.getProjectId()));
        return task;
    }

    @Nullable
    public Task persistTask(@Nullable final Task task) {
        if (task == null) {
            return null;
        }
        em.persist(task);
        return task;
    }

    @Nullable
    public Task persistTaskRecord(@Nullable final TaskRecord record) {
        if (record == null) {
            return null;
        }
        @NotNull final Task task = toEntity(record);
        return persistTask(task);
    }

    @Nullable
    public Task mergeTaskRecord(@Nullable final TaskRecord record) {
        if (record == null) {
            return null;
        }
        @NotNull final Task task = toEntity(record);
        return mergeTask(task);
    }

    @Nullable
    public Task mergeTask(@Nullable final Task task) {
        if (task == null) {
            return null;
        }
        return em.merge(task);
    }

    @NotNull
    public Long getCountTask() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Long> query = builder.createQuery(Long.class);
        @NotNull final Root<Task> root = query.from(Task.class);
        query.select(builder.count(root));
        return em.createQuery(query).getSingleResult();
    }

    @NotNull
    public List<String> getListTaskId() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<String> query = builder.createQuery(String.class);
        @NotNull final Root<Task> root = query.from(Task.class);
        query.multiselect(root.get(AttributeConst.ID));
        return em.createQuery(query).getResultList();
    }

    @NotNull
    public List<Task> getListTask() {
        @NotNull final CriteriaBuilder builder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Task> query = builder.createQuery(Task.class);
        @NotNull final Root<Task> root = query.from(Task.class);
        query.orderBy(builder.desc(root.get(AttributeConst.CREATED)));
        return em.createQuery(query).getResultList();
    }

    @Nullable
    public Task getTaskById(@Nullable final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        @NotNull final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        @NotNull final Root<Task> root = criteriaQuery.from(Task.class);

        @NotNull final Path<String> pathTaskId = root.get(AttributeConst.ID);
        @NotNull final ParameterExpression<String> parameterId = criteriaBuilder.parameter(String.class, AttributeConst.ID);
        @NotNull final Predicate predicateById = criteriaBuilder.equal(pathTaskId, parameterId);
        criteriaQuery.where(predicateById);

        @NotNull final TypedQuery<Task> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter(AttributeConst.ID, id);
        typedQuery.setMaxResults(1);
        return getEntity(typedQuery);
    }

    @Nullable
    public Task removeTaskById(@Nullable final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        @Nullable final Task task = getTaskById(id);
        if (task == null) {
            return null;
        }
        em.remove(task);
        return task;
    }

    @NotNull
    public Long getCountTaskByProjectId(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) {
            return 0L;
        }

        @NotNull final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        @NotNull final Root<Task> root = criteriaQuery.from(Task.class);

        @NotNull final Join<Task, Project> joinProject = root.join(Project.RELATION_NAME);
        @NotNull final Path<String> pathProjectId = joinProject.get(AttributeConst.ID);
        @NotNull final ParameterExpression<String> parameterProjectId = criteriaBuilder.parameter(String.class, AttributeConst.PROJECT_ID);
        @NotNull final Predicate predicateByProjectId = criteriaBuilder.equal(pathProjectId, parameterProjectId);

        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(criteriaBuilder.and(predicateByProjectId));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(AttributeConst.CREATED)));

        @NotNull final TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter(AttributeConst.PROJECT_ID, projectId);

        return typedQuery.getSingleResult();
    }

    @NotNull
    public List<Task> getListTaskByProjectId(@Nullable final String projectId) {
        if (projectId == null || projectId.isEmpty()) {
            return Collections.emptyList();
        }

        @NotNull final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        @NotNull final Root<Task> root = criteriaQuery.from(Task.class);

        @NotNull final Join<Task, Project> joinProject = root.join(Project.RELATION_NAME);
        @NotNull final Path<String> pathProjectId = joinProject.get(AttributeConst.ID);
        @NotNull final ParameterExpression<String> parameterProjectId = criteriaBuilder.parameter(String.class, AttributeConst.PROJECT_ID);
        @NotNull final Predicate predicateByProjectId = criteriaBuilder.equal(pathProjectId, parameterProjectId);

        criteriaQuery.where(criteriaBuilder.and(predicateByProjectId));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(AttributeConst.CREATED)));

        @NotNull final TypedQuery<Task> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter(AttributeConst.PROJECT_ID, projectId);

        return typedQuery.getResultList();
    }

}
