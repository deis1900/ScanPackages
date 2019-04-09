package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionHelper {


    @SuppressWarnings("unchecked")
    public static <T> T getAnnotationParameter(Annotation annotation, String parameterName, Class<T> type) {
        try {
            Method m = annotation.getClass().getMethod(parameterName);
            Object o = m.invoke(annotation);
            if ( o.getClass().getName().equals( type.getName() ) ) {
                return ( T ) o;
            }
            else {
                String msg = "Wrong parameter type. Expected: " + type.getName() + " Actual: " + o.getClass().getName();
                throw new RuntimeException( msg );
            }
        }
        catch ( NoSuchMethodException e ) {
            String msg = "The specified annotation defines no parameter '" + parameterName + "'.";
            throw new RuntimeException( msg, e );
        }
        catch ( IllegalAccessException | InvocationTargetException e ) {
            String msg = "Unable to get '" + parameterName + "' from " + annotation.getClass().getName();
            throw new RuntimeException( msg, e );
        }
    }

}