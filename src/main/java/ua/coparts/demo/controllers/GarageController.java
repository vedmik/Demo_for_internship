package ua.coparts.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.coparts.demo.Exception.ResourceNotFoundException;
import ua.coparts.demo.models.Car;
import ua.coparts.demo.models.User;
import ua.coparts.demo.repo.CarRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Controller
public class GarageController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/garage")
    public String garage(@AuthenticationPrincipal User user, Model model){
        Iterable<Car> cars = carRepository.findByOwner(user);
        model.addAttribute("cars", cars);
        model.addAttribute("user", user);
        return "garage";
    }

    @GetMapping("/garage/addCar")
    public String addCar(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
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

        Car car = new Car(carBrand, carModel, carYear, carColor, carDesc, user);
        carRepository.save(car);
        return "redirect:/garage";
    }

    @GetMapping("/garage/car_{carId}")
    public String carDetails(@AuthenticationPrincipal User user, @PathVariable(value = "carId") long carId, Model model){
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Optional<Car> post = carRepository.findById(carId);
        ArrayList<Car> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("user", user);
        model.addAttribute("car", res);
        return "carDetails";
    }

    @GetMapping("/garage/car_{carId}/edit")
    public String carEdit(@AuthenticationPrincipal User user, @PathVariable(value = "carId") long carId, Model model){
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Optional<Car> findCarInDB = carRepository.findById(carId);
        ArrayList<Car> res = new ArrayList<>();
        findCarInDB.ifPresent(res :: add);
        model.addAttribute("user", user);
        model.addAttribute("car", res);
        return "carEdit";
    }

    @PostMapping("/garage/car_{carId}/edit")
    public String carUpdate(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "carId") long carId,
            @RequestParam String carBrand,
            @RequestParam String carModel,
            @RequestParam int carYear,
            @RequestParam String carColor,
            @RequestParam String carDesc,
            Model model) throws ResourceNotFoundException {
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
    public String carRemove(@AuthenticationPrincipal User user, @PathVariable(value = "carId") long carId, Model model) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Id Type Not Found " + carId));
        carRepository.delete(car);
        return "redirect:/garage";
    }
}
