package com.potopalskyi.movieland.entity;

import com.potopalskyi.movieland.entity.enums.SortType;
import com.potopalskyi.movieland.util.Util;

public class MovieSortParam {
    private String ratingSortType;
    private String priceSortType;

    public MovieSortParam(String ratingSortType, String priceSortType){
        if(Util.checkEnumContainsValue(SortType.class, ratingSortType)){
            this.ratingSortType = ratingSortType;
        }
        if(Util.checkEnumContainsValue(SortType.class, priceSortType)){
            this.priceSortType = priceSortType;
        }
    }

    public String getRatingSortType() {
        return ratingSortType;
    }

    public String getPriceSortType() {
        return priceSortType;
    }
}
