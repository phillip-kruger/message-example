package com.github.phillipkruger.messageexample.application;

import javax.enterprise.util.AnnotationLiteral;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class QualifiedLiteral extends AnnotationLiteral<Qualified> implements Qualified {

    private String value="";

    @Override
    public String value() {
        return this.value;
    }
}