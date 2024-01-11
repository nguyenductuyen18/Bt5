package com.example.mp3.model;
import javax.persistence.*;

@Entity
@Table(name = "zing")
public class Zing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String artist;
    private String file;

    public Zing(int id, String name, String artist,String file) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.file = file;
    }

    public Zing() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
