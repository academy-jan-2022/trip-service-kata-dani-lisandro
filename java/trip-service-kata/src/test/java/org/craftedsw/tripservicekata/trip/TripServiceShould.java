package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class TripServiceShould {
    public User loggedUser = null;
    @Test()
    void return_error_if_user_is_not_logged() {
        TripService tripService = new TestableTripService();

        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(new User()));
    }

    @Test() void
    return_empty_trips_list_when_not_friends(){
        TripService tripService = new TestableTripService();
        User notFriend = new User();
        notFriend.addFriend(new User());
        notFriend.addTrip(new Trip());

        loggedUser = new User();

        List<Trip> trips = tripService.getTripsByUser(notFriend);

        assertThat(trips.size(), is(0));
    }

     private class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }
    }
}
