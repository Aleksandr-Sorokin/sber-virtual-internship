package ru.sorokin.sbervirtualinternship;

import ru.sorokin.sbervirtualinternship.service.CityService;

import java.io.File;

public class SberVirtualInternshipApplication {

    public static void main(String[] args) {

        File file = new File("Задача ВС Java Сбер.csv");
        CityService cityService = new CityService();
        System.out.println(cityService.findMaxPopulationInCity(file));
    }
}
