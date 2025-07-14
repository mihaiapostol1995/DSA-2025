package leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// FUCKING HARD ONE
class WaterAndJugProblem {
    public static void main(String[] args) {
        WaterAndJugProblem wp = new WaterAndJugProblem();
        boolean b = wp.canMeasureWater(3, 5, 4);
        System.out.println(b);
    }

    public boolean canMeasureWater(int x, int y, int target) {
        Queue<State> states = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        var initialState = new State(0, 0);
        visited.add(initialState);
        states.add(initialState);

        while (!states.isEmpty()) {
            var state = states.poll();

            if (state.jug1 + state.jug2 == target
                || state.jug1 == target
                || state.jug2 == target) {
                return true;
            }

            var possibleStates = getStates(state, x, y);
            for (var possibleState : possibleStates) {
                if (!visited.contains(possibleState)) {
                    visited.add(possibleState);
                    states.add(possibleState);
                }
            }
        }
        return false;
    }

    private List<State> getStates(State state, int x, int y) {
        List<State> states = new LinkedList<>();

        // Empty a jug
        states.add(new State(0, state.jug2));
        states.add(new State(state.jug1, 0));

        // Fill either jug completely with water
        states.add(new State(x, state.jug2));
        states.add(new State(state.jug1, y));

        // Pour the difference
        int jugSum = state.jug1 + state.jug2;

        int nextFirstJug = Math.min(jugSum, x);
        states.add(new State(nextFirstJug, Math.max(state.jug2 - (nextFirstJug - state.jug1), 0)));

        int nextSecondJug = Math.min(jugSum, y);
        states.add(new State(Math.max(state.jug1 - (nextSecondJug - state.jug2), 0), nextSecondJug));
        return states;
    }

    record State(int jug1, int jug2) {}
}
