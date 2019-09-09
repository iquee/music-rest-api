package com.github.iquee.music.disc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.javafaker.Faker;

@SpringBootTest
public class MusicRestTests {

	int count = 20;
	private static Faker FAKER;
	private static MockMvc MOCK;
	private static String API_PREFIX = "/api/disc/";
	private WebApplicationContext ctx;

	@Autowired
	public MusicRestTests(WebApplicationContext ctx) {
		this.ctx = ctx;
		FAKER = new Faker();
		MOCK = MockMvcBuilders.webAppContextSetup(this.ctx).build();
	}

	public void doCreateTrack() throws Exception {
		JSONObject jsonObject = buildDiscBodyRequest();
		MOCK.perform(post(API_PREFIX).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private JSONObject buildDiscBodyRequest() {
		JSONObject json = new JSONObject();
		json.put("name", FAKER.music().instrument());
		json.put("price", new BigDecimal(FAKER.commerce().price(0, 50)));
		json.put("genre", FAKER.music().genre());
		return json;
	}
}
