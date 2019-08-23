package com.luiztaira.sample.music.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.json.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Collection Discs: Based on Spotify content, store disc by name, genre and price
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "tracks")
public class Track implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @EqualsAndHashCode.Include private String id;
	@NotNull private String name;
	@NotNull private List<Document> artists;
	@NotNull private Document album;
	@NotNull private Double popularity;
	@NotNull private Double duration;
	@NotNull private String spotifyId;
	@NotNull private String spotifyLink;

}