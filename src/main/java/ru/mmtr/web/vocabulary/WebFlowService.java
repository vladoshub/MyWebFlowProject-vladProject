package ru.mmtr.web.vocabulary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mmtr.entity.Key;
import ru.mmtr.entity.Type;
import ru.mmtr.flow.Out;
import ru.mmtr.service.ServiceWorker;
import ru.mmtr.web.dto.KeysDto;
import ru.mmtr.web.dto.WordsDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component("WebFlowService")

public class WebFlowService {


    private ServiceWorker serviceWorker;

    @Autowired
    public WebFlowService(ServiceWorker serviceWorker) {
        this.serviceWorker = serviceWorker;
    }


    private Out createOutKeys(List<Key> keys) {
        Out out = new Out();
        if (keys != null && keys.size() != 0) {
            out.setKeys(keys);
        } else
            out.setOutMess("no match");
        return out;
    }

    public Out delKeyById(Long id, Integer typeOfVoc) throws IOException {
        return findKeys(typeOfVoc, serviceWorker.deleteKeyById(id), id, true);
    }

    public Out delWordById(Long id, Integer typeOfVoc, Long idKey) throws IOException {
        return findKeys(typeOfVoc, serviceWorker.deleteWordById(id), id, idKey);
    }

    public Out updateKey(Long id, String newKey, Integer typeOfVoc) throws IOException {
        return findKeys(typeOfVoc, serviceWorker.updateKey(id, newKey, typeOfVoc), id, true);
    }

    public Out updateWord(Long id, String newWord, Integer typeOfVoc, Long idKey) throws IOException {
        return findKeys(typeOfVoc, serviceWorker.updateWord(id, newWord, typeOfVoc), id, idKey);
    }

    public Out searchByKey(String key, Integer typeOfVoc) throws IOException {
        return createOutKeys(serviceWorker.searchByKey(key, typeOfVoc));
    }

    public Out searchByWords(String word, Integer typeOfVoc) throws IOException {
        return createOutKeys(serviceWorker.searchByWord(word, typeOfVoc));
    }

    public Out addKey(String key, String word, Integer voc) throws IOException {
        return findKeysAfterAddKey(voc, serviceWorker.addKey(voc, key, word));
    }

    public Out addWord(Long idKey, String word, Integer voc) throws IOException {
        return findKeys(voc, serviceWorker.addToKey(voc, idKey, word), idKey, true);

    }

    public Out findKeys(Integer typeOfVoc) throws IOException {
        Out out = new Out();
        out.setOutMess("");
        out.setKeys(serviceWorker.findKeys(typeOfVoc));
        return out;
    }

    public Out editManyWordsInKey(Integer typeOfVoc, Long keyId, String words) throws IOException {
        List<String> regWords = new ArrayList<String>();
        String masW[] = words.split("#_#");
        if (masW.length == 1)
            return findKeys(typeOfVoc, serviceWorker.addToKey(typeOfVoc, keyId, masW[0]), keyId, true);
        for (int i = 0; i < masW.length; i++) {
            regWords.add(masW[i]);
        }
        return findKeys(typeOfVoc, serviceWorker.addToKey(typeOfVoc, keyId, regWords), keyId, true);
    }

    public Out addManyWordsAndKey(String key, String word, Integer voc) throws IOException {
        List<String> regKey = new ArrayList<String>();
        String masK[] = word.split("#_#");
        if (masK.length == 1)
            return findKeysAfterAddKey(voc, serviceWorker.addKey(voc, key, masK[0]));
        for (int i = 0; i < masK.length; i++) {
            regKey.add(masK[i]);
        }
        return findKeysAfterAddKey(voc, serviceWorker.addKey(voc, key, regKey));
    }

    public Out findKeys(Integer typeOfVoc, String inPut, Long id, boolean isKey) throws IOException {

        Out out = new Out();

        out.setKeys(serviceWorker.findKeys(typeOfVoc));
        out.setOutMess(inPut);
        if (isKey)
            out.setKeysDto(new KeysDto(id));
        else
            out.setWordsDto(new WordsDto(id));
        return out;
    }

    public Out findKeys(Integer typeOfVoc, String inPut) throws IOException {
        Out out = new Out();
        out.setKeys(serviceWorker.findKeys(typeOfVoc));
        out.setOutMess(inPut);
        return out;
    }

    public Out findKeysAfterAddKey(Integer typeOfVoc, String inPut) throws IOException {
        Out out = new Out();
        out.setKeys(serviceWorker.findKeys(typeOfVoc));
        out.setOutMess(inPut);
        out.setAddKeyValid(true);
        return out;
    }

    public Out findKeys(Integer typeOfVoc, String inPut, Long id, Long idKey) throws IOException {
        Out out = new Out();
        out.setKeys(serviceWorker.findKeys(typeOfVoc));
        out.setOutMess(inPut);
        if (idKey != null) {
            out.setKeysDto(new KeysDto(id));
        }
        if (id != null) {
            out.setWordsDto(new WordsDto(id));
        }
        return out;
    }

    public Type findIdByType(String type) throws IOException {
        Type out = serviceWorker.findByIdType(type);
        return out;
    }

    public List<Type> getTypes() throws IOException {
        return serviceWorker.getTypes();
    }

}
