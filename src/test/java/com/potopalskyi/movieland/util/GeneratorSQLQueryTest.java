package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.MovieSearchParam;
import com.potopalskyi.movieland.entity.MovieSortAndLimitParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorSQLQueryTest {

    private GeneratorSQLQuery generatorSQLQuery = new GeneratorSQLQuery();

    @Test
    public void generateSearchMoviesTest(){
        String expectedString = "select m.id as id, name_rus, name_eng, year, rating, price\n" +
                "from movie m\n" +
                "join genre_movie gm\n" +
                "on m.id = gm.id_movie\n" +
                "join genre g\n" +
                "on gm.id_genre = g.id\n" +
                "join country_movie cm\n" +
                "on m.id = cm.movie_id\n" +
                "join country c\n" +
                "on c.id = cm.country_id\n" +
                "where 1 = 1\n" +
                "and name_eng like  \"%TestMovie%\" \n" +
                "and name like  \"%Франция%\" \n" +
                "group by 1, 2, 3, 4, 5";
        MovieSearchParam movieSearchParam = new MovieSearchParam();
        movieSearchParam.setTitleEnglish("TestMovie");
        movieSearchParam.setCountry("Франция");
        assertEquals(expectedString, generatorSQLQuery.generateSearchMoviesQuery(movieSearchParam));
    }

    @Test
    public void generateAllMoviesWithParamQueryTest(){
        String expected1 = "select id, name_rus, name_eng, year, rating, price\n" +
                "                                from movie m\n" +
                "order by rating asc,price asc\n" +
                "limit 5,5";
        String expected2 = "select id, name_rus, name_eng, year, rating, price\n" +
                "                                from movie m\n" +
                "limit 0,5";
        String expected3 = "select id, name_rus, name_eng, year, rating, price\n" +
                "                                from movie m\n" +
                "order by rating desc\n" +
                "limit 45,5";
        MovieSortAndLimitParam movieSortAndLimitParam1 = new MovieSortAndLimitParam("asc", "asc", "2");
        MovieSortAndLimitParam movieSortAndLimitParam2 = new MovieSortAndLimitParam(null, "ascasasd", "1");
        MovieSortAndLimitParam movieSortAndLimitParam3 = new MovieSortAndLimitParam("desc", "ascasasd", "10");
        assertEquals(expected1, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam1));
        assertEquals(expected2, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam2));
        assertEquals(expected3, generatorSQLQuery.generateAllMoviesWithParamQuery(movieSortAndLimitParam3));
    }
}
