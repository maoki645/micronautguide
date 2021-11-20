package com.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "/users")
class UserController {
    private static final List<User> users = new ArrayList<User>();

    static{
        users.add(new User("Teste", "123456789"));
        users.add(new User("Teste2", "000000000"));
    }

    @Get(value = "/")
    public List<User> getById() {
        return users;
    }

}