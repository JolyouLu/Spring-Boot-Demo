package top.jolyoulu.springboot_jsp_shiro.shiro.salt;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author: JolyouLu
 * @Date: 2021/6/17 22:10
 * @Version 1.0
 * 自定义salt实现 实现序列化接口 解决Redis序列化报错 java.io.NotSerializableException: org.apache.shiro.util.SimpleByteSource
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {


    public MySimpleByteSource(byte[] bytes) {
        super(bytes);
    }

    public MySimpleByteSource(char[] chars) {
        super(chars);
    }

    public MySimpleByteSource(String string) {
        super(string);
    }

    public MySimpleByteSource(ByteSource source) {
        super(source);
    }

    public MySimpleByteSource(File file) {
        super(file);
    }

    public MySimpleByteSource(InputStream stream) {
        super(stream);
    }
}
