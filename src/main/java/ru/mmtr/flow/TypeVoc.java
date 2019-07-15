package ru.mmtr.flow;

import ru.mmtr.entity.Type;
import ru.mmtr.service.ServiceChecker;

import java.io.Serializable;

public class TypeVoc implements Serializable {
    private Type type;
    public void setType(Type type) {
        this.type = type;
        ServiceChecker.regKeysSet(type.getRegKeys());
        ServiceChecker.regWordsSet(type.getRegWords());
    }

    public Type getType() {
        return type;
    }

}
