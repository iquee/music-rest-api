package com.luiztaira.sample.music.disc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import com.luiztaira.sample.music.exception.TrackException;
import com.luiztaira.sample.music.service.TrackService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.github.javafaker.Faker;
import com.luiztaira.sample.music.domain.Track;

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
