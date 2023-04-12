package algorithmes.interfaces;

import data.User;

/**
 * Interface of algorithm, which finds the two users who live farthest from each other.
 */
public interface FurthestLiving {
    /**
     * Finds the two users who live farthest from each other among the given array of users.
     * The time complexity of the algorithm is: O( n^2 )
     * Another algorithm (O(n logn) ) did not want to work correctly.
     *
     * @param users an array of {@link User} objects representing the users to search for
     * @return a FurthestUsers object containing the two {@link User} objects who live farthest from each other
     */
    FurthestUsers findFurthestLivingUsers(User[] users);

    /**
     * A simple record to store the two {@link User} objects who live farthest from each other, and distance
     */
    record FurthestUsers(User user1, User user2, double distance) {}

}
