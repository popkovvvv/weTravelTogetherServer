package com.weTravelTogether.models.request;

import javax.validation.constraints.*;

public class ItemCreate {

    @NotBlank
    @Size(max=255)
    private final String description;

    @NotNull
    private final Integer count;

    public ItemCreate(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCount() {
        return count;
    }
}
