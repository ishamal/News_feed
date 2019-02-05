package com.test.ishara.newsfeed.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDto {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesDto> articlesDtos;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesDto> getArticlesDtos() {
        return articlesDtos;
    }

    public void setArticlesDtos(List<ArticlesDto> articlesDtos) {
        this.articlesDtos = articlesDtos;
    }
}
