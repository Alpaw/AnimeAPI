package com.example.animeapi.anime.dao;

import com.example.animeapi.anime.exceptions.ErrorResponse;
import com.example.animeapi.anime.model.Anime;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IAnimeDAO {

    List<Anime> findAll();

    Optional<Anime> findById(int id);

    void save(Anime a);

    void update(Anime a,int id);

    void delete(int id);

    void deleteAll();
}
