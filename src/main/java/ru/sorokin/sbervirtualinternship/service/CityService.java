package ru.sorokin.sbervirtualinternship.service;

import ru.sorokin.sbervirtualinternship.enums.TypeSortCity;
import ru.sorokin.sbervirtualinternship.model.City;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CityService {
    ReadFiles readFiles;

    public CityService() {
        this.readFiles = new ReadFiles();
    }

    public List<City> createSortListCity(File file, TypeSortCity sort) {
        Comparator<City> compareCity = Comparator.comparing(City::getId);
        if (TypeSortCity.DESC_DISTRICT_CASE_SENSITIVE.equals(sort)) {
            compareCity = Comparator.comparing(City::getDistrict).thenComparing(City::getName);
        } else if (TypeSortCity.DESC_NAME_CASE_INSENSITIVE.equals(sort)) {
            compareCity = Comparator.comparing(City::getName, String::compareToIgnoreCase);
        }
        List<String> stringList = readFiles.getLinesFromFile(file);
        List<City> cities = stringList.stream().map(lineCity -> {
            String[] cityToArray = lineCity.split(";");
            if (cityToArray.length < 6) {
                return new City(Integer.valueOf(cityToArray[0]),
                        cityToArray[1], cityToArray[2], cityToArray[3],
                        Integer.valueOf(cityToArray[4]), "");

            } else {
                return new City(Integer.valueOf(cityToArray[0]),
                        cityToArray[1], cityToArray[2], cityToArray[3],
                        Integer.valueOf(cityToArray[4]), cityToArray[5]);
            }
        }).sorted(compareCity).collect(Collectors.toList());
        return cities;
    }

    public String findMaxPopulationInCity(File file) {
        int count = 0;
        int maxPopulationIndex = 0;
        int maxPopulation = 0;
        List<String> stringList = readFiles.getLinesFromFile(file);
        City[] cities = new City[stringList.size()];
        for (String city : stringList) {
            String[] cityToArray = city.split(";");
            if (Integer.valueOf(cityToArray[4]) > maxPopulation) {
                maxPopulation = Integer.valueOf(cityToArray[4]);
                maxPopulationIndex = count;
            }
            if (cityToArray.length < 6) {
                cities[count++] = new City(Integer.valueOf(cityToArray[0]),
                        cityToArray[1],
                        cityToArray[2],
                        cityToArray[3],
                        Integer.valueOf(cityToArray[4]),
                        "");
            } else {
                cities[count++] = new City(Integer.valueOf(cityToArray[0]),
                        cityToArray[1],
                        cityToArray[2],
                        cityToArray[3],
                        Integer.valueOf(cityToArray[4]),
                        cityToArray[5]);
            }
        }
        return String.format("[%d] = %d", maxPopulationIndex, maxPopulation);
    }
}
