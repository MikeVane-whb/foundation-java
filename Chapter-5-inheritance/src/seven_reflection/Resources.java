package seven_reflection;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 资源
 * @author: whb
 * @date: 2022-10-07-17-04
 * @version: 1.0
 */
public class Resources {
    public static void main(String[] args) throws IOException {
        System.out.println("=============反射5.7.3 资源=============");
        System.out.println("Class 可以查找资源文件，步骤如下");
        System.out.println("1. 获得拥有资源的类的 Class 对象，例如，ResourceTest.class");
        System.out.println("2. 有些方法，如 ImageIcon 类的 getImage 方法，接受描述资源位置的URL");
        System.out.println("3. 否则，使用 getResourceAsStream 方法得到一个输入流来读取文件中的数据");
        Class cl = Resources.class;
        URL aboutURL = cl.getResource("about.gif");
        ImageIcon icon = new ImageIcon(aboutURL);

        InputStream stream = cl.getResourceAsStream("data/about.txt");
        byte[] bytes = readInputStream(stream);
        String about = new String(bytes, "UTF-8");

        InputStream stream2 = cl.getResourceAsStream("corejava/title.txt");
        bytes = readInputStream(stream2);
        String title = new String(bytes, StandardCharsets.UTF_8).trim();

        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
