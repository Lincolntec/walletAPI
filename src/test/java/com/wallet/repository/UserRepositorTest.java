package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositorTest {

	private static final String EMAIL = "email@teste.com.br";

	@Autowired
	UserRepository repository;

	@Before
	public void setUp() {
		User u = new User();
		u.setName("Lincoln");
		u.setPassword("Senha123");
		u.setEmail(EMAIL);

		repository.save(u);

	}

	@After
	public void tearDown() {

		repository.deleteAll();

	}

	@Test
	public void testSave() {
		User u = new User();
		u.setName("Teste");
		u.setPassword("123456");
		u.setEmail("teste@teste.com");

		User response = repository.save(u);

		assertNotNull(response);
	}

	@Test
	public void testeFindByEmail() {

		Optional<User> response = repository.findByEmailEquals(EMAIL);

		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);

	}

}
