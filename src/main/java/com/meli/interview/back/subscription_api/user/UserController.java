package com.meli.interview.back.subscription_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.interview.back.subscription_api.exception.ObjectNotFoundException;
import com.meli.interview.back.subscription_api.session.User;
import com.meli.interview.back.subscription_api.session.UserRepository;
import com.meli.interview.back.subscription_api.subscription.SubscriptionService;

@RestController("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionService subService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        if (user.getId() != null) throw new IllegalArgumentException("No se puede crear un usuario existente");
        user = userRepository.save(user);
        return user;
    }

    @PutMapping("/")
    public User modifyUser(@RequestBody User user) {
        if (user.getId() == null || userRepository.findById(user.getId()) == null) 
            throw new ObjectNotFoundException("El usuario no existe");
        user = userRepository.save(user);
        return user;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("El usuario no existe"));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @GetMapping("/{id}/totalprice") 
    public float getTotalPrice(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("El usuario no existe"));
        return subService.getUserSubscriptionsCost(user);
    }

    @PutMapping("/friends/{id}")
    public String addFriend(@PathVariable long id) {

    }

    @DeleteMapping("/friends/{id}")
    public String removeFriend(@PathVariable long id) {

    }

    @PutMapping("/subscriptions/{sub}")
    public String addSubscription(@PathVariable String sub) {

    }

    @DeleteMapping("/subscriptions/{sub}")
    public String removeSubscription(@PathVariable String sub) {

    }

}
