package ua.coparts.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.coparts.demo.Exception.ResourceNotFoundException;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.CarBrand;
import ua.coparts.demo.models.User;
import ua.coparts.demo.repo.CarBrandRepository;
import ua.coparts.demo.repo.CarRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class GarageController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @GetMapping("/garage")
    public String garage(Model model){
        Iterable<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "garage";
    }

    @GetMapping("/garage/addCar")
    public String addCar(Model model){
        Iterable<CarBrand> carBrands = carBrandRepository.findAll();
        model.addAttribute("carBrands", carBrands);
        return "carAdd";
    }

    @PostMapping("/garage/addCar")
    public String addNewCar(
            @AuthenticationPrincipal User user,
            @RequestParam String carBrand,
            @RequestParam String carModel,
            @RequestParam int carYear,
            @RequestParam String carColor,
            @RequestParam String carDesc,
            Model model){
        if(carBrand == "notSwitched"){
            return "redirect:/garage/addCar";
        }
        Car car = new Car(carBrand, carModel, carYear, carColor, carDesc, user);
        carRepository.save(car);
        return "redirect:/garage";
    }

    @GetMapping("/garage/car_{carId}")
    public String carDetails(@PathVariable(value = "carId") long carId, Model model){
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Optional<Car> post = carRepository.findById(carId);
        ArrayList<Car> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("car", res);
        return "carDetails";
    }

    @GetMapping("/garage/car_{carId}/edit")
    public String carEdit(@PathVariable(value = "carId") long carId, Model model){
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Iterable<CarBrand> carBrands = carBrandRepository.findAll();
        model.addAttribute("carBrands", carBrands);
        Optional<Car> post = carRepository.findById(carId);
        ArrayList<Car> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("car", res);
        return "carEdit";
    }

    @PostMapping("/garage/car_{carId}/edit")
    public String carUpdate(@PathVariable(value = "carId") long carId, @RequestParam String carBrand, @RequestParam String carModel, @RequestParam int carYear, @RequestParam String carColor, @RequestParam String carDesc, Model model) throws ResourceNotFoundException {
        if(carBrand == "notSwitched"){
            return "redirect:/garage/car_{carId}/edit";
        }
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Id Type Not Found " + carId));
        car.setCarBrand(carBrand);
        car.setCarModel(carModel);
        car.setCarYear(carYear);
        car.setCarColor(carColor);
        car.setCarDesc(carDesc);
        carRepository.save(car);
        return "redirect:/garage";
    }

    @PostMapping("/garage/car_{carId}/remove")
    public String carRemove(@PathVariable(value = "carId") long carId, Model model) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Id Type Not Found " + carId));
        carRepository.delete(car);
        return "redirect:/garage";
    }
}
