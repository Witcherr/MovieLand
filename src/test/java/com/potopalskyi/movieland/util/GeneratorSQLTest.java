package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.MovieSearchParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorSQLTest {

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
        GeneratorSQL generatorSQL = new GeneratorSQL();
        MovieSearchParam movieSearchParam = new MovieSearchParam();
        movieSearchParam.setTitleEnglish("TestMovie");
        movieSearchParam.setCountry("Франция");
        assertEquals(expectedString, generatorSQL.generateSearchMovies(movieSearchParam));
    }
}
