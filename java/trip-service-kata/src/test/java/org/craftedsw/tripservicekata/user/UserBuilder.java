package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.stream.Stream;

import static java.util.stream.Stream.*;


public class UserBuilder {
    private final Trip[] trips;
    private final User[] friends;



    public UserBuilder() {
        trips = new Trip[]{};
        friends = new User[]{};
    }

    private UserBuilder(Trip[] trips, User[] friends) {
        this.trips = trips;
        this.friends = friends;
    }

    public UserBuilder withFriends(User... user) {
        Stream<User> newFriends = concat(of(this.friends), of(user));
        return new UserBuilder(this.trips, newFriends.toArray(User[]::new));
    }

    public UserBuilder withTrips(Trip... trips) {
        Stream<Trip> newTrips = concat(of(this.trips), of(trips));
        return new UserBuilder(newTrips.toArray(Trip[]::new), this.friends);
    }

    public User build() {
        User user = new User();
        of(trips).forEach(user::addTrip);
        of(friends).forEach(user::addFriend);

        return user;
    }
}
