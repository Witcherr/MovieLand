package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.MovieSearchParam;
import org.springframework.stereotype.Service;

@Service
public class GeneratorSQL {
    private static final String LIKE = " like ";
    private static final String AND = "and ";
    private static final String PERCENT_RIGHT = " \"%";
    private static final String PERCENT_LEFT = "%\" ";
    private static final String NAME_RUSSIAN_FIELD = "name_rus";
    private static final String NAME_ENGLISH_FIELD = "name_eng";
    private static final String GENRE_FIELD = "genre";
    private static final String YEAR_FIELD = "year";
    private static final String COUNTRY_FIELD = "name";
    private static final String INITIAL_SEARCH_SQL = "select m.id as id, name_rus, name_eng, year, rating, price\n" +
            "from movie m\n" +
            "join genre_movie gm\n" +
            "on m.id = gm.id_movie\n" +
            "join genre g\n" +
            "on gm.id_genre = g.id\n" +
            "join country_movie cm\n" +
            "on m.id = cm.movie_id\n" +
            "join country c\n" +
            "on c.id = cm.country_id\n" +
            "where 1 = 1\n";
    private static final String CLOSE_SEARCH_SQL = "group by 1, 2, 3, 4, 5";

    public String generateSearchMovies(MovieSearchParam movieSearchParam){
        StringBuilder sb = new StringBuilder(INITIAL_SEARCH_SQL);
        sb.append(addCondition(NAME_RUSSIAN_FIELD, movieSearchParam.getTitleRussian()));
        sb.append(addCondition(NAME_ENGLISH_FIELD, movieSearchParam.getTitleEnglish()));
        sb.append(addCondition(GENRE_FIELD, movieSearchParam.getGenre()));
        sb.append(addCondition(YEAR_FIELD, movieSearchParam.getYear()));
        sb.append(addCondition(COUNTRY_FIELD, movieSearchParam.getCountry()));
        sb.append(CLOSE_SEARCH_SQL);
        return sb.toString();
    }

    private String addCondition(String field, String value){
        if(value != null){
            return AND + field + LIKE + PERCENT_RIGHT + value + PERCENT_LEFT + "\n";
        }
        return "";
    }

}

