package ru.sorokin.sbervirtualinternship;

import ru.sorokin.sbervirtualinternship.model.City;
import ru.sorokin.sbervirtualinternship.service.ReadFiles;

import java.io.File;
import java.util.List;

public class SberVirtualInternshipApplication {

    public static void main(String[] args) {
        ReadFiles readFiles = new ReadFiles();
        List<String> stringList = readFiles.getLinesFromFile(new File("Задача ВС Java Сбер.csv"));
        stringList.stream().map(s -> {
            String[] d = s.split(";");
            if (d.length < 6) {
                return new City(Integer.valueOf(d[0]), d[1], d[2], d[3], Integer.valueOf(d[4]), "");
            } else {
                return new City(Integer.valueOf(d[0]), d[1], d[2], d[3], Integer.valueOf(d[4]), d[5]);
            }
        }).forEach(System.out::println);
    }
}
