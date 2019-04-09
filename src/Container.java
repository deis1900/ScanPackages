import annotation.*;
import utils.ClassScaner;
import utils.AnnotatedClassesFinder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Scan(packages = "service")
@Configuration
public class Container {
    public static void main(String[] args) {
        final Class<Configuration> CONFIGURATION_CLASS = Configuration.class;
        final Class<Scan> SCAN_CLASS = Scan.class;
        Class[] classes = null;
        Class[] annotations = {Component.class, Autowired.class, Qualifier.class};

        if (Container.class.isAnnotationPresent(CONFIGURATION_CLASS)) {

            if (Container.class.isAnnotationPresent(SCAN_CLASS)) {

                String pathToScan = "packages";
                Annotation scanAnnot = Container.class.getAnnotation(SCAN_CLASS);
                String[] packagesToScan =
                        ReflectionHelper.getAnnotationParameter(scanAnnot,
                                pathToScan, String[].class);
                for (String pack : packagesToScan) {
                    try {
                        classes = ClassScaner.getClasses(pack);

                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }

                }
                Map<Class, Class> annotatedClasses =
                        AnnotatedClassesFinder.getAnnotatedClasses(classes, annotations);
                System.out.println("Component: ");
                annotatedClasses.entrySet().forEach(System.out::println);
                System.out.println("Annotation inside class: ");
                Map<Class, Map<Object, Class>> annInsideClasses = AnnotatedClassesFinder.getAnnotatedInsideClasses(classes, annotations);
                Map<Object, Class> annInClass;
                Iterator<Class> clazz = annInsideClasses.keySet().iterator();
                while(clazz.hasNext()){
                    annInClass = annInsideClasses.get(clazz.next());
                    System.out.print("Class " + clazz.next() + " have : ");
                    annInClass.entrySet().forEach(System.out::println);
                }
            }
        } else {
            String msg = "No configuration file found.";
            throw new RuntimeException(msg);
        }
    }
}
