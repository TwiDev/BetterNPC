package ch.twidev.betternpc.nms.common.controller;

import org.bukkit.entity.EntityType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EntityControllerType {

    EntityType entityType();

}
