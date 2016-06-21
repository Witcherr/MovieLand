package com.potopalskyi.movieland.entity.enums;

public enum SortType {

    ASC("asc"), DESC("desc");

    private String sortId;

    SortType(String sortId){
        this.sortId = sortId;
    }

    public String sortId(){
        return sortId;
    }
}
