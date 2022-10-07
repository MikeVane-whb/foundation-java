package common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: whb
 * @date: 2022-10-07-16-13
 * @version: 1.0
 */
public class Demo<T> {

    private Type type;

    public Demo(){
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class){
            throw new  IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        else {
            Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            this.type = actualTypeArgument;
            System.out.println(type.getTypeName());
        }
    }
}
