package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.MovieSortParam;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieComparatorTest {

     List<Movie> movies = new ArrayList<>();
     Movie movie1 = new Movie();
     Movie movie2 = new Movie();
     Movie movie3 = new Movie();
     Movie movie4 = new Movie();
     Movie movie5 = new Movie();

    @Before
    public void setUp() {
        movie1.setRating(8.0);
        movie1.setPrice(20.0);
        movie2.setRating(6.0);
        movie2.setPrice(30.0);
        movie3.setRating(8.5);
        movie3.setPrice(15.0);
        movie4.setRating(8.0);
        movie4.setPrice(40.0);
        movie5.setRating(7.5);
        movie5.setPrice(30.0);
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
    }

    @Test
    public void testSortRatingAscPriceAsc() {
        movies.sort(new MovieComparator(new MovieSortParam("asc", "asc")));
        assertEquals(movie2, movies.get(0));
        assertEquals(movie5, movies.get(1));
        assertEquals(movie1, movies.get(2));
        assertEquals(movie4, movies.get(3));
        assertEquals(movie3, movies.get(4));
    }

    @Test
    public void testSortRatingAscPriceDesc() {
        movies.sort(new MovieComparator(new MovieSortParam("asc", "desc")));
        assertEquals(movie2, movies.get(0));
        assertEquals(movie5, movies.get(1));
        assertEquals(movie4, movies.get(2));
        assertEquals(movie1, movies.get(3));
        assertEquals(movie3, movies.get(4));
    }

    @Test
    public void testSortRatingDescPriceAsc() {
        movies.sort(new MovieComparator(new MovieSortParam("desc", "asc")));
        assertEquals(movie3, movies.get(0));
        assertEquals(movie1, movies.get(1));
        assertEquals(movie4, movies.get(2));
        assertEquals(movie5, movies.get(3));
        assertEquals(movie2, movies.get(4));
    }

    @Test
    public void testSortRatingDescPriceDesc() {
        movies.sort(new MovieComparator(new MovieSortParam("desc", "desc")));
        assertEquals(movie3, movies.get(0));
        assertEquals(movie4, movies.get(1));
        assertEquals(movie1, movies.get(2));
        assertEquals(movie5, movies.get(3));
        assertEquals(movie2, movies.get(4));
    }

    @Test
    public void testSortRatingAscPriceNull() {
        movies.sort(new MovieComparator(new MovieSortParam("asc", null)));
        assertEquals(movie2, movies.get(0));
        assertEquals(movie5, movies.get(1));
        assertEquals(movie1, movies.get(2));
        assertEquals(movie4, movies.get(3));
        assertEquals(movie3, movies.get(4));
    }

    @Test
    public void testSortRatingDescPriceNull() {
        movies.sort(new MovieComparator(new MovieSortParam("desc", null)));
        assertEquals(movie3, movies.get(0));
        assertEquals(movie1, movies.get(1));
        assertEquals(movie4, movies.get(2));
        assertEquals(movie5, movies.get(3));
        assertEquals(movie2, movies.get(4));
    }

    @Test
    public void testSortRatingNullPriceAsc() {
        movies.sort(new MovieComparator(new MovieSortParam(null, "asc")));
        assertEquals(movie3, movies.get(0));
        assertEquals(movie1, movies.get(1));
        assertEquals(movie2, movies.get(2));
        assertEquals(movie5, movies.get(3));
        assertEquals(movie4, movies.get(4));
    }

    @Test
    public void testSortRatingNullPriceDesc() {
        movies.sort(new MovieComparator(new MovieSortParam(null, "desc")));
        assertEquals(movie4, movies.get(0));
        assertEquals(movie2, movies.get(1));
        assertEquals(movie5, movies.get(2));
        assertEquals(movie1, movies.get(3));
        assertEquals(movie3, movies.get(4));
    }
}
