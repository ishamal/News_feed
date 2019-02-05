package com.test.ishara.newsfeed.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceResultDto {

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    private List<SourceDto> sourceDtos;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceDto> getSourceDtos() {
        return sourceDtos;
    }

    public void setSourceDtos(List<SourceDto> sourceDtos) {
        this.sourceDtos = sourceDtos;
    }
}
