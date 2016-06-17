package com.potopalskyi.movieland.util;

public class GeneratorSQL {
    private static final String LIKE = "like";
    private static final String AND = "and";
    private static final String PERCENT_RIGHT = "\"%";
    private static final String PERCENT_LEFT = "%\"";
    private static final String NAME_RUSSIAN_FIELD = "name_rus";
    private static final String NAME_ENGLISH_FIELD = "name_eng";
    private static final String GENRE_FIELD = "genre";
    private static final String YEAR_FIELD = "year";
    private static final String INITIAL_SEARCH_SQL = "select name_rus, name_eng, description, year, rating, group_concat(distinct g.genre separator ', ') as genreLis\n" +
            "from movie m\n" +
            "join genre_movie gm\n" +
            "on m.id = gm.id_movie\n" +
            "join genre g\n" +
            "on gm.id_genre = g.id\n" +
            "join country_movie cm\n" +
            "on m.id = cm.movie_id\n" +
            "where 1 = 1";
    private static final String CLOSE_SEARCH_SQL = "group by 1, 2, 3, 4, 5";

    public static String generateSearchMovies(String nameRussian, String nameEnglish, String genre, String year){
        StringBuilder sb = new StringBuilder(INITIAL_SEARCH_SQL);
        sb.append(addCondition(NAME_RUSSIAN_FIELD, nameRussian));
        sb.append(addCondition(NAME_ENGLISH_FIELD, nameEnglish));
        sb.append(addCondition(GENRE_FIELD, genre));
        sb.append(addCondition(YEAR_FIELD, year));
        return "";
    }

    private static String addCondition(String field, String value){
        if(value != null){
            return field + LIKE + PERCENT_LEFT + value + PERCENT_RIGHT + "\n";
        }
        return "";
    }

}

