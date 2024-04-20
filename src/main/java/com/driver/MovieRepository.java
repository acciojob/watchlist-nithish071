package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        String moveiName = movie.getName();
        if(movieMap.containsKey(moveiName)){
            System.out.println(moveiName +"Already exists");
        } else {
            movieMap.put(moveiName,movie);
        }
    }

    public void saveDirector(Director director){
        String DirectorName = director.getName();
        if(directorMap.containsKey(DirectorName)){
            System.out.println(DirectorName +"Already exists");
        } else {
            directorMap.put(DirectorName,director);
        }
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            if(!directorMovieMapping.containsKey(director)){
                List<String> list = new ArrayList<>();
                list.add(movie);
                directorMovieMapping.put(director,list);
            } else {
                List<String> ls = directorMovieMapping.get(director);
                ls.add(movie);
                directorMovieMapping.put(director,ls);
            }
        }
    }

    public Movie findMovie(String movie){
        // your code here
        for(String mov: movieMap.keySet()){
            if(mov.equals(movie)){
                return movieMap.get(mov);
            }
        }
        return null;
    }

    public Director findDirector(String director){
        // your code here
        for(String name: directorMap.keySet()){
            if(name.equals(director)){
                return directorMap.get(name);
            }
        }
        return null;
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        for(String name: directorMovieMapping.keySet()){
            if(name.equals(director)){
                return directorMovieMapping.get(name);
            }
        }
        return null;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        List<String> movies = directorMovieMapping.get(director);
        directorMap.remove(director);
        directorMovieMapping.remove(director);
        for(String name: movies){
            movieMap.remove(name);
        }
    }

    public void deleteAllDirector(){
        List<String> directors = new ArrayList<>(directorMap.keySet());
        for(String name: directors){
            this.deleteDirector(name);
        }
    }
}