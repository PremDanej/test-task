package com.test.task.controllers;

import com.test.task.model.Movies;
import com.test.task.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {
    @Autowired
    MoviesRepository repository;

    @GetMapping("/movies")
    public List getMovies() {
        return repository.getMovies();
    }

    @GetMapping("/longest-duration-movies")
    public List getHighestMovies() {
        return repository.getHighestMovies();
    }

    @GetMapping("/top-rated-movies")
    public List getTopRatedMovies() {
        return repository.getTopRatedMovies();
    }

    @GetMapping("/genre-movies-with-subtotals")
    public List getMoviesWithSubTotal() {
        return repository.getMoviesWithSubTotal();
    }

    @PostMapping("/new-movie")
    public ResponseEntity<String> addNewMovie(@RequestBody Movies movies) {
        repository.addNewMovie(movies);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/update-runtime-minutes")
    public void updateRuntimeMinutes() {
        repository.updateRuntimeMinutes();
    }

}
