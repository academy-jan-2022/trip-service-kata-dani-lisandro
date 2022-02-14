package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

import java.util.stream.Stream;


class UserBuilder {
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
        Stream<User> newFriends = Stream.concat(Stream.of(this.friends), Stream.of(user));
        return new UserBuilder(this.trips, newFriends.toArray(User[]::new));
    }

    public UserBuilder withTrips(Trip... trips) {
        Stream<Trip> newTrips = Stream.concat(Stream.of(this.trips), Stream.of(trips));
        return new UserBuilder(newTrips.toArray(Trip[]::new), this.friends);
    }

    public User build() {
        User user = new User();

        for (Trip trip : trips) {
            user.addTrip(trip);
        }
        for (User friend : friends) {
            user.addFriend(friend);
        }

        return user;
    }
}
