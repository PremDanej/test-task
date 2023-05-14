package com.test.task.repository;

import com.test.task.model.GenresWithTotal;
import com.test.task.model.HighestMovies;
import com.test.task.model.Movies;
import com.test.task.model.TopRatedMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviesRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movies> getMovies() {
        return jdbcTemplate.query("select * from movies", BeanPropertyRowMapper.newInstance(Movies.class));
    }

    public List<HighestMovies> getHighestMovies() {
        return jdbcTemplate.query("select tconst, primaryTitle, runtimeMinutes, genres from movies order by runtimeMinutes DESC LIMIT 10"
                , BeanPropertyRowMapper.newInstance(HighestMovies.class));
    }

    public List<TopRatedMovies> getTopRatedMovies() {
        return jdbcTemplate.query("select m.tconst, m.primaryTitle, m.genres, r.averageRating from movies as m, ratings as r where m.tconst = r.tconst AND r.averageRating > 6.0 ORDER BY r.averageRating"
                , BeanPropertyRowMapper.newInstance(TopRatedMovies.class));
    }

    public List<GenresWithTotal> getMoviesWithSubTotal() {
        return jdbcTemplate.query("SELECT\n" +
                        "\t\tmovies.genres, movies.primaryTitle, ratings.numVotes, TotalNumVotes.Total\n" +
                        "\tFROM movies\n" +
                        "\t\tLEFT JOIN ratings ON movies.tconst = ratings.tconst\n" +
                        "\t\tLEFT JOIN (\n" +
                        "\t\t\tSELECT movies.genres, SUM(ratings.numVotes) AS Total\n" +
                        "\t\t\tFROM movies LEFT JOIN ratings ON movies.tconst = ratings.tconst\n" +
                        "\t\t\tGROUP BY movies.genres\n" +
                        "\t\t) AS TotalNumVotes ON movies.genres = TotalNumVotes.genres\n" +
                        "\n" +
                        "\tORDER BY\n" +
                        "\t\tmovies.genres, movies.primaryTitle"
                , BeanPropertyRowMapper.newInstance(GenresWithTotal.class));
    }


    public void addNewMovie(Movies movies) {
        jdbcTemplate.update("insert into movies values(?,?,?,?,?)"
                , movies.getTconst(),
                movies.getTitleType(),
                movies.getPrimaryTitle(),
                movies.getRuntimeMinutes(),
                movies.getGenres());
    }

    public void updateRuntimeMinutes() {
        jdbcTemplate.update("UPDATE movies SET runtimeMinutes = CASE \n" +
                "\tWHEN genres = 'Documentary' THEN runtimeMinutes + 15\n" +
                "\tWHEN genres = 'Animation' THEN runtimeMinutes + 30\n" +
                "\tELSE runtimeMinutes + 45\n" +
                "\tEND");
    }
}
