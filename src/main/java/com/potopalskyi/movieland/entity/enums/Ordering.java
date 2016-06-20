package com.potopalskyi.movieland.entity.enums;

public enum Ordering {

    ASC("asc"), DESC("desc");

    private String sortId;

    Ordering(String sortId){
        this.sortId = sortId;
    }

    public String sortId(){
        return sortId;
    }
}
