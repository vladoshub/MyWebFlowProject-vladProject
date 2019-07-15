package ru.mmtr.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "key")
public class Key implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key")
    private String key;
    @OneToMany(mappedBy = "key", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Word> words;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Key() {
        super();
    }

    public Key(Long id){
        this.id=id;
    }

    public Key(Integer typeId) {
        this.type = new Type(typeId);
    }


    public Key(String key, List<Word> words, Type type) {
        this.key = key;
        this.words = words;
        this.type = type;
    }

    public Key(String key, Type type) {
        this.key = key;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key_id) {
        this.key = key_id;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void setWords(Word word) {

        if (this.words != null)
            this.words.add(word);
        else {
            this.words = new ArrayList<Word>();
            this.words.add(word);
        }
    }

    @Override
    public String toString() {
        return "KEYS{" +
                "id=" + id +
                ", Key='" + key + '\'' +
                ", Word=" + words +
                '}';
    }
}
