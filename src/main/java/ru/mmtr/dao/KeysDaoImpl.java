package ru.mmtr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mmtr.entity.Key;
import ru.mmtr.entity.Type;
import ru.mmtr.entity.Word;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeysDaoImpl implements KeysDao {
    @Autowired
    public KeysDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(KeysDaoImpl.class);

    @Override
    public String addKey(String key, Integer type, List<String> words) {

            if (!findByKey(key,type).isEmpty())
            return "такой ключ уже есть";
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Key keyN = new Key(type);
            keyN.setKey(key);
            for (String wordStr : words) {
                Word word = new Word();
                word.setWord(wordStr);
                keyN.setWords(word);
                word.setKey(keyN);
            }
            session.saveOrUpdate(key);
            tx1.commit();
            session.close();
            return "Ok";

    }

    @Override
    public String addKey(String key, Integer type, String wordStr) {

            if (!findByKey(key,type).isEmpty())
            return "такой ключ уже есть";
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Key keyN = new Key(type);
            keyN.setKey(key);
            Word word = new Word();
            word.setWord(wordStr);
            keyN.setWords(word);
            word.setKey(keyN);
            session.saveOrUpdate(key);
            tx1.commit();
            session.close();
            return "Ok";
    }

    @Override
    public String addWord(Long keyId, Integer type, List<String> words) {
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            for (String s : words) {
                Word word = new Word(keyId);
                word.setWord(s);
                session.saveOrUpdate(word);
            }
            tx1.commit();
            session.close();
            return "Ok";

    }

    @Override
    public String addWord(Long keyId, Integer type, String words) {
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Word word = new Word(keyId);
            word.setWord(words);
            session.saveOrUpdate(word);
            tx1.commit();
            session.close();
            return "Ok";

    }

    @Override
    public List<Key> findByKey(String key, Integer type) {

            Session session = this.sessionFactory.openSession();
            List<Key> ux = session.createQuery("From Key where key='" + key + "' and type_id='" + type + "'").list();
            session.close();
            return ux;
    }

    @Override
    public List<Key> findByWord(String word, Integer type) {
            List<Key> ux2 = new ArrayList<>();
            Session session = this.sessionFactory.openSession();
            List<Word> ux = session.createQuery("From Word where word='" + word + "'").list();
            for (Word w : ux) {
                Key key = new Key();
                key.setType(w.getKey().getType());
                key.setWords(w.getKey().getWords());
                key.setKey(w.getKey().getKey());
                key.setId(w.getKey().getId());
                ux2.add(key);
            }
            session.close();
            return ux2;
    }




    @Override
    public String deleteKeyById(Long id) {
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Key key = (Key) session.load(Key.class, id);
            session.delete(key);
            tx1.commit();
            session.close();
            return "Ok";

    }

    @Override
    public String deleteWordById(Long id) {
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Word word = (Word) session.load(Word.class, id);
            session.delete(word);
            tx1.commit();
            session.close();
            return "Ok";
    }

    @Override
    public String updateKey(Long id, String newKeys, Integer type) {
            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            List<Key> ux = session.createQuery("From Key where id=" + id + " and type_id=" + type + "").list();
            ux.get(0).setKey(newKeys);
            session.update(ux.get(0));
            tx1.commit();
            session.close();
            return "Ok";

    }

    @Override
    public String updateWord(Long id, String newWords, Integer type) {

            Session session = this.sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            Word word = (Word) session.get(Word.class, id);
            word.setWord(newWords);
            session.update(word);
            tx1.commit();
            session.close();
            return "Ok";
    }

    @Override
    public List<Key> getKeysList(Integer type) {
            Session session = this.sessionFactory.openSession();
            String hql = "FROM Key WHERE type_id=" + type + " ORDER BY key ASC";
            List<Key> VocabularyList = session.createQuery(hql).list();
            session.close();
            return VocabularyList;
    }

    @Override
    public Type findByIdType(String type) {
            Session session = this.sessionFactory.openSession();
            String hql = "FROM Type WHERE type='" + type + "' ORDER BY type ASC";
            Type typel = (Type) session.createQuery(hql).uniqueResult();
            session.close();
            return typel;
    }

    @Override
    public List<Type> getTypes() {
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Type ORDER BY type ASC";
        List<Type> typel = (List<Type>) session.createQuery(hql).list();
        session.close();
        return typel;
    }
}
