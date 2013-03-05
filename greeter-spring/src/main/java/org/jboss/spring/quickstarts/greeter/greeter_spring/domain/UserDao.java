package org.jboss.spring.quickstarts.greeter.greeter_spring.domain;

public interface UserDao {
    User getForUsername(String username);

    void createUser(User user);
}