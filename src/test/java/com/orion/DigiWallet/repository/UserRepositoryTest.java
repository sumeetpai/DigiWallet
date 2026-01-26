package com.orion.DigiWallet.repository;


import com.orion.DigiWallet.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
// FIRST SEE THE APPLICATION.PROPERTIES IN TEST RESOURCES FOLDER
    // ALSO LOOK AT THE DBSCIPT.SQL AND DATAINSERT.SQL FILES IN MAIN FOLDER
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void existsByUsername_shouldReturnTrue_whenUserExists() {

        // GIVEN
        User user = new User();
        user.setUsername("AlexTest");
        user.setFullName("Alex Johnson");
        user.setRole("USER");
        user.setStatus("ACTIVE");

        userRepository.save(user);

        // WHEN
        boolean exists = userRepository.existsByUsername("AlexTest");

        // THEN
        assertThat(exists).isTrue();
    }

    @Test
    void existsByUsername_shouldReturnFalse_whenUserDoesNotExist() {

        // WHEN
        boolean exists = userRepository.existsByUsername("unknown");

        // THEN
        assertThat(exists).isFalse();
    }
}

