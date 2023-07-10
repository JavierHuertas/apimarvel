package com.marvel.apiheroes.dto;

public class ComicCharactersCount {
	private Long comicId;
	private String comicName;
	private int characterCount;

	public ComicCharactersCount(final Long comicId, final String comicName, final int characterCount) {
		this.comicId = comicId;
		this.comicName = comicName;
		this.characterCount = characterCount;
	}

	public Long getComicId() {
		return comicId;
	}

	public void setComicId(final Long comicId) {
		this.comicId = comicId;
	}

	public String getComicName() {
		return comicName;
	}

	public void setComicName(final String comicName) {
		this.comicName = comicName;
	}

	public int getCharacterCount() {
		return characterCount;
	}

	public void setCharacterCount(final int characterCount) {
		this.characterCount = characterCount;
	}
}
