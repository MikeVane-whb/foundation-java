package ninth;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: whb
 * @date: 2022-10-04-21-22
 * @version: 1.0
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
        System.out.println("=============泛型8.9.3 虚拟机中的泛型类型信息=============");
        // read class name from command line args or user input(从命令行参数或者用户输入 读取 class 名称)
        String name;
        if(args.length > 0) name = args[0];
        else{
            try(Scanner in = new Scanner(System.in)){
                System.out.println("Enter class name (e.g, java.util.Collections): ");
                name = in.next();
            }
        }
        try {
            // print generic info for class and public methods(打印类和public方法的泛型信息)
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method declaredMethod : cl.getDeclaredMethods()) {
                printMethod(declaredMethod);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * print class info
     * @param cl
     */
    public static void printClass(Class<?> cl){
        System.out.print(cl);
        printTypes(cl.getTypeParameters(),"<",", ",">",true);
        Type sc = cl.getGenericSuperclass();
        if(sc != null){
            System.out.print(" extends ");
            printType(sc,false);
        }
        printTypes(cl.getGenericInterfaces()," implements ",", ","",false);
        System.out.println();
    }

    /**
     * print types info
     * @param types
     * @param pre pre string
     * @param sep sep string
     * @param suf suf string
     * @param isDefinition
     */
    private static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if(pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})){
            return;
        }
        if(types.length > 0){
            System.out.print(pre);
        }
        for (int i = 0; i < types.length; i++) {
            if(i > 0){
                System.out.print(sep);
            }
            printType(types[i],isDefinition);
        }
        if(types.length > 0){
            System.out.print(suf);
        }
    }

    private static void printType(Type type, boolean isDefinition) {
        if(type instanceof Class){
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        }
        else if(type instanceof TypeVariable){
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(isDefinition){
                printTypes(t.getBounds()," extends "," & ","",false);
            }
        }
        else if(type instanceof WildcardType){
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds()," extends "," & ","",false);
            printTypes(t.getLowerBounds()," super "," & ","",false);
        }
        else if(type instanceof ParameterizedType){
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if(owner != null){
                printType(owner,false);
                System.out.print(".");
            }
            printType(t.getRawType(),false);
            printTypes(t.getActualTypeArguments(),"<",", ",">",false);
        }
        else if(type instanceof GenericArrayType){
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(),isDefinition);
            System.out.print("[]");
        }
    }

    public static void printMethod(Method m){
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(),"<",", ",">",true);
        printType(m.getGenericReturnType(),false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(),"",", ","",false);
        System.out.println(")");

    }
}

