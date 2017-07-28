package ru.volnenko.project.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.volnenko.project.api.ProjectServiceAPI;
import ru.volnenko.project.constant.AttributeConst;
import ru.volnenko.project.dao.ProjectDAO;
import ru.volnenko.project.dto.ProjectRecord;
import ru.volnenko.project.dto.ResultRecord;
import ru.volnenko.project.entity.Project;
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

@Path(ProjectService.SERVICE_PATH)
@Interceptors({InterceptorService.class})
@WebService(serviceName = ProjectService.SERVICE_NAME, targetNamespace = ProjectService.SERVICE_NAMESPACE)
public class ProjectService extends AbstractService implements ProjectServiceAPI {

    @NotNull
    private static final Logger LOGGER = Logger.getLogger(ProjectService.class.getSimpleName());

    @Inject
    private ProjectDAO projectDAO;

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
    @Produces({MediaType.APPLICATION_JSON})
    public ProjectRecord getProjectById(
            @QueryParam(AttributeConst.ID)
            @WebParam(name = AttributeConst.ID, partName = AttributeConst.ID)
            @Nullable final String id
    ) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            @Nullable final Project project = projectDAO.getProjectById(id);
            if (project == null) {
                return null;
            }
            return new ProjectRecord(project);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }

    @GET
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Path(GET_COUNT_PROJECT_PATH)
    public Long getCountProject() {
        try {
            return projectDAO.getCountProject();
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
    @Path(GET_LIST_PROJECT_PATH)
    public List<ProjectRecord> getListProject() {
        try {
            @NotNull final List<Project> projects = projectDAO.getListProject();
            return Project.toListRecord(projects);
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
    @Path(GET_LIST_PROJECT_ID_PATH)
    public List<String> getListProjectId() {
        try {
           return projectDAO.getListProjectId();
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

    @POST
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(PERSIST_PROJECT_PATH)
    public ResultRecord persistProject(@Nullable final ProjectRecord projectRecord) {
        if (projectRecord == null || projectRecord.getId() == null || projectRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Project project = projectDAO.persistProjectRecord(projectRecord);
            return getResultByNotNull(project);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(MERGE_PROJECT_PATH)
    public ResultRecord mergeProject(@Nullable final ProjectRecord projectRecord) {
        if (projectRecord == null || projectRecord.getId() == null || projectRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Project project = projectDAO.mergeProjectRecord(projectRecord);
            return getResultByNotNull(project);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(REMOVE_PROJECT_PATH)
    public ResultRecord removeProject(@Nullable final ProjectRecord projectRecord) {
        if (projectRecord == null || projectRecord.getId() == null || projectRecord.getId().isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Project project = projectDAO.removeProjectById(projectRecord.getId());
            return getResultByNotNull(project);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

    @POST
    @NotNull
    @Override
    @WebMethod
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(REMOVE_PROJECT_BY_ID_PATH)
    public ResultRecord removeProjectById(
            @FormParam(AttributeConst.ID)
            @WebParam(name = AttributeConst.ID, partName = AttributeConst.ID)
            @Nullable final String id
    ) {
        if (id == null || id.isEmpty()) {
            return getFail();
        }
        try {
            @Nullable final Project project = projectDAO.removeProjectById(id);
            return getResultByNotNull(project);
        } catch (@NotNull final Exception e) {
            LOGGER.severe(e.getMessage());
            return getFail(e);
        }
    }

}
