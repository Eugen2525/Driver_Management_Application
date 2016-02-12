package ru.javawebinar.topjava.util;

import java.beans.PropertyEditorSupport;

/**
 * Created by Eugen2525 on 12/10/2015.
 */
public class EnumEditor extends PropertyEditorSupport {
    private Class clazz;

    public EnumEditor(Class clazz) {
        this.clazz = clazz;
    };

    public String getAsText() {
        return (((Enum) getValue()).name());
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Enum.valueOf(clazz, text));
    }
}
