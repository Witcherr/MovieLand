package com.potopalskyi.movieland.util;

import com.potopalskyi.movieland.entity.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertJsonTest {

    @Test
    public void toJsonTest(){
        String expectedJson = "[{\"titleRussian\":\"Ага\",\"tittleEnglish\":\"Aga\",\"year\":2016,\"rating\":7.4,\"genre\":\"[Первый, Второй]\"},{\"titleRussian\":\"Тест\",\"tittleEnglish\":\"Test\",\"year\":2010,\"rating\":72.4,\"genre\":\"[Второй, Первый]\"}]";
        Movie movie1 = new Movie();
        ConvertJson convertJson = new ConvertJson();
        movie1.setTitleRussian("Ага");
        movie1.setTitleEnglish("Aga");
        movie1.setYear(2016);
        movie1.setRating(7.4);
        movie1.setGenreList(Arrays.asList("Первый", "Второй"));

        Movie movie2 = new Movie();
        movie2.setTitleRussian("Тест");
        movie2.setTitleEnglish("Test");
        movie2.setYear(2010);
        movie2.setRating(72.4);
        movie2.setGenreList(Arrays.asList("Второй", "Первый"));
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        assertEquals(expectedJson, convertJson.toJson(movies));
    }

    @Test
    public void tojsonDetailedTest(){
        String expectedJson = "{\"titleRussian\":\"Ага\",\"tittleEnglish\":\"Aga\",\"year\":2016,\"country\":\"[Бельгия, Франция]\",\"genre\":\"[Второй, Первый, Детектив]\",\"description\":\"Деттективная история...\",\"reviews\":\"[Отличный фильм, Супер, Классный фильм, рекомендую!]\"}";
        Movie movie = new Movie();
        ConvertJson convertJson = new ConvertJson();
        movie.setTitleRussian("Ага");
        movie.setTitleEnglish("Aga");
        movie.setYear(2016);
        movie.setRating(7.4);
        movie.setGenreList(Arrays.asList("Второй", "Первый", "Детектив"));
        movie.setDescription("Деттективная история...");
        movie.setCountryList( Arrays.asList("Бельгия", "Франция"));
        movie.setReviewList(Arrays.asList("Отличный фильм", "Супер", "Классный фильм, рекомендую!" ));
        assertEquals(expectedJson, convertJson.toJsonDetailed(movie));
    }
}
