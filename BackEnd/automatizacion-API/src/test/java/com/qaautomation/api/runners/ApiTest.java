package com.qaautomation.api.runners;

import com.intuit.karate.junit5.Karate;

class ApiTest {
    @Karate.Test
    Karate usersApi() {
        return Karate.run("classpath:features/users").relativeTo(getClass());
    }
}
