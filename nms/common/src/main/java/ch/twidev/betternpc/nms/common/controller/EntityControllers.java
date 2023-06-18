package ch.twidev.betternpc.nms.common.controller;

import org.bukkit.entity.EntityType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityControllers {

    private static final HashMap<EntityType, Constructor<? extends EntityController>> NPC_TYPES = new HashMap<>();

    static {
        // Init default entities types
        for (EntityType value : EntityType.values()) {
            NPC_TYPES.put(value, null);
        }
    }

    public static void loadControllersFromPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        for (Class<? extends EntityController> aClass : new ArrayList<>(reflections.getSubTypesOf(EntityController.class))) {
            if (aClass.isAnnotationPresent(EntityControllerType.class)) {
                EntityType entityType = aClass.getAnnotation(EntityControllerType.class).entityType();

                try {
                    Constructor<? extends EntityController> constructor = aClass.getConstructor();
                    constructor.setAccessible(true);
                    NPC_TYPES.put(entityType, constructor);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean isControllerExists(EntityType entityType) {
        return NPC_TYPES.containsKey(entityType);
    }

    public static EntityController createController(EntityType entityType) {
        if(isControllerExists(entityType)) {
            Constructor<? extends EntityController> constructor = NPC_TYPES.get(entityType);
            if(constructor == null) {
                throw new NullPointerException("Unknown entity controller constructor for entity type " + entityType);
            }

            try {
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

}
