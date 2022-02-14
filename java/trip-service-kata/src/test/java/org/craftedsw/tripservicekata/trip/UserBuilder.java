package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

class UserBuilder {
    private final User currentUser;

    public UserBuilder() {
        currentUser = new User();
    }

    private UserBuilder(User user) {
        this.currentUser = user;
    }

    public UserBuilder withFriends(User user) {
        currentUser.addFriend(user);
        return new UserBuilder(currentUser);
    }

    public UserBuilder withTrips(Trip trip) {
        currentUser.addTrip(trip);
        return new UserBuilder(currentUser);
    }

    public User build() {
        return currentUser;
    }
}
