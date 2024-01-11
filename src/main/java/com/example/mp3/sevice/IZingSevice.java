package com.example.mp3.sevice;

import com.example.mp3.model.Zing;

import java.util.List;

public interface IZingSevice {
    List<Zing> findAll();
    void AddZing(Zing zing);

    void deleteZing(Zing zing);
    Zing findById(int id);
    void update(Zing zing);
}
