package algorithmes;

import algorithmes.interfaces.FurthestLiving;
import data.User;

/**
 * Implementation of algorithm, which finds the two users who live farthest from each other.
 * The time complexity of the algorithm is: O( n^2 )
 * Another algorithm (O(n logn) ) did not want to work correctly.
 */
public class UsersFurthestLiving implements FurthestLiving {
    @Override
    public FurthestUsers findFurthestLivingUsers(User[] users) {
        FurthestUsers furthestUsers = new FurthestUsers(users[0], users[0], 0.0f);

        for(User user1: users) {
            for(User user2: users) {
                if(user1 == user2) continue;
                double dist = Double.parseDouble(ProductCategories.df.format(distance(user1, user2)));

                if(dist > furthestUsers.distance())
                    furthestUsers = new FurthestUsers(user1, user2, dist);
            }
        }

        return furthestUsers;
    }

    private double distance(User user1, User user2) {
        return Math.sqrt(
                   Math.pow(user1.address().geolocation().latitude() - user2.address().geolocation().latitude(), 2) +
                   Math.pow(user1.address().geolocation().longitude() - user2.address().geolocation().longitude(), 2)
        );
    }
}
