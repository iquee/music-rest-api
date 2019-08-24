package com.luiztaira.music.disc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import com.luiztaira.music.track.Track;
import com.luiztaira.music.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

@SpringBootTest
public class MusicUnitTests {
	@Autowired
	TrackService discService;
	static Faker FAKER = new Faker();

	private Track buildDisc() {
		String name = FAKER.music().instrument();
		BigDecimal price = new BigDecimal(FAKER.commerce().price(0, 50));
		String genre = FAKER.music().genre();
		return new Track();
	}

}
