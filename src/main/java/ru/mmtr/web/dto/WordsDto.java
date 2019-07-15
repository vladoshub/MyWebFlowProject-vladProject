package ru.mmtr.web.dto;

import ru.mmtr.entity.Key;

import java.io.Serializable;

public class WordsDto implements Serializable {
    private Long id;
    private String word;
    private Key key;
    private boolean validate;

    public WordsDto(Long id) {
        this.id= id;
        this.validate=false;
    }

    public void setWords(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isValidate() {
        return validate;
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
