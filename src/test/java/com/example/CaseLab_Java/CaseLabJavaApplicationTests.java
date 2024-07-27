package com.example.CaseLab_Java;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaseLabJavaApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private FileRepo repository;

	@AfterEach
	public void resetDb() {
		repository.deleteAll();
	}

	@Test
	public void whenCreateFile_thenStatus201() {
		File file = new File("SGVsbG8gV29ybGQ=", "FirstFile", "this is file");
		ResponseEntity<String> response = restTemplate.postForEntity("/files", file, String.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void givenFile_whenGetFile_thenStatus200() {
		long id = createTestFile(new FileEntity("SGVsbG8gV29ybGQ=", "FirstFile", LocalDateTime.now(), "this is file")).getId();

		FileEntity file = restTemplate.getForObject("/files/{id}", FileEntity.class, id);
		assertThat(file.getTitle(), is("FirstFile"));
	}

	@Test
	public void givenFiles_whenGetFiles_thenStatus200() {
		createTestFile(new FileEntity("SGVsbG8gV29ybGQ=", "FirstFile", LocalDateTime.now(), "this is file"));
		createTestFile(new FileEntity("SGVsbG8gV29ybGQ=", "SecondFile", LocalDateTime.now(), "this is file too"));
		ResponseEntity<List<FileEntity>> response = restTemplate.exchange("/files", HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});
		List<FileEntity> files = response.getBody();
		assertThat(files, hasSize(2));
		assertThat(files.get(1).getTitle(), is("SecondFile"));
	}

	private FileEntity createTestFile(FileEntity file) {
		return repository.save(file);
	}

}
