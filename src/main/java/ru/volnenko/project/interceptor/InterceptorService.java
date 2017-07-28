package ru.volnenko.project.interceptor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.NotFoundException;

public class InterceptorService {

    @NotNull
    @AroundInvoke
    public Object invoke(@NotNull final InvocationContext context) throws Exception {
        @Nullable final Object result = context.proceed();
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

}
