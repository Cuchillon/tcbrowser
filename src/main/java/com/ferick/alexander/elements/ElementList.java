package com.ferick.alexander.elements;

import java.util.ArrayList;
import java.util.Optional;

public class ElementList<E extends BaseElement> extends ArrayList<E> {

    public Optional<E> getElement(String text) {
        return stream().filter(e -> e.getText().equals(text)).findFirst();
    }
}
