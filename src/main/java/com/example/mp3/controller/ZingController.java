package com.example.mp3.controller;


import com.example.mp3.model.Zing;
import com.example.mp3.model.ZingForm;
import com.example.mp3.sevice.IZingSevice;
import com.example.mp3.sevice.ZingSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ZingController {

    @Autowired
    private IZingSevice zingService = new ZingSevice();

    @GetMapping("/zings")
    public String index(Model model) {
        List<Zing> zingList = zingService.findAll();
        model.addAttribute("zings", zingList);
        return "/index";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("zings", new ZingForm());
        return "/add";
    }
    @Value("${file-upload}")
    private String fileUpload;
    @PostMapping("/save")
    public String save(@ModelAttribute("zings") ZingForm zingForm) {
        MultipartFile multipartFile = zingForm.getFile();
        String fileName= multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(zingForm.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Zing zing = new Zing(zingForm.getId(),zingForm.getName(),zingForm.getArtist(),fileName);
        zingService.AddZing(zing);
        return "redirect:/zings";
    }
    @GetMapping("{id}/edit")
    public String edit(Model model,@PathVariable int id) {
        model.addAttribute("zings",zingService.findById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("zings")ZingForm zingForm){
        MultipartFile multipartFile = zingForm.getFile();
        String fileName= multipartFile.getOriginalFilename();
        Zing zing =  new Zing(zingForm.getId(),zingForm.getName(),zingForm.getArtist(),fileName);
        zingService.update(zing);
        return "redirect:/zings";
    }

    @GetMapping("{id}/delete")
    public String delete(Model model,@PathVariable int id) {
        model.addAttribute("zings",zingService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String deleteZing(ZingForm zingForm) {
        MultipartFile multipartFile = zingForm.getFile();
        String fileName= multipartFile.getOriginalFilename();
        Zing zing =  new Zing(zingForm.getId(),zingForm.getName(),zingForm.getArtist(),fileName);
zingService.deleteZing(zing);
        return "redirect:/zings";
    }
}