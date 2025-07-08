package leetcode.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class FlightPathReconstruction {
    public static void main(String[] args) {
        List<Ticket> tickets = Arrays.asList(
                new Ticket("Paris", "Berlin"),
                new Ticket("Bucharest", "Milano"),
                new Ticket("Berlin", "Roma"),
                new Ticket("Milano", "Paris")
        );

        FlightPathReconstruction fp = new FlightPathReconstruction();

        for (var ticket : tickets) {
            ArrayList<String> result1 = new ArrayList<>();
            result1.add(ticket.origin);

            var newList = new ArrayList<>(tickets);
            newList.remove(ticket);
            fp.findFlightPath(ticket, newList, result1, tickets.size());
        }

        System.out.println(String.join(" → ", fp.result));
    }

    private List<String> result;

    private void findFlightPath(Ticket ticket, List<Ticket> tickets, List<String> result, int size) {
        if (tickets.isEmpty()) {
            result.add(ticket.destination);
            this.result = new ArrayList<>(result);
            return;
        }

        var destination = ticket.destination;
        Optional<Ticket> any = tickets.stream()
                .filter(t -> t.origin.equals(destination))
                .findAny();
        if (any.isPresent()) {
            result.add(ticket.destination);

            var newList = new ArrayList<>(tickets);
            newList.remove(any.get());
            findFlightPath(any.get(), newList, result, size);
        }
    }


    public static String findPath(List<Map<String, String>> tickets) {
        Map<String, String> flightMap = new HashMap<>();
        Set<String> destinations = new HashSet<>();

        // Build map and destination set
        for (Map<String, String> ticket : tickets) {
            String origin = ticket.get("origin");
            String destination = ticket.get("destination");
            flightMap.put(origin, destination);
            destinations.add(destination);
        }

        // Find starting point (origin not in destinations)
        String start = null;
        for (String origin : flightMap.keySet()) {
            if (!destinations.contains(origin)) {
                start = origin;
                break;
            }
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        while (start != null) {
            path.add(start);
            start = flightMap.get(start);
        }

        return String.join(" → ", path);
    }
}

class Ticket {
    String origin;
    String destination;

    public Ticket(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}