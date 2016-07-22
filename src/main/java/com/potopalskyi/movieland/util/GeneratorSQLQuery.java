package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;
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
    private static final String RATING_FIELD = "rating ";
    private static final String PRICE_FIELD = "price ";
    private static final String FREE_SPACE = " ";
    private static final String COMMA_SEPARATOR = ",";
    private static final String INITIAL_SEARCH_SQL = "select m.id as id, name_rus, name_eng, year, rating, price " +
            "from movie m " +
            "join genre_movie gm " +
            "on m.id = gm.id_movie " +
            "join genre g " +
            "on gm.id_genre = g.id " +
            "join country_movie cm " +
            "on m.id = cm.movie_id " +
            "join country c " +
            "on c.id = cm.country_id " +
            "where 1 = 1 ";
    private static final String CLOSE_SEARCH_SQL = "group by 1, 2, 3, 4, 5";
    private static final String INITIAL_ALL_MOVIES_SQL = "select id, name_rus, name_eng, year, IFNULL(sumrating/countrating, 0) rating, price " +
            "from movie m " +
            "left join (select movie_id, sum(rating) sumrating, count(*) countrating " +
            "from rating_movie " +
            "group by movie_id) n " +
            "on m.id = n.movie_id ";


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

    public String generateAllMoviesWithParamQuery(MovieSortLimitCurrencyParam movieSortLimitCurrencyParam){
        String ratingValue = movieSortLimitCurrencyParam.getRatingSortType();
        String priceValue = movieSortLimitCurrencyParam.getPriceSortType();
        StringBuilder sb = new StringBuilder(INITIAL_ALL_MOVIES_SQL);
        if(ratingValue != null || priceValue != null){
            sb.append(ORDER_BY);
            if(ratingValue!= null){
                sb.append(RATING_FIELD).append(ratingValue).append(COMMA_SEPARATOR);
            }
            if(priceValue!=null){
                sb.append(PRICE_FIELD).append(priceValue).append(COMMA_SEPARATOR);
            }
            sb.deleteCharAt(sb.lastIndexOf(COMMA_SEPARATOR));
            sb.append(FREE_SPACE);
        }
        sb.append(LIMIT).append(LIMIT_FOR_PAGE * (Integer.parseInt(movieSortLimitCurrencyParam.getPage()) - 1)).append(COMMA_SEPARATOR).append(LIMIT_FOR_PAGE);
        return sb.toString();
    }

    private String addCondition(String field, String value){
        if(value != null){
            return AND + field + LIKE + PERCENT_RIGHT + value + PERCENT_LEFT + FREE_SPACE;
        }
        return "";
    }

}

