package com.potopalskyi.movieland.util;

public class Util {
    public static <E extends Enum> boolean checkEnumContainsValue(Class<E> enumData , String value){
        for(Enum singleEnum: enumData.getEnumConstants()){
            if(singleEnum.toString().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
