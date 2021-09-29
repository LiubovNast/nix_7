package service;

import entity.City;
import entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheShortestPath {

    private static List<City> cities = new ArrayList<>();

    public int find(List<City> cityList, int indexStart, int indexFinish) {
        cities = cityList;
        int finalResult = Integer.MAX_VALUE;
        if (indexStart == indexFinish) {
            return 0;
        }
//        String[] temp = betweenCities.trim().split(" ");
//        String startName = temp[0];
//        String finishName = temp[1];
//        int indexFinish = 0;
//        int indexStart;
//        City cityStart = new City();
//        for (City city : cities) {
//            if (city.getName().equals(finishName)) {
//                indexFinish = city.getId();
//            }
//            if (city.getName().equals(startName)) {
//                cityStart = city;
//            }
//        }
//        indexStart = cityStart.getId();
        List<Result> results = new ArrayList<>();
        results.addAll(getResultFromList(cities, indexStart, indexFinish));
        int i = 0;
        while (true) {
            if (i >= results.size()) break;
            if (!results.get(i).isFinish()) {
                List<Result> previous = getResultFromList(cities, indexFinish, results.get(i));
                if (previous != null && !previous.isEmpty()) results.addAll(previous);
            }
            i++;
        }
        for (Result result : results) {
            if (result.isFinish()) {
                if (finalResult > result.getDistance()) {
                    finalResult = result.getDistance();
                }
            }
        }
        return finalResult;
    }

    private static List<Result> getResultFromList(List<City> cities, int indexFinish, final Result previousResult) {
        List<Result> resultList = new ArrayList<>();
        List<Integer> previousCities = new ArrayList<>();
        previousCities.addAll(previousResult.getFromCity());
        int indexStart = previousCities.get(previousCities.size() - 1);
        City cityStart = null;
        for (City city : cities) {
            if (city.getId() == indexStart) {
                cityStart = city;
                break;
            }
        }

        Map<Integer, Integer> neighbours = cityStart.getNeighbourDistance();
        for (Map.Entry<Integer, Integer> index : neighbours.entrySet()) {
            List<Integer> idCities = new ArrayList<>();
            idCities.addAll(previousCities);
            if (idCities.stream().anyMatch(p -> p == index.getKey())) continue;
            Result result = new Result();
            int indexCity = index.getKey();
            int distance = index.getValue();
            int previousDistance = previousResult.getDistance();
            result = getResult(indexCity, distance, idCities, previousDistance, indexFinish);
            resultList.add(result);
        }
        return resultList;
    }

    private static Result getResult(int indexCity, int distance, final List<Integer> idCities, int previousDistance, int indexFinish) {
        Result result = new Result();
        List<Integer> previousCities = new ArrayList<>();
        previousCities.addAll(idCities);
        idCities.add(indexCity);
        result.setFromCity(idCities);
        result.setDistance(previousDistance + distance);
        if (indexCity == indexFinish) {
            result.setFinish(true);
        }
        return result;
    }

    private static List<Result> getResultFromList(List<City> cities, int indexStart, int indexFinish) {
        List<Result> resultList = new ArrayList<>();
        City cityStart = null;
        for (City city : cities) {
            if (city.getId() == indexStart) {
                cityStart = city;
                break;
            }
        }

        Map<Integer, Integer> neighbours = cityStart.getNeighbourDistance();
        for (Map.Entry<Integer, Integer> index : neighbours.entrySet()) {
            Result result = new Result();
            List<Integer> previousCities = new ArrayList<>();
            previousCities.add(1);
            previousCities.add(index.getKey());
            result.setFromCity(previousCities);
            result.setDistance(result.getDistance() + index.getValue());
            if (index.getKey() == indexFinish) {
                result.setFinish(true);
            }
            resultList.add(result);
        }
        return resultList;
    }
}
