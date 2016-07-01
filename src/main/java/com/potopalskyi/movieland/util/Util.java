package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Country;
import com.potopalskyi.movieland.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static <E extends Enum> boolean checkEnumContainsValue(Class<E> enumData , String value){
        for(Enum singleEnum: enumData.getEnumConstants()){
            if(singleEnum.toString().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }

    public static List<Genre> cloneListGenre(List<Genre> inputList){
        List<Genre> newList = new ArrayList<>();
        for(Genre genre: inputList){
            Genre singleGenre = new Genre();
            singleGenre.setId(genre.getId());
            singleGenre.setName(genre.getName());
            newList.add(singleGenre);
        }
        return newList;
    }

    public static List<Country> cloneListCountry(List<Country> inputList){
        List<Country> newList = new ArrayList<>();
        for(Country country: inputList){
            Country singleCountry = new Country();
            singleCountry.setId(country.getId());
            singleCountry.setName(country.getName());
            newList.add(singleCountry);
        }
        return newList;
    }
}
