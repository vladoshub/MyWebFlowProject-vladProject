package ru.mmtr.flow;

import ru.mmtr.entity.Type;
import ru.mmtr.service.ServiceChecker;

import java.io.Serializable;
import java.util.List;

public class TypeVoc implements Serializable {
    private List<Type> typeList;
    private Type type;
    public void setType(Type type) {
        this.type = type;
        ServiceChecker.regKeysSet(type.getRegKeys());
        ServiceChecker.regWordsSet(type.getRegWords());
    }

    public void setTypeList(List<Type> typeList) {
        if(!typeList.isEmpty())
        this.typeList = typeList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public Type getType() {
        return type;
    }

}
