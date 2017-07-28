package ru.volnenko.project.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.volnenko.project.api.TaskServiceAPI;
import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dao.TaskDAO;
import ru.volnenko.project.dto.ResultRecord;
import ru.volnenko.project.dto.TaskRecord;
import ru.volnenko.project.entity.Task;
import ru.volnenko.project.interceptor.InterceptorService;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path(TaskService.SERVICE_PATH)
@Interceptors({InterceptorService.class})
@WebService(serviceName = TaskService.SERVICE_NAME, targetNamespace = TaskService.SERVICE_NAMESPACE)
public class TaskService extends AbstractService implements TaskServiceAPI {

    @NotNull
    private static final Logger LOGGER = Logger.getLogger(TaskService.class.getSimpleName());

    @Inject
    private TaskDAO taskDAO;

    @GET
    @NotNull
    @Override
    @WebMethod
    @Path(PING_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    public ResultRecord ping() {
        return getSuccess();
    }

    @GET
    @Nullable
    @Override
    @WebMethod
    @Path(GET_LIST_TASK_ID_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    public TaskRecord getTaskById(
            @QueryParam(AttributeConst.ID)
            @WebParam(name = AttributeConst.ID, targetNamespace = AttributeConst.ID)
            @Nullable final String id
    ) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            @Nullable final Task task = taskDAO.getTaskById(id);
            if (task == null) {
                return null;
            }
            return new TaskRecord(task);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Path(GET_LIST_TASK_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    public List<TaskRecord> getListTask() {
        try {
            @NotNull final List<Task> tasks = taskDAO.getListTask();
            return Task.toListRecord(tasks);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Path(GET_COUNT_TASK_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    public Long getCountTask() {
        try {
            return taskDAO.getCountTask();
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return 0L;
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Path(GET_LIST_TASK_BY_PROJECT_ID_PATH)
    public List<TaskRecord> getListTaskByProjectId(
            @QueryParam(AttributeConst.PROJECT_ID)
            @WebParam(name = AttributeConst.PROJECT_ID, partName = AttributeConst.PROJECT_ID)
            @Nullable final String projectId
    ) {
        try {
            @NotNull final List<Task> tasks = taskDAO.getListTaskByProjectId(projectId);
            return Task.toListRecord(tasks);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Path(GET_COUNT_TASK_BY_PROJECT_ID_PATH)
    public Long getCountTaskByProjectId(
            @QueryParam(AttributeConst.PROJECT_ID)
            @WebParam(name = AttributeConst.PROJECT_ID, partName = AttributeConst.PROJECT_ID)
            @Nullable final String projectId
    ) {
        if (projectId == null || projectId.isEmpty()) {
            return 0L;
        }
        try {
            return taskDAO.getCountTaskByProjectId(projectId);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return 0L;
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Path(GET_LIST_TASK_ID_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> getListTaskId() {
        try {
            return taskDAO.getListTaskId();
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

    @POST
    @Nullable
    @Override
    @WebMethod
    @Path(PERSIST_TASK_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public ResultRecord persistTask(@Nullable final TaskRecord taskRecord) {
        if (taskRecord == null || taskRecord.getId() == null || taskRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Task task = taskDAO.persistTaskRecord(taskRecord);
            return getResultByNotNull(task);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @Nullable
    @Override
    @WebMethod
    @Path(MERGE_TASK_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public ResultRecord mergeTask(@Nullable final TaskRecord taskRecord) {
        if (taskRecord == null || taskRecord.getId() == null || taskRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Task task = taskDAO.mergeTaskRecord(taskRecord);
            return getResultByNotNull(task);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @Nullable
    @Override
    @WebMethod
    @Path(REMOVE_TASK_PATH)
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public ResultRecord removeTask(@Nullable final TaskRecord taskRecord) {
        if (taskRecord == null || taskRecord.getId() == null || taskRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Task task = taskDAO.removeTaskById(taskRecord.getId());
            return getResultByNotNull(task);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @Nullable
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Path(REMOVE_TASK_BY_ID_PATH)
    public ResultRecord removeTaskById(
            @FormParam(AttributeConst.ID)
            @WebParam(name = AttributeConst.ID, partName = AttributeConst.ID)
            @Nullable final String id
    ) {
        if (id == null || id.isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Task task = taskDAO.removeTaskById(id);
            return getResultByNotNull(task);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

}
