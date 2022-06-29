package com.meli.interview.back.subscription_api.subscription;

import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.session.User;
import com.meli.interview.back.subscription_api.session.UserSession;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    /**
     * Devuelve el costo total de las suscripciones de un usuario siempre que el usuario que esté logueado
     * se encuentre en su lista de amigos
     * @param user
     * @return costo total de la suscripciones del user
     * @throws UserNotLoggedInException si no hay un usuario logueado
     */
    public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException {
        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();

        // get logged user
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            float totalPrice = 0;
            isFriend = (user.getFriends() != null && user.getFriends().contains(loggedUser));
            if (isFriend) {
                subscriptionList = SubscriptionDAO.findSubscriptionByUser(user);
                for (Subscription subscription : subscriptionList) {
                    totalPrice += subscription.getPrice();
                }
            }
            return totalPrice;
        } else {
            throw new UserNotLoggedInException();
        }
    }
}
