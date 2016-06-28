package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
import org.springframework.stereotype.Service;

@Service
public class GeneratorSQLQuery {
    private static final int LIMIT_FOR_PAGE = 5;
    private static final String LIKE = " like ";
    private static final String AND = "and ";
    private static final String ORDER_BY = "order by ";
    private static final String LIMIT = "limit ";
    private static final String PERCENT_RIGHT = " \"%";
    private static final String PERCENT_LEFT = "%\" ";
    private static final String NAME_RUSSIAN_FIELD = "name_rus";
    private static final String NAME_ENGLISH_FIELD = "name_eng";
    private static final String GENRE_FIELD = "genre";
    private static final String YEAR_FIELD = "year";
    private static final String COUNTRY_FIELD = "name";
    private static final String RATING_FIELD = "rating";
    private static final String PRICE_FIELD = "price";
    private static final String FREE_SPACE = " ";
    private static final String COMMA_SEPARATOR = ",";
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
    private static final String INITIAL_ALL_MOVIES_SQL = "select id, name_rus, name_eng, year, rating, price\n" +
            "                                from movie m\n";


    public String generateSearchMoviesQuery(MovieSearchParam movieSearchParam){
        StringBuilder sb = new StringBuilder(INITIAL_SEARCH_SQL);
        sb.append(addCondition(NAME_RUSSIAN_FIELD, movieSearchParam.getTitleRussian()));
        sb.append(addCondition(NAME_ENGLISH_FIELD, movieSearchParam.getTitleEnglish()));
        sb.append(addCondition(GENRE_FIELD, movieSearchParam.getGenre()));
        sb.append(addCondition(YEAR_FIELD, movieSearchParam.getYear()));
        sb.append(addCondition(COUNTRY_FIELD, movieSearchParam.getCountry()));
        sb.append(CLOSE_SEARCH_SQL);
        return sb.toString();
    }

    public String generateAllMoviesWithParamQuery(MovieSortAndLimitParam movieSortAndLimitParam){
        StringBuilder sb = new StringBuilder(INITIAL_ALL_MOVIES_SQL);
        if(movieSortAndLimitParam.getRatingSortType() != null && movieSortAndLimitParam.getPriceSortType() != null){
            sb.append(ORDER_BY + RATING_FIELD + FREE_SPACE + movieSortAndLimitParam.getRatingSortType() + COMMA_SEPARATOR +
                    PRICE_FIELD + FREE_SPACE + movieSortAndLimitParam.getPriceSortType() + "\n");
        } else if(movieSortAndLimitParam.getRatingSortType() != null){
            sb.append(ORDER_BY + RATING_FIELD + FREE_SPACE + movieSortAndLimitParam.getRatingSortType() +"\n");
        } else if(movieSortAndLimitParam.getPriceSortType() != null){
            sb.append(ORDER_BY + PRICE_FIELD + FREE_SPACE + movieSortAndLimitParam.getPriceSortType() + "\n");
        }
        sb.append(LIMIT + LIMIT_FOR_PAGE*(Integer.parseInt(movieSortAndLimitParam.getPage()) - 1) + COMMA_SEPARATOR + LIMIT_FOR_PAGE);
        return sb.toString();
    }

    private String addCondition(String field, String value){
        if(value != null){
            return AND + field + LIKE + PERCENT_RIGHT + value + PERCENT_LEFT + "\n";
        }
        return "";
    }

}

