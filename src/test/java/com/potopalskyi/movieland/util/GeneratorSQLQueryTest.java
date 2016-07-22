package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.param.MovieSearchParam;
import com.potopalskyi.movieland.entity.param.MovieSortLimitCurrencyParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorSQLQueryTest {

    private GeneratorSQLQuery generatorSQLQuery = new GeneratorSQLQuery();

    @Test
    public void generateSearchMoviesTest(){
        String expectedString = "select m.id as id, name_rus, name_eng, year, rating, price " +
                "from movie m " +
                "join genre_movie gm " +
                "on m.id = gm.id_movie " +
                "join genre g " +
                "on gm.id_genre = g.id " +
                "join country_movie cm " +
                "on m.id = cm.movie_id " +
                "join country c " +
                "on c.id = cm.country_id " +
                "where 1 = 1 " +
                "and name_eng like  \"%TestMovie%\"  " +
                "and name like  \"%Франция%\"  " +
                "group by 1, 2, 3, 4, 5";
        MovieSearchParam movieSearchParam = new MovieSearchParam();
        movieSearchParam.setTitleEnglish("TestMovie");
        movieSearchParam.setCountry("Франция");
        assertEquals(expectedString, generatorSQLQuery.generateSearchMoviesQuery(movieSearchParam));
    }

    @Test
    public void generateAllMoviesWithParamQueryTest(){
        String expected1 = "select id, name_rus, name_eng, year, IFNULL(sumrating/countrating, 0) rating, price from movie m left join (select movie_id, sum(rating) sumrating, count(*) countrating from rating_movie group by movie_id) n on m.id = n.movie_id order by rating asc,price asc limit 5,5";
        String expected2 = "select id, name_rus, name_eng, year, IFNULL(sumrating/countrating, 0) rating, price from movie m left join (select movie_id, sum(rating) sumrating, count(*) countrating from rating_movie group by movie_id) n on m.id = n.movie_id limit 0,5";
        String expected3 = "select id, name_rus, name_eng, year, IFNULL(sumrating/countrating, 0) rating, price from movie m left join (select movie_id, sum(rating) sumrating, count(*) countrating from rating_movie group by movie_id) n on m.id = n.movie_id order by rating desc limit 45,5";
        MovieSortLimitCurrencyParam movieSortLimitCurrencyParam1 = new MovieSortLimitCurrencyParam("asc", "asc", "2", null);
        MovieSortLimitCurrencyParam movieSortLimitCurrencyParam2 = new MovieSortLimitCurrencyParam(null, "ascasasd", "1", null);
        MovieSortLimitCurrencyParam movieSortLimitCurrencyParam3 = new MovieSortLimitCurrencyParam("desc", "ascasasd", "10", null);
        assertEquals(expected1, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortLimitCurrencyParam1));
        assertEquals(expected2, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortLimitCurrencyParam2));
        assertEquals(expected3, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortLimitCurrencyParam3));
    }
}
