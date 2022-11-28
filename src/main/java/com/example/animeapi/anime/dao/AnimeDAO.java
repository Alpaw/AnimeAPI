package com.example.animeapi.anime.dao;

import com.example.animeapi.anime.exceptions.CustomDataNotFoundException;
import com.example.animeapi.anime.exceptions.ErrorResponse;
import com.example.animeapi.anime.model.Anime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AnimeDAO implements IAnimeDAO{
     List<Anime> listAnime= new ArrayList<>();
     private int cptId=0;
    Map<String,Anime> hashListAnime= new HashMap<String, Anime>();

     public AnimeDAO(){
         JSONParser jsonParser = new JSONParser();

         try {
             JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src/main/resources/animes.json"));


             for(int i=0;i<jsonArray.size();i++){

                 JSONObject jsonObject = (JSONObject) jsonArray.get(i);


                //System.out.println("Anime name : "+jsonObject.get("name"));
                Anime a=new Anime();
                a.setId(Integer.toString(i));
                a.setName(jsonObject.get("name").toString());
                a.setCreatedAt(jsonObject.get("createdAt").toString());
                a.setCountry(jsonObject.get("country").toString());
                a.setNbChap(jsonObject.get("nbChap").toString());
                a.setOther(jsonObject.get("other").toString());
                a.setLike(jsonObject.get("like").toString());
                a.setDislike(jsonObject.get("dislike").toString());
                a.setGenre(jsonObject.get("genre").toString());
                a.setDescription(jsonObject.get("description").toString());
                a.setImageURL(jsonObject.get("imageURL").toString());
                a.setManga(jsonObject.get("manga").toString());

/*
                System.out.println(a.getId());
                System.out.println(a.getName());
                System.out.println(a.getCreatedAt());
                System.out.println(a.getCountry());
                System.out.println(a.getNbChap());
                System.out.println(a.getOther());
                System.out.println(a.getLike());
                System.out.println(a.getDislike());
                System.out.println(a.getGenre());
                System.out.println(a.getDescription());
                System.out.println(a.getImageURL());
                System.out.println(a.getManga());

                 */
                 this.save(a);
                 String jsonInputString = jsonObject.toString();


                     //System.out.println(response.toString());
                 }

             }catch (IOException | ParseException e){
             System.out.println("error");
         }


     }
    @Override
    public List<Anime> findAll(){
       // System.out.println(" findAll Not implemented");

        //return listAnime.stream()
          //      .filter(Objects::nonNull)
            //    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        return  (hashListAnime.values())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

     }

    @Override
    public Optional<Anime> findById(int id){
        //System.out.println(" findById Not implemented");

        //return Optional.ofNullable(listAnime.get(id));
        if(hashListAnime.get(Integer.toString(id))==null){
            throw new CustomDataNotFoundException("Anime with id : "+id+" not found");
        }

        return Optional.ofNullable(hashListAnime.get(Integer.toString(id)));
    }

    @Override
    public void save(Anime a){
        listAnime.add(a);

        //a.setId(Integer.toString(listAnime.size()-1));
        a.setId(Integer.toString(cptId));
        cptId++;

        hashListAnime.put(a.getId(),a);

        System.out.println("The anime with name : "+a.getName()+" is now in the database");
        //System.out.println(" save Not implemented");



    }

    @Override
    public void update(Anime anime, int id){

        System.out.println("The anime with the id : "+id+" is updated.");
        Anime a=listAnime.get((id));



        a=hashListAnime.get(Integer.toString(id));



        //Update all the fields

        if(a!=null){
            a.setName(anime.getName()!=null ? anime.getName() : a.getName());
            a.setCreatedAt(anime.getCreatedAt()!=null ? anime.getCreatedAt() : a.getCreatedAt());
            a.setCountry(anime.getCountry()!=null ? anime.getCountry() : a.getCountry());
            a.setNbChap(anime.getNbChap()!=null ? anime.getNbChap() : a.getNbChap());
            a.setOther(anime.getOther()!=null ? anime.getOther() : a.getOther());
            a.setLike(anime.getLike()!=null ? anime.getLike() : a.getLike());
            a.setDislike(anime.getDislike()!=null ? anime.getDislike() : a.getDislike());
            a.setGenre(anime.getGenre()!=null ? anime.getGenre() : a.getGenre());
            a.setDescription(anime.getDescription()!=null ? anime.getDescription() : a.getDescription());
            a.setImageURL(anime.getImageURL()!=null ? anime.getImageURL() : a.getImageURL());
            a.setManga(anime.getManga()!=null ? anime.getManga() : a.getManga());
            a.setAuthor(anime.getAuthor()!=null ? anime.getAuthor() : a.getAuthor());
        } else {
            throw new CustomDataNotFoundException();
        }



    }

    @Override
    public void delete(int id) {
         int size=hashListAnime.size();
        listAnime.remove(hashListAnime.get(Integer.toString(id)));
        hashListAnime.remove(Integer.toString(id));



        if(size==hashListAnime.size()){

            System.out.println("Anime with id : "+id+" was not found");

            throw new CustomDataNotFoundException("Anime with id : "+id+" was not found");


        }
        System.out.println("Anime with id : "+id+" is deleted");

     }

    @Override
    public void deleteAll(){

         hashListAnime.clear();
         listAnime.clear();
         if(hashListAnime.size()==0){
             System.out.println("Database is now empty");
         }
    }
}
