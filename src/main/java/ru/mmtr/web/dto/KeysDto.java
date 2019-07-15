package ru.mmtr.web.dto;

import ru.mmtr.entity.Word;

import java.io.Serializable;
import java.util.List;

public class KeysDto implements Serializable {
    private Long id;
    private String key;
    private List<Word> words;
    private boolean validate;

    public KeysDto(Long id) {
        this.id= id;
        this.validate=false;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isValidate() {
        return validate;
    }

    public long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

}
