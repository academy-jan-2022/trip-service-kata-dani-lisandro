package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceShould {
    public static final Trip TO_PORTUGAL = new Trip();
    public User loggedUser = null;
    private TripService tripService;
    private TripDAO mockedTripDao;
    @BeforeEach
    void setUp() {
        mockedTripDao = mock(TripDAO.class);
        tripService = new TestableTripService(mockedTripDao);

    }

    @Test()
    void return_error_if_user_is_not_logged() {
        User user = new User();
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(user));
    }

    @Test()
    void
    return_empty_trips_list_when_not_friends() {
        User differentUser = new User();
        User notFriend = new UserBuilder()
                .withFriends(differentUser)
                .withTrips(TO_PORTUGAL)
                .build();

        loggedUser = new User();

        List<Trip> trips = tripService.getTripsByUser(notFriend);

        assertThat(trips.size(), is(0));
    }

    @Test()
    void
    return_trips_when_users_are_friends() {

        loggedUser = new User();
        User friend = new UserBuilder()
                .withFriends(loggedUser)
                .withTrips(TO_PORTUGAL)
                .build();

        when(mockedTripDao.findTripsByUser(friend)).thenReturn(friend.trips());
        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(1));
        // dudo assertThat(trips, contains(portugal));
    }

    private class TestableTripService extends TripService {


        public TestableTripService(TripDAO tripDAO) {
            super(tripDAO);
        }

        @Override
        protected Optional<User> getLoggedUser() {
            return Optional.ofNullable(loggedUser);
        }
    }

}
