package me.lolok.protoboard.version.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class FieldHelper {

    /**
     * Get a declared field of a class
     *
     * @param clazz to pick the field from
     * @param name  of the field
     */

    public static Field getDeclaredField(Class<?> clazz, String name) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    /**
     * Get a declared method of a class
     *
     * @param clazz  to pick the method from
     * @param name   of the method
     * @param params classes of the method
     */
    public static Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... params) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(name, params);
    }

    /**
     * Set a new value to a field of a class
     *
     * @param clazz  to pick the field from
     * @param object to affect
     * @param name   of the field
     * @param value  to apply
     */
    public static void setDeclaredField(Class<?> clazz, Object object, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getDeclaredField(clazz, name);
        Objects.requireNonNull(field).set(object, value);
    }

}
