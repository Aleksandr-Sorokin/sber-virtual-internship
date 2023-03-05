package ru.sorokin.sbervirtualinternship;

import ru.sorokin.sbervirtualinternship.enums.TypeSortCity;
import ru.sorokin.sbervirtualinternship.model.City;
import ru.sorokin.sbervirtualinternship.service.CityService;

import java.io.File;
import java.util.List;

public class SberVirtualInternshipApplication {

    public static void main(String[] args) {
        TypeSortCity typeSort = TypeSortCity.DESC_NAME_CASE_INSENSITIVE;
        if (args.length > 0) {
            if (TypeSortCity.DESC_NAME_CASE_INSENSITIVE.equals(TypeSortCity.valueOf(args[0])) ||
                    TypeSortCity.DESC_DISTRICT_CASE_SENSITIVE.equals(TypeSortCity.valueOf(args[0]))) {
                typeSort = TypeSortCity.valueOf(args[0]);
            }
        }

        File file = new File("Задача ВС Java Сбер.csv");
        CityService cityService = new CityService();
        List<City> cities = cityService.createListCity(file, typeSort);
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
