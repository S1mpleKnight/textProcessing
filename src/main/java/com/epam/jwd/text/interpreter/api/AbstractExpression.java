package com.epam.jwd.text.interpreter.api;

import com.epam.jwd.text.interpreter.impl.Expression;

public interface AbstractExpression{
    public abstract void interpret(Expression expression);
}
