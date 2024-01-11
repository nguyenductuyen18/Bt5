package com.example.mp3.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class ZingForm {
    private int id;
    private String name;
    private String artist;
    private MultipartFile file;

    public ZingForm( String name, String artist,MultipartFile file) {
        this.name = name;
        this.artist = artist;
        this.file = file;
    }

    public ZingForm() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
}

