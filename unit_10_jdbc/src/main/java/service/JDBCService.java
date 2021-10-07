package service;

import entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCService {

    private static final Logger log = LoggerFactory.getLogger(JDBCService.class);
    private static final List<City> cities = new ArrayList<>();
    private static final TheShortestPath theShortestPath = new TheShortestPath();

    public void findTheShortestWayInDB(Connection connection) {

        SolutionService solutionService = new SolutionService(() -> connection);
        ProblemService problemService = new ProblemService(() -> connection);

        List<Solution> solutions = solutionService.findAll();
        List<Problem> problems = problemService.findAll();

        if (solutions != null) {
            for (Solution solution : solutions) {
                int id_problem = solution.getProblemId();
                problems.removeIf(p -> p.getId() == id_problem);
            }
            solutions.clear();
        }

        if (!problems.isEmpty()) {
            LocationService locationService = new LocationService(() -> connection);
            RouteService routeService = new RouteService(() -> connection);

            List<Location> locations = locationService.findAll();
            List<Route> routes = routeService.findAll();
            for (Location location : locations) {
                City city = new City();
                int indexCity = location.getId();
                city.setId(indexCity);
                city.setName(location.getName());
                Map<Integer, Integer> neighbourCosts = new HashMap<>();
                for (Route route : routes) {
                    if (route.getFromId() == indexCity) {
                        neighbourCosts.put(route.getToId(), route.getCost());
                    } else if (route.getToId() == indexCity) {
                        neighbourCosts.put(route.getFromId(), route.getCost());
                    }
                }
                city.setNeighbourDistance(neighbourCosts);
                cities.add(city);
            }

            for (Problem problem : problems) {
                Solution solution = new Solution();
                solution.setProblemId(problem.getId());
                int from = problem.getFromId();
                int to = problem.getToId();
                int cost = theShortestPath.find(cities, from, to);
                solution.setCost(cost);
                log.info("We find cost {} for problem {}", cost, solution.getProblemId());
                solutions.add(solution);
            }
            if (!solutions.isEmpty()) {
                solutionService.create(solutions);
            }
        } else {
            log.info("You don't have unsolved problems.");
        }
    }
}
