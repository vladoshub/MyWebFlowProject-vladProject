package ru.mmtr.service;

import ru.mmtr.entity.Key;
import ru.mmtr.entity.Type;

import java.io.IOException;
import java.util.List;

public interface ServiceWorker {

    public String deleteKeyById(Long id) throws IOException;

    public String deleteWordById(Long id) throws IOException;

    public List<Key> searchByKey(String key, Integer type) throws IOException;

    public List<Key> searchByWord(String word, Integer type) throws IOException;

    public String updateKey(Long id, String newKey, Integer type) throws IOException;

    public String updateWord(Long id, String newWord, Integer type) throws IOException;

    public String addKey(Integer type, String key, List<String> words) throws IOException;

    public String addKey(Integer type, String key, String word) throws IOException;

    public String addToKey(Integer type, Long id, List<String> words) throws IOException;

    public String addToKey(Integer type, Long id, String word) throws IOException;

    public List<Key> findKeys(Integer type);

    public boolean checkWord(String word, Integer num);

    public boolean checkKey(String word, Integer num);

    public Type findByIdType(String type) throws IOException;
}
