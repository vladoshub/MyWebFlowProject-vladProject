package ru.mmtr.flow;

import ru.mmtr.entity.Key;
import ru.mmtr.entity.Word;
import ru.mmtr.web.dto.KeysDto;
import ru.mmtr.web.dto.WordsDto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("flow")
public class Out implements Serializable {
    private String outMess;
    private List<Key> keys;
    private List<Word> words;
    private KeysDto keysDto;
    private WordsDto wordsDto;
    private boolean addKeyValid;

    public KeysDto getKeysDtos() {
        return keysDto;
    }

    public WordsDto getWordsDtos() {
        return wordsDto;
    }

    public void setKeysDto(KeysDto keysDtos) {
        this.keysDto = keysDtos;
    }

    public void setWordsDto(WordsDto wordsDtos) {
        this.wordsDto = wordsDtos;
    }

    public boolean isAddKeyValid() {
        return addKeyValid;
    }

    public void setAddKeyValid(boolean addKeyValid) {
        this.addKeyValid = addKeyValid;
    }

    public Out() {
    }

    public Out(String outMess) {
        this.outMess = outMess;
    }

    public String getOutMess() {

        return outMess;
    }

    public void setOutMess(String outMess) {
        if (outMess == null)
            this.outMess = "server error";
        else
            this.outMess = outMess;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
