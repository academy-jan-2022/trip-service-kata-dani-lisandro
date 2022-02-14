package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class TripService {


    private final TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        return getLoggedUser()
                .map(loggedUser -> getTripsWhenFriends(user, loggedUser))
                .orElseThrow(UserNotLoggedInException::new);
    }

    private List<Trip> getTripsWhenFriends(User user, User loggedUser) {
        return user.isFriendsWith(loggedUser) ? tripDAO.findTripsByUser(user) : emptyList();
    }

    protected Optional<User> getLoggedUser() {
        return Optional.ofNullable(UserSession.getInstance().getLoggedUser());
    }

}
