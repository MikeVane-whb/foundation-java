package ninth;

/**
 * @author: whb
 * @date: 2022-10-05-12-03
 * @version: 1.0
 */

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A type literal describes a type that can be generic, such as ArrayList<String>
 * @param <T>
 */
class TypeLiteral<T>{
    private Type type;

    /**
     * This constructor must be invoked from that can be generic, such as ArrayList<String>
     */
    public TypeLiteral(){
        /**
         * getGenericSuperclass()
         *  返回 Type，该类型表示由此类表示的实体（class, interface, primitive type or void）的直接超类。
         */
        Type parentType = getClass().getGenericSuperclass();
        // ParameterizedType表示参数化类型，如集合<String>。
        if (parentType instanceof ParameterizedType){
            ParameterizedType tempType = (ParameterizedType) parentType;
            // getActualTypeArguments() 返回一个 Type 对象数组，这些对象表示此类型的实际类型参数。
            Type[] typeArguments = tempType.getActualTypeArguments();
            type = typeArguments[0];
        }
        else {
            throw new UnsupportedOperationException(
                    "Construct as new TypeLiteral<...>(){}");
        }
    }

    private TypeLiteral(Type type){
        this.type = type;
    }

    /**
     * Yields a type literal that describes the given type
     * @param type
     * @return
     */
    public static TypeLiteral<?> of(Type type){
        return new TypeLiteral<Object>(type);
    }

    public String toString(){
        if(type instanceof Class){
            // getName() 以字符串形式返回由此类对象表示的实体的名称。
            return ((Class<?>)type).getName();
        }
        else{
            return type.toString();
        }
    }

    public boolean equals(Object otherObject){
        return otherObject instanceof TypeLiteral
                && type.equals(((TypeLiteral<?>) otherObject).type);
    }

    public int hashCode(){
        return type.hashCode();
    }
}

/**
 * Formats objects, using rules that associate types with formatting functions.
 */
class Formatter{
    private Map<TypeLiteral<?>, Function<?,String>> rules = new HashMap<>();;

    /**
     * Add a formatting rule to this formatter
     * @param type the type to which this rules apply
     * @param formatterForType the function that formats objects of this type
     * @param <T>
     */
    public <T> void forType(TypeLiteral<T> type, Function<T,String> formatterForType){
        rules.put(type,formatterForType);
    }

    /**
     * Formats all fields of an object using the rules of this formatter
     * @param obj an object
     * @return a string with all field names and formatted values
     */
    public String formatFields(Object obj) throws IllegalArgumentException, IllegalAccessException{
        StringBuilder result = new StringBuilder();
        // getDeclaredFields 获取所有成员变量(包括private)
        for (Field f : obj.getClass().getDeclaredFields()){
            result.append(f.getName());
            result.append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if (formatterForType != null){
                // formatterForType has parameter type ?. Nothing can be passed to its apply
                // method. Cast makes the parameter type to Object, so we can invoke it.
                Function<Object,String> objectFormatter = (Function<Object, String>) formatterForType;
                result.append(objectFormatter.apply(f.get(obj)));
            }
            else {
                result.append(f.get(obj).toString());
            }
            result.append("\n");
        }
        return result.toString();
    }
}

public class TypeLiterals {
    public static class Sample{
        ArrayList<Integer> nums;
        ArrayList<Character> chars;
        ArrayList<String> strings;

        public Sample(){
            nums = new ArrayList<>();
            nums.add(42);
            nums.add(1729);
            chars = new ArrayList<>();
            chars.add('H');
            chars.add('i');
            strings = new ArrayList<>();
            strings.add("Hello");
            strings.add("World");
        }
    }

    private static <T> String join(String separator, ArrayList<T> elements){
        StringBuilder result = new StringBuilder();
        for(T e : elements){
            if(result.length() > 0){
                result.append(separator);
            }
            result.append(e.toString());
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        Formatter formatter = new Formatter();
        formatter.forType(new TypeLiteral<ArrayList<Integer>>(){},
                lst -> join(" ",lst));
        formatter.forType(new TypeLiteral<ArrayList<Character>>(){},
                lst -> "\"" + join("",lst) + "\"");
        System.out.println(formatter.formatFields(new Sample()));
    }
}
