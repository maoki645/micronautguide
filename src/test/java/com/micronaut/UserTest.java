package com.micronaut;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class UserTest {
    @Inject
    @Client("/users")
    HttpClient client;

    @Test
    public void verifyGithubReleasesCanBeFetchedWithLowLevelHttpClient() {
        //when:
        HttpRequest<Object> request = HttpRequest.GET("/");

        HttpResponse<List<User>> rsp = client.toBlocking().exchange(request,
                Argument.listOf(User.class));

        //then: 'the endpoint can be accessed'
        assertEquals(HttpStatus.OK, rsp.getStatus());
        assertNotNull(rsp.body());

        //when:
        List<User> releases = rsp.body();

        //then:
        assertNotNull(releases);
        assertTrue(releases.stream()
                .anyMatch(o -> "Teste".equals(o.getNome())));
    }

}
