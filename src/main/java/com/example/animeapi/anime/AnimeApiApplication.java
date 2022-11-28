package com.example.animeapi.anime;

import com.example.animeapi.anime.model.Anime;
import com.example.animeapi.anime.web.controller.AnimeController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

@SpringBootApplication
public class AnimeApiApplication {

    public static void main(String[] args) {


        SpringApplication.run(AnimeApiApplication.class, args);

        //Fill the db
        AnimeController animeController=new AnimeController();
        JSONParser jsonParser = new JSONParser();

        //Parsing the contents of the JSON file
        /*
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src/main/resources/animes.json"));


            for(int i=0;i<jsonArray.size();i++){

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);


                System.out.println("Anime name : "+jsonObject.get("name"));
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



               // animeController.addAnimeForDb(a);

               URL url = new URL("http://localhost:8080/Animes");
               HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                String jsonInputString = jsonObject.toString();

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    //System.out.println(response.toString());
                }

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //Iterating the contents of the array

        /*Iterator<String> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

         */


    }

}
