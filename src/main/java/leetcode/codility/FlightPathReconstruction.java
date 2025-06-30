package leetcode.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
}

class Ticket {
    String origin;
    String destination;

    public Ticket(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}