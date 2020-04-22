package com.pohjelmointi.tasks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pohjelmointi.tasks.domain.Kayttaja;
import com.pohjelmointi.tasks.domain.KayttajaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class KayttajaRepositoryTests {

    @Autowired
    private KayttajaRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
        Kayttaja user = repository.findByUsername("user");
        assertThat(user.getRole()).isEqualTo("USER");
    }

    @Test
    public void createNewUser() {
    	Kayttaja user = new Kayttaja("user2", "password2", "USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }    

} 