package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class AnnotatedClassesFinder {
    public static Map<Class, Class> getAnnotatedClasses(Class[] classes, Class[] annotations) {

        Map<Class, Class> annotatedClasses = new HashMap<>();
        Arrays.stream(annotations)
                .forEach((annotation) -> {
                    for (Class clazz : classes) {
                        if (clazz.isAnnotationPresent(annotation)) {
                            annotatedClasses.put(clazz, annotation);
                        }
                    }
                });
        return annotatedClasses;
    }

    public static Map<Class, Map<Object, Class>> getAnnotatedInsideClasses(Class[] classes, Class[] annotations) {
        Map<Class, Map<Object, Class>> classesWithAnnotations = new HashMap<>();
        for (Class clazz : classes) {
            classesWithAnnotations.put(clazz, annotatedMethods(clazz.getMethods(), annotations));
            classesWithAnnotations.put(clazz, annotatedConstructors(clazz.getConstructors(), annotations));
        }

        return classesWithAnnotations;
    }

    private static Map<Object, Class> annotatedMethods(Method[] methods, Class[] annotations) {
        Map<Object, Class> methodsWithAnnotation = new HashMap<>();

        Arrays.stream(annotations).forEach(annotation -> {
            for (Method method : methods) {
                if (method.isAnnotationPresent(annotation)) {
                    methodsWithAnnotation.put(method, annotation);
                }
            }
        });
        return methodsWithAnnotation;
    }

    private static Map<Object, Class> annotatedConstructors(Constructor[] constructors, Class[] annotations) {
        Map<Object, Class> annotatedConstructors = new HashMap<>();
        Arrays.stream(annotations).forEach(annotation -> {
            for (Constructor constructor : constructors) {
                if (constructor.isAnnotationPresent(annotation)) {
                    annotatedConstructors.put(constructor, annotation);
                }
            }
        });
        return annotatedConstructors;
    }
}
