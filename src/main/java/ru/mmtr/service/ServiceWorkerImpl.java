package ru.mmtr.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mmtr.dao.KeysDao;
import ru.mmtr.dao.KeysDaoImpl;
import ru.mmtr.entity.Key;
import ru.mmtr.entity.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component("ServiceWorker")
public class ServiceWorkerImpl implements ServiceWorker {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ServiceWorkerImpl.class);

    @Autowired
    public ServiceWorkerImpl(SessionFactory ses) {
        keysDao = new KeysDaoImpl(ses);
    }

    private KeysDao keysDao;

    @Override
    public String deleteKeyById(Long id) throws IOException {
        try {
            return keysDao.deleteKeyById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String deleteWordById(Long id) throws IOException {
        try {
            return keysDao.deleteWordById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    @Override
    public List<Key> searchByKey(String key, Integer type) throws IOException {
        try {
            return keysDao.findByKey(key,type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Key> searchByWord(String word, Integer type) throws IOException {
        try {
            return keysDao.findByWord(word, type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String updateKey(Long id, String newKey, Integer type) throws IOException {
        try {
            if (!checkKey(newKey, type))
                return "ключ не относится к данному словарю";
            return keysDao.updateKey(id, newKey, type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String updateWord(Long id, String newWord, Integer type) throws IOException {

        try {
            if (checkWord(newWord, type))
                return keysDao.updateWord(id, newWord, type);
            return "несоответсвие правилам словаря ";

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String addKey(Integer type, String key, List<String> words) throws IOException {
        try {
            if (!checkKey(key, type))
                return "ключ не относится к данному словарю";
            List<String> newWords = new ArrayList<String>();
            for (String s : words) {
                if (checkWord(s, type))
                    newWords.add(s);
            }
            if (newWords.size() > 0)
                return keysDao.addKey(key, type, newWords);
            return "ни 1 слово не подходит по правилам словаря";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String addKey(Integer type, String key, String word) throws IOException {
        try {
            if (!checkKey(key, type))
                return "ключ не относится к данному словарю";
            if (checkWord(word, type))
                return keysDao.addKey(key, type, word);
            return "слово не подходит по правилам словаря";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String addToKey(Integer type, Long idKey, List<String> words) throws IOException {
        try {
            List<String> newWords = new ArrayList<String>();
            for (String s : words) {
                if (checkWord(s, type))
                    newWords.add(s);
            }
            if (newWords.size() > 0)
                return keysDao.addWord(idKey, type, words);
            return "ни 1 слово не подходит по правилам словаря";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }
    @Override
    public Type findByIdType(String type) throws IOException {
        try {
            return keysDao.findByIdType(type);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    @Override
    public String addToKey(Integer type, Long keyId, String word) throws IOException {
        try {
            if (checkWord(word, type))
                return keysDao.addWord(keyId, type, word);
            return "слово не подходит по правилам словаря";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<Key> findKeys(Integer type) {
        try {
            return keysDao.getKeysList(type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean checkWord(String word, Integer num) {
        return ServiceChecker.checkWord(word);
    }

    @Override
    public boolean checkKey(String word, Integer num) {
        return ServiceChecker.checkKey(word);
    }

    @Override
    public List<Type> getTypes() throws IOException {
        return keysDao.getTypes();
    }
}