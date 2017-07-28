package ru.volnenko.project.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.dto.ProjectRecord;
import ru.volnenko.project.dto.ResultRecord;

import java.util.List;

public interface ProjectServiceAPI {

    @NotNull
    String SERVICE_NAME = "ProjectService";

    @NotNull
    String SERVICE_PATH = "/" + SERVICE_NAME;

    @NotNull
    String SERVICE_ENDPOINT = "ru.volnenko.project.service.ProjectService";

    @NotNull
    String SERVICE_NAMESPACE = "http://service.project.volnenko.ru/";

    @NotNull
    String PING_OPERATION_NAME = "ping";

    @NotNull
    String PING_PATH = "/" + PING_OPERATION_NAME;

    @NotNull
    String PERSIST_PROJECT_OPERATION_NAME = "persistProject";

    @NotNull
    String PERSIST_PROJECT_PATH = "/" + PERSIST_PROJECT_OPERATION_NAME;

    @NotNull
    String MERGE_PROJECT_OPERATION_NAME = "mergeProject";

    @NotNull
    String MERGE_PROJECT_PATH = "/" + MERGE_PROJECT_OPERATION_NAME;

    @NotNull
    String REMOVE_PROJECT_OPERATION_NAME = "removeProject";

    @NotNull
    String REMOVE_PROJECT_PATH = "/" + REMOVE_PROJECT_OPERATION_NAME;

    @NotNull
    String REMOVE_PROJECT_BY_ID_OPERATION_NAME = "removeProjectById";

    @NotNull
    String REMOVE_PROJECT_BY_ID_PATH = "/" + REMOVE_PROJECT_BY_ID_OPERATION_NAME;

    @NotNull
    String GET_LIST_PROJECT_OPERATION_NAME = "getListProject";

    @NotNull
    String GET_LIST_PROJECT_PATH = "/" + GET_LIST_PROJECT_OPERATION_NAME;

    @NotNull
    String GET_LIST_PROJECT_ID_OPERATION_NAME = "getListProjectId";

    @NotNull
    String GET_LIST_PROJECT_ID_PATH = "/" + GET_LIST_PROJECT_ID_OPERATION_NAME;

    @NotNull
    String GET_COUNT_PROJECT_OPERATION_NAME = "getCountProject";

    String GET_COUNT_PROJECT_PATH = "/" + GET_COUNT_PROJECT_OPERATION_NAME;

    @NotNull
    ResultRecord ping();

    @Nullable
    ProjectRecord getProjectById(@Nullable String id);

    @NotNull
    List<ProjectRecord> getListProject();

    @NotNull
    Long getCountProject();

    @NotNull
    List<String> getListProjectId();

    @NotNull
    ResultRecord persistProject(@Nullable ProjectRecord projectRecord);

    @NotNull
    ResultRecord mergeProject(@Nullable ProjectRecord projectRecord);

    @NotNull
    ResultRecord removeProject(@Nullable ProjectRecord projectRecord);

    @NotNull
    ResultRecord removeProjectById(@Nullable String id);

}
