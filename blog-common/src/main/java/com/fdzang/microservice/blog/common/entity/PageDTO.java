package com.fdzang.microservice.blog.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<T> {

    private List<T> result;

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("pages")
    private List<Long> pages;

    @JsonProperty("previous")
    private Integer previous;

    @JsonProperty("next")
    private Integer next;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("page_no")
    private int pageNo;

    public PageDTO(int pageNo, int pageSize, long totalCount){
        this.pageNo=pageNo;
        this.pageSize=pageSize;
        this.totalCount=totalCount;
    }

    @JsonProperty("total_page")
    public int getTotalPage(){
        int totalPage=(int)totalCount/pageSize;
        if(totalCount%pageSize>0){
            totalPage++;
        }
        return totalPage;
    }

    @JsonIgnore
    public int getStartPos(){
        if(pageNo<=1){
            return 0;
        }else{
            return (pageNo-1)*pageSize;
        }
    }

    @JsonIgnore
    public int getEndPos(){
        int page=pageNo;
        if(pageNo<1){
            page=1;
        }
        int endPos=page*pageSize;
        if(endPos<totalCount){
            return endPos;
        }else{
            return (int)(totalCount);
        }
    }

    @JsonProperty("pages")
    public List<Long> getPages() {
        pages = new ArrayList<>();
        totalCount=getTotalPage();

        long indexPage = 1;
        if (totalCount - 2 <= 0){
            indexPage = 1;
        }else if (pageNo - totalCount <= 2)
        {
            indexPage = pageNo - 4;
        } else
        {
            indexPage = totalCount - 2;
        }
        for (int i = 1; i <= 5 && indexPage <= pageNo; indexPage++, i++)
        {
            pages.add(indexPage);
        }

        return pages;
    }

    @JsonProperty("previous")
    public Integer getPrevious() {
        previous = 0;
        if(pageNo>1){
            previous=pageNo-1;
        }
        return previous;
    }

    @JsonProperty("next")
    public Integer getNext() {
        next=pageNo;
        if(pageNo+1<=getTotalPage()){
            next = pageNo+1;
        }
        return next;
    }
}



