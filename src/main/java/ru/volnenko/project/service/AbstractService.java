package ru.volnenko.project.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.volnenko.project.dto.ResultRecord;

import java.util.Collection;

public abstract class AbstractService {

    @NotNull
    protected ResultRecord getSuccess() {
        return new ResultRecord(true);
    }

    @NotNull
    protected ResultRecord getFail() {
        return new ResultRecord(false);
    }

    @NotNull
    protected ResultRecord getFail(@NotNull final Exception e) {
        return new ResultRecord(false, e.getMessage());
    }

    @NotNull
    protected ResultRecord getResultByNotNull(@Nullable final Object value) {
        return value != null ? getSuccess() : getFail();
    }

    @NotNull
    protected ResultRecord getResultByNotEmpty(@Nullable final Collection collection) {
        return collection != null && !collection.isEmpty() ? getSuccess() : getFail();
    }

}
