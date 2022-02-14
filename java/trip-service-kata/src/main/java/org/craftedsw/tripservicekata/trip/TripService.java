package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();

        if (loggedUser != null) {
            return isFriendOf(user, loggedUser) ? getTripsBy(user) : Collections.emptyList();

        } else {
            throw new UserNotLoggedInException();
        }
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

    protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
    }

}
