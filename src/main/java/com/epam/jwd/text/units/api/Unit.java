package com.epam.jwd.text.units.api;

public interface Unit{
    void add(Unit anyUnit);

    Unit getChild(int position);

    void operation();
}
