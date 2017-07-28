package ru.volnenko.project.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.dto.ResultRecord;
import ru.volnenko.project.dto.TaskRecord;

import java.util.List;

public interface TaskServiceAPI {

    @NotNull
    String SERVICE_NAME = "TaskService";

    @NotNull
    String SERVICE_PATH = "/" + SERVICE_NAME;

    @NotNull
    String SERVICE_ENDPOINT = "ru.volnenko.project.service.TaskService";

    @NotNull
    String SERVICE_NAMESPACE = "http://service.project.volnenko.ru/";

    @NotNull
    String PING_OPERATION_NAME = "ping";

    @NotNull
    String PING_PATH = "/" + PING_OPERATION_NAME;

    @NotNull
    String PERSIST_TASK_OPERATION_NAME = "persistTask";

    @NotNull
    String PERSIST_TASK_PATH = "/" + PERSIST_TASK_OPERATION_NAME;

    @NotNull
    String MERGE_TASK_OPERATION_NAME = "mergeTask";

    @NotNull
    String MERGE_TASK_PATH = "/" + MERGE_TASK_OPERATION_NAME;

    @NotNull
    String REMOVE_TASK_OPERATION_NAME = "removeTask";

    @NotNull
    String REMOVE_TASK_PATH = "/" + REMOVE_TASK_OPERATION_NAME;

    @NotNull
    String REMOVE_TASK_BY_ID_OPERATION_NAME = "removeTaskById";

    @NotNull
    String REMOVE_TASK_BY_ID_PATH = "/" + REMOVE_TASK_BY_ID_OPERATION_NAME;

    @NotNull
    String GET_LIST_TASK_OPERATION_NAME = "getListTask";

    @NotNull
    String GET_LIST_TASK_PATH = "/" + GET_LIST_TASK_OPERATION_NAME;

    @NotNull
    String GET_LIST_TASK_ID_OPERATION_NAME = "getListTaskId";

    @NotNull
    String GET_LIST_TASK_ID_PATH = "/" + GET_LIST_TASK_ID_OPERATION_NAME;

    @NotNull
    String GET_COUNT_TASK_OPERATION_NAME = "getCountTask";

    @NotNull
    String GET_COUNT_TASK_PATH = "/" + GET_COUNT_TASK_OPERATION_NAME;

    @NotNull
    String GET_LIST_TASK_BY_PROJECT_ID_OPERATION_NAME = "getListTaskByProjectId";

    @NotNull
    String GET_LIST_TASK_BY_PROJECT_ID_PATH = "/" + GET_LIST_TASK_BY_PROJECT_ID_OPERATION_NAME;

    @NotNull
    String GET_COUNT_TASK_BY_PROJECT_ID_OPERATION_NAME = "getCountTaskByProjectId";

    @NotNull
    String GET_COUNT_TASK_BY_PROJECT_ID_PATH = "/" + GET_COUNT_TASK_BY_PROJECT_ID_OPERATION_NAME;

    @NotNull
    ResultRecord ping();

    @Nullable
    TaskRecord getTaskById(@Nullable String id);

    @NotNull
    List<TaskRecord> getListTask();

    @NotNull
    Long getCountTask();
    
    @NotNull
    List<TaskRecord> getListTaskByProjectId(@Nullable String projectId);

    @NotNull
    Long getCountTaskByProjectId(@Nullable String projectId);
    
    @NotNull
    List<String> getListTaskId();
    
    @Nullable
    ResultRecord persistTask(@Nullable TaskRecord taskRecord);
    
    @Nullable
    ResultRecord mergeTask(@Nullable TaskRecord taskRecord);
    
    @Nullable
    ResultRecord removeTask(@Nullable TaskRecord taskRecord);
    
    @Nullable
    ResultRecord removeTaskById(@Nullable String id);
    
}
