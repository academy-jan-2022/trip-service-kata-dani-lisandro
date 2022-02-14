package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripServiceShould {

    @Test()
    void return_error_if_user_is_not_logged() {
        TripService tripService = new TestableTripService();
        User user = new User();

        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(user));
    }

    private static class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return null;
        }
    }
}
