package ru.mmtr.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "type")
public class Type implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String regwords;
    private String regkeys;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Key> keys;

    public Type() {
        super();
    }

    public Type(String type, List<Key> keys) {
        this.type = type;
        this.keys = keys;
    }

    public Type(String type, Key keys) {
        this.type = type;
        this.keys.add(keys);
    }

    public Type(Integer typeId) {
        this.id = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String key_id) {
        this.type = key_id;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {

        this.keys = keys;
    }

    public void setKeys(Key key) {

        this.keys.add(key);
    }

    public String getRegKeys() {
        return regkeys;
    }

    public String getRegWords() {
        return regwords;
    }

    public void setRegKeys(String regKey) {
        regkeys = regKey;
    }

    public void setRegWords(String regWord) {
        regwords = regWord;
    }

    @Override
    public String toString() {
        return "KEYS{" +
                "id=" + id +
                ", Key='" + type + '\'' +
                ", Word=" + keys +
                '}';
    }
}