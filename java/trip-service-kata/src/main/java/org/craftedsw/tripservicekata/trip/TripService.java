package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        return getLoggedUser()
                .map(
                    loggedUser -> isFriendOf(user, loggedUser) ? getTripsBy(user) : Collections.<Trip> emptyList()
                )
                .orElseThrow(UserNotLoggedInException::new);
    }

    private boolean isFriendOf(User user, User loggedUser) {
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return true;

            }
        }
        return false;
    }

    protected List<Trip> getTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected Optional<User> getLoggedUser() {
		return Optional.ofNullable(UserSession.getInstance().getLoggedUser());
    }

}
