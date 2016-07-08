package com.potopalskyi.movieland.entity.param;

import com.potopalskyi.movieland.entity.enums.SortType;
import com.potopalskyi.movieland.util.Util;

public class MovieSortAndLimitParam {
    private String ratingSortType;
    private String priceSortType;
    private String page;

    public MovieSortAndLimitParam(String ratingSortType, String priceSortType, String page){
        if(Util.checkEnumContainsValue(SortType.class, ratingSortType)){
            this.ratingSortType = ratingSortType;
        }
        if(Util.checkEnumContainsValue(SortType.class, priceSortType)){
            this.priceSortType = priceSortType;
        }
        this.page = page;
    }

    public String getRatingSortType() {
        return ratingSortType;
    }

    public String getPriceSortType() {
        return priceSortType;
    }

    public String getPage() {
        return page;
    }
}
