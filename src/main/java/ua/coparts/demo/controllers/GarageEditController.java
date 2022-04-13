package ua.coparts.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.coparts.demo.Exception.ResourceNotFoundException;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.CarBrand;
import ua.coparts.demo.repo.CarBrandRepository;

import java.sql.SQLOutput;

@Controller
public class GarageEditController {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @GetMapping("/garage/edit")
    public String garage(Model model){
        Iterable<CarBrand> carBrands = carBrandRepository.findAll();
        model.addAttribute("carBrands", carBrands);
        return "garageEdit";
    }

    @PostMapping("/garage/edit")
    public String brandAdd(@RequestParam String newCarBrand, Model model){
        String newCarBrandUpper = newCarBrand.toUpperCase().trim();
        Iterable<CarBrand> checkCarBrands = carBrandRepository.findAll();
        for(CarBrand key : checkCarBrands){
            if( key.getCarBrand().equals(newCarBrandUpper)){
                return "redirect:/garage/edit";
            }
        }
        CarBrand carBrand = new CarBrand(newCarBrandUpper);
        carBrandRepository.save(carBrand);
        return "redirect:/garage/edit";
    }

    @PostMapping("/garage/edit/brandDelete")
    public String brandDelete(@RequestParam Long removedCarBrandId, Model model) throws ResourceNotFoundException {
        CarBrand carBrand = carBrandRepository.findById(removedCarBrandId).orElseThrow(() -> new ResourceNotFoundException("Id Type Not Found " + removedCarBrandId));
        carBrandRepository.delete(carBrand);
        return "redirect:/garage/edit";
    }

}
