package com.medium.shazinsadakath.pii.data.protection.demo.utils;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.lang.annotation.Annotation;

public class ProtectDataAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    protected <A extends Annotation> A _findAnnotation(Annotated ann, Class<A> annoClass) {
        if (!ann.hasAnnotation(ProtectData.class)) {
            return super._findAnnotation(ann, annoClass);
        }
        return null;
    }
}
