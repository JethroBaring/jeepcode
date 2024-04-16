package com.jethro.jeepcode.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jethro.jeepcode.models.Route;
import com.jethro.jeepcode.utils.JeepCodeValidator;

@Service
public class RouteService {
    private final Map<String, Route> jeepRoutes;

    public RouteService() {
        this.jeepRoutes = initializeRoutes();
    }

    public Route getJeepRoute(String jeepCode) {
        if (JeepCodeValidator.isValidJeepCode(jeepCode)) {
            if (jeepRoutes.containsKey(jeepCode)) {
                return jeepRoutes.get(jeepCode);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided Jeep code '" + jeepCode
                    + "' does not match any known jeep codes. Please provide a valid Jeep code.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Jeep code format: '" + jeepCode
                + "'. Please provide a valid Jeep code in the format of 2 digits followed by a letter.");

    }

    public Map<String, Route> getJeepsRoutes(String jeepCodes) {
        List<String> codes = List.of(jeepCodes.split(","));
        List<String> invalidCodes = new ArrayList<>();
        Map<String, Route> routes = new HashMap<>();

        for (String code : codes) {
            if (JeepCodeValidator.isValidJeepCode(code) && jeepRoutes.containsKey(code)) {
                routes.put(code, jeepRoutes.get(code));
            } else {
                invalidCodes.add(code);
            }
        }

        if (!invalidCodes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or Unknown Jeep codes: " + invalidCodes);
        }

        return routes;
    }

    public Map<String, Route> initializeRoutes() {
        Map<String, Route> jeepRoutes = new HashMap<>();
        jeepRoutes.put("01A", new Route(List.of("Alpha", "Bravo", "Charlie", "Echo", "Golf")));
        jeepRoutes.put("02B", new Route(List.of("Alpha", "Delta", "Echo", "Foxtrot", "Golf")));
        jeepRoutes.put("03C", new Route(List.of("Charlie", "Delta", "Foxtrot", "Hotel", "India")));
        jeepRoutes.put("04A", new Route(List.of("Charlie", "Delta", "Echo", "Foxtrot", "Golf")));
        jeepRoutes.put("04D", new Route(List.of("Charlie", "Echo", "Foxtrot", "Golf", "India")));
        jeepRoutes.put("06B", new Route(List.of("Delta", "Hotel", "Juliet", "Kilo", "Lima")));
        jeepRoutes.put("06D", new Route(List.of("Delta", "Foxtrot", "Golf", "India", "Kilo")));
        jeepRoutes.put("10C", new Route(List.of("Foxtrot", "Golf", "Hotel", "India", "Juliet")));
        jeepRoutes.put("10H", new Route(List.of("Foxtrot", "Hotel", "Juliet", "Lima", "November")));
        jeepRoutes.put("11A", new Route(List.of("Foxtrot", "Golf", "Kilo", "Mike", "November")));
        jeepRoutes.put("11B", new Route(List.of("Foxtrot", "Golf", "Lima", "Oscar", "Papa")));
        jeepRoutes.put("20A", new Route(List.of("India", "Juliet", "November", "Papa", "Romeo")));
        jeepRoutes.put("20C", new Route(List.of("India", "Kilo", "Lima", "Mike", "Romeo")));
        jeepRoutes.put("42C", new Route(List.of("Juliet", "Kilo", "Lima", "Mike", "Oscar")));
        jeepRoutes.put("42D", new Route(List.of("Juliet", "November", "Oscar", "Quebec", "Romeo")));

        return jeepRoutes;
    }
}
