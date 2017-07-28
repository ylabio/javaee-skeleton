package ru.volnenko.project.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.LinkedHashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationREST extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> result = new LinkedHashSet<Class<?>>();
        result.add(ProjectService.class);
        result.add(TaskService.class);
        return result;
    }

}
