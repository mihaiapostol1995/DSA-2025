package leetcode.premium;

public class FindCelebrity extends Relation {
    public int findCelebrity(int n) {
        // Step 1: Find potential celebrity candidate
        int candidate = 0;
        
        // If candidate knows someone, that someone could be celebrity
        // If candidate doesn't know someone, that someone cannot be celebrity
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        
        // Step 2: Verify the candidate
        // Check if candidate is actually a celebrity
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            
            // Celebrity should not know anyone AND everyone should know celebrity
            if (knows(candidate, i) || !knows(i, candidate)) {
                return -1; // No celebrity found
            }
        }
        
        return candidate;
    }

    @Override
    public boolean knows(int i, int i2) {
        return false;
    }
}

class Relation {
    boolean knows(int i, int i2) {
        return false;
    };
}