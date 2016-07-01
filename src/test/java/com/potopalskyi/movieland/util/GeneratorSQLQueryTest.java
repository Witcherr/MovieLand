package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
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
        String expected1 = "select id, name_rus, name_eng, year, rating, price " +
                "                                from movie m " +
                "order by rating asc,price asc " +
                "limit 5,5";
        String expected2 = "select id, name_rus, name_eng, year, rating, price " +
                "                                from movie m " +
                "limit 0,5";
        String expected3 = "select id, name_rus, name_eng, year, rating, price " +
                "                                from movie m " +
                "order by rating desc " +
                "limit 45,5";
        MovieSortAndLimitParam movieSortAndLimitParam1 = new MovieSortAndLimitParam("asc", "asc", "2");
        MovieSortAndLimitParam movieSortAndLimitParam2 = new MovieSortAndLimitParam(null, "ascasasd", "1");
        MovieSortAndLimitParam movieSortAndLimitParam3 = new MovieSortAndLimitParam("desc", "ascasasd", "10");
        assertEquals(expected1, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam1));
        assertEquals(expected2, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam2));
        assertEquals(expected3, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam3));
    }
}
