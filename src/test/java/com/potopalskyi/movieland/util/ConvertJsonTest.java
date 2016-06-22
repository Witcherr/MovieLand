package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Country;
import com.potopalskyi.movieland.entity.Genre;
import com.potopalskyi.movieland.entity.Movie;
import com.potopalskyi.movieland.entity.Review;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertJsonTest {

    @Test
    public void toJsonTest() {
        String expectedJson = "[{\"titleRussian\":\"Ага\",\"titleEnglish\":\"Aga\",\"year\":2016,\"rating\":7.4,\"genre\":[\"Второй\",\"Первый\"]},{\"titleRussian\":\"Тест\",\"titleEnglish\":\"Test\",\"year\":2010,\"rating\":72.4,\"genre\":[\"Второй\",\"Первый\"]}]";
        Movie movie1 = new Movie();
        ConvertJson convertJson = new ConvertJson();
        movie1.setTitleRussian("Ага");
        movie1.setTitleEnglish("Aga");
        movie1.setYear(2016);
        movie1.setRating(7.4);
        Genre genre1 = new Genre();
        genre1.setName("Первый");
        Genre genre2 = new Genre();
        genre2.setName("Второй");
        movie1.setGenreList(Arrays.asList(genre1, genre2));
        Movie movie2 = new Movie();
        movie2.setTitleRussian("Тест");
        movie2.setTitleEnglish("Test");
        movie2.setYear(2010);
        movie2.setRating(72.4);
        genre1.setName("Второй");
        genre2.setName("Первый");
        movie2.setGenreList(Arrays.asList(genre1, genre2));
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        assertEquals(expectedJson, convertJson.toJson(movies));
    }

    @Test
    public void toJsonDetailedTest(){
        String expectedJson = "{\"titleRussian\":\"Ага\",\"titleEnglish\":\"Aga\",\"year\":2016,\"country\":[\"Бельгия\",\"Франция\"],\"genre\":[\"Второй\",\"Первый\",\"Детектив\"],\"description\":\"Деттективная история...\",\"review\":[\"Отличный фильм\",\"Супер\",\"Классный фильм, рекомендую!\"],\"rating\":7.4}";
        Movie movie = new Movie();
        ConvertJson convertJson = new ConvertJson();
        movie.setTitleRussian("Ага");
        movie.setTitleEnglish("Aga");
        movie.setYear(2016);
        movie.setRating(7.4);
        Genre genre1 = new Genre();
        genre1.setName("Второй");
        Genre genre2 = new Genre();
        genre2.setName("Первый");
        Genre genre3 = new Genre();
        genre3.setName("Детектив");
        movie.setGenreList(Arrays.asList(genre1, genre2, genre3));
        movie.setDescription("Деттективная история...");
        Country country1 = new Country();
        Country country2 = new Country();
        country1.setName("Бельгия");
        country2.setName("Франция");
        movie.setCountryList(Arrays.asList(country1, country2));
        Review review1 = new Review();
        Review review2 = new Review();
        Review review3 = new Review();
        review1.setDescription("Отличный фильм");
        review2.setDescription("Супер");
        review3.setDescription("Классный фильм, рекомендую!");
        movie.setReviewList(Arrays.asList(review1, review2, review3));
        System.out.println(convertJson.toJsonDetailed(movie));
        assertEquals(expectedJson, convertJson.toJsonDetailed(movie));
    }
}
