package com.potopalskyi.movieland.entity.annotation;

import com.potopalskyi.movieland.entity.enums.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleTypeRequired {
    RoleType role();
}
