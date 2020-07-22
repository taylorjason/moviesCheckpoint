package com.galvanize.moviesCheckpoint;

import com.fasterxml.jackson.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class Movie {

        @JsonProperty("Title")
        private String title;
        @JsonProperty("Minutes")
        private int duration;
        @JsonProperty("Genre")
        private String genre;
        @JsonProperty("Rating")
        private double rating;
        @JsonProperty("Metascore")
        private int metascore;
        @JsonProperty("Description")
        private String description;
        @JsonProperty("Votes")
        private int votes;
        private float gross;
        @JsonProperty("Year")
        private String year;
        @JsonProperty("Credits")
        private List<Person> credits;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getMetascore() {
            return metascore;
        }

        public void setMetascore(int metascore) {
            this.metascore = metascore;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        @JsonGetter("Gross")
        public float getGross() {
            return gross;
        }

        @JsonSetter("gross")
        public void setGross(float gross) {
            this.gross = gross;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<Person> getCredits() {
            return credits;
        }

        public void setCredits(List<Person> credits) {
            this.credits = credits;
        }

        public Movie(String title, int duration, String genre, double rating, int metascore, String description, int votes, float gross, String year, List<Person> credits) {
            this.title = title;
            this.duration = duration;
            this.genre = genre;
            this.rating = rating;
            this.metascore = metascore;
            this.description = description;
            this.votes = votes;
            this.gross = gross;
            this.year = year;
            this.credits = credits;
        }


    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class Person {
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @JsonCreator
        public Person(String role, String firstName, String lastName) {
            this.role = role;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @JsonProperty("Role")
        private String role;
        @JsonProperty("FirstName")
        private String firstName;
        @JsonProperty("LastName")
        private String lastName;
    }

    @GetMapping("/movie")
    public Movie getMovie(){
        Person dir = new Person("Director", "Billy", "Bob");
        Person star1 = new Person("Star", "Al", "Pacino");
        Movie movie1 = new Movie("The Godfaterh",
                175,
                "Crime, Drama",
                9.2,
                100,
                "The description",
                1561591,
                134.97f,
                "1972",
                Arrays.asList(dir, star1)
                );
        return movie1;
    }

    @PostMapping("/gross/total")
    public Object postGrossTotal(@RequestBody List<Movie> movies){
        HashMap output= new HashMap();
        double sum = 0.0;
        for (Movie movie : movies) {
            System.out.println(movie.getGross());
            sum += movie.getGross();
            System.out.println(sum);
        }
        output.put("result",  new DecimalFormat("#.##").format(sum));
        return output;
    }

}
