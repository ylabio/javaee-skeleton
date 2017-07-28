package ru.volnenko.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultRecord extends AbstractDTO {

    @NotNull
    private Boolean success = true;

    @NotNull
    private String message = "";

    public ResultRecord() {
    }

    public ResultRecord(@NotNull final Boolean success) {
        this.success = success;
    }

    public ResultRecord(@NotNull final Boolean success, @NotNull final String message) {
        this.success = success;
        this.message = message;
    }

    @NotNull
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(@NotNull final Boolean success) {
        this.success = success;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NotNull final String message) {
        this.message = message;
    }

}
