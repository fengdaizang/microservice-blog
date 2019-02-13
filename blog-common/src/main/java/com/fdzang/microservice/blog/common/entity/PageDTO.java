package com.fdzang.microservice.blog.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDTO<T> {

    private List<T> result;

    @JsonProperty("total_count")
    private long totalCount;

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
}
