package dsa.proyecto.G4.db.orm.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ObjectHelper {
    public static String[] getFields(Object entity) {
        Class<?> theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] sFields = new String[fields.length];

        int i = 0;
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                sFields[i++] = field.getName();
            }
        }

        return Arrays.copyOf(sFields, i);
    }

    public static void setter(Object object, String property, Object value) {
        // Method // invoke
        List<Method> methods = new ArrayList<>(Arrays.asList(object.getClass().getDeclaredMethods()));
        try {
            Method m = methods.stream().filter((Method method) -> method.getName().contains("set" + getMethodName(property))).findFirst().get();
            m.invoke(object,  value);
        }catch (NoSuchElementException | IllegalAccessException | InvocationTargetException e){

        }

    }

    public static String getMethodName(String property) {
        return property.substring(0,1).toUpperCase()+property.substring(1);
    }

    public static Object getter(Object object, String property) {
        String propToUppercase = property.substring(0, 1).toUpperCase() + property.substring(1);
        String getterName = "get" + propToUppercase;
        try {
            Method m = object.getClass().getDeclaredMethod(getterName);
            Object o = m.invoke(object);
            return o;

        }catch (NoSuchMethodException e){
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}