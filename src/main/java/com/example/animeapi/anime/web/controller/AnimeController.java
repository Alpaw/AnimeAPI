package com.example.animeapi.anime.web.controller;

import com.example.animeapi.anime.dao.IAnimeDAO;
import com.example.animeapi.anime.exceptions.CustomDataNotFoundException;
import com.example.animeapi.anime.exceptions.ErrorResponse;
import com.example.animeapi.anime.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimeController {


    @Autowired
    private IAnimeDAO dao;

    @ResponseBody
    @GetMapping(value="/Animes")
    public List<Anime> getLesAnimes(){
        return dao.findAll();
    }

    /*
    @GetMapping("/allAnimes")
    public String showAll(Model model){
        model.addAttribute("animes", dao.findAll());
        return "allAnimes";
    }

     */


    @PostMapping(value="/Animes")
    public void addAnimes(@RequestBody Anime a){
        dao.save(a);
    }

    @PutMapping(value="/Animes/{id}")
    public ResponseEntity<ErrorResponse> updateAnimes(@RequestBody Anime a, @PathVariable int id){
        try{
            dao.update(a,id);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<>(
                    new ErrorResponse(
                            status,
                            "Anime with id : "+id+" is updated"
                    ),
                    status
            );

        }catch(CustomDataNotFoundException cs){
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(
                    new ErrorResponse(
                            status,
                            "Anime with id : "+id+" was not found."
                    ),
                    status
            );
        }
    }


    @DeleteMapping(value="/Animes/{id}")

    public ResponseEntity<ErrorResponse> delete(@PathVariable int id){


        try{
            dao.delete(id);
            HttpStatus status = HttpStatus.OK;
            return new ResponseEntity<>(
                    new ErrorResponse(
                            status,
                            "Anime with id : "+id+" is deleted"
                    ),
                    status
            );

        }catch (CustomDataNotFoundException cs){
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(
                    new ErrorResponse(
                            status,
                            cs.getMessage()
                    ),
                    status
            );
        }


    }

    @DeleteMapping(value="Animes/deleteAll")
    @ResponseStatus(code = HttpStatus.OK, reason = "Database is now empty")
    public void deleteAll(){
        dao.deleteAll();
    }


    @ResponseBody
    @GetMapping(value="/Animes/{id}")

    public Anime getAnime(@PathVariable int id) {



        try{
            return dao.findById(id).orElse(null);

        }catch (CustomDataNotFoundException cs){
            return new Anime();//on retourne un anime vide si ya pas l'anime dans la bdd
        }



    }



}
