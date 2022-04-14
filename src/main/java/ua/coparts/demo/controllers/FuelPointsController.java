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
import ua.coparts.demo.models.FuelPoint;
import ua.coparts.demo.models.MyReverseComparator;
import ua.coparts.demo.models.User;
import ua.coparts.demo.repo.CarRepository;
import ua.coparts.demo.repo.FuelPointRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class FuelPointsController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FuelPointRepository fuelPointRepository;

    @GetMapping("/garage/car_{carId}")
    public String fuelPoints(@AuthenticationPrincipal User user, @PathVariable(value = "carId") long carId, Model model){
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Optional<Car> findCarInDB = carRepository.findById(carId);
        ArrayList<Car> res = new ArrayList<>();
        findCarInDB.ifPresent(res :: add);
        List<FuelPoint> fuelPoints = fuelPointRepository.findByCarId(res.get(0));
        Collections.sort(fuelPoints, new MyReverseComparator());
        model.addAttribute("fuel", fuelPoints);
        model.addAttribute("user", user);
        model.addAttribute("car", res);
        return "FuelPoints";
    }

    @PostMapping("/garage/car_{carId}")
    public String newFuelPoints(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "carId") long carId,
            @RequestParam int odometer,
            @RequestParam double refueling,
            @RequestParam double price,
            Model model) throws ResourceNotFoundException {
        if(!carRepository.existsById(carId)){
            return "redirect:/garage";
        }
        Optional<Car> findCarInDB = Optional.ofNullable(carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Id Type Not Found " + carId)));;
        ArrayList<Car> res = new ArrayList<>();
        findCarInDB.ifPresent(res :: add);
        FuelPoint fuelPoint = new FuelPoint(odometer, refueling, price, res.get(0));
        fuelPointRepository.save(fuelPoint);
        List<FuelPoint> fuelPoints = fuelPointRepository.findByCarId(res.get(0));
        model.addAttribute("fuel",fuelPoints);
        model.addAttribute("user", user);
        model.addAttribute("car", res);
        return "FuelPoints";
    }
}
