package com.example.demo1.serial.compareserial;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;

/**
 * @author liangqing
 * @since 2020/11/24 14:58
 */
@Slf4j
public class JdkSerial {

    public static void main(String[] args) throws IOException {
        jdkSerial();
        //   hessianSerial();
//        kryoSerial();
    }

    /**
     * 1. java序列化
     * java自带序列化
     * 不支持跨语言，仅支持java ，java私有化的内部序列化协议
     * 序列化之后的体积是二进制流的5倍
     * 序列化后的性能是二进制的6.17倍
     *
     * @throws FileNotFoundException
     */
    private static void jdkSerial() throws FileNotFoundException {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            OutputStream outputStream = new FileOutputStream(new File("test-person.txt"));
            User user = new User();
            user.setName("lq")
                    .setAge(12)
                    .setSex("男");
            SerializationUtils.serialize(user, outputStream);
//            SerializationUtils.deserialize(new FileInputStream("test-person.txt"));
        }
        long e = System.currentTimeMillis();
        log.info("方法名：main,结果{}", (e - l) / 1000);
    }

    private static void jdkReverseSerial() throws FileNotFoundException {
        long l = System.currentTimeMillis();
        SerializationUtils.deserialize(new FileInputStream("test-person.txt"));
        long e = System.currentTimeMillis();
        log.info("方法名：main,结果{}", (e - l) / 1000);
    }

    /**
     * hessian序列化的优缺点
     * 支持跨语言
     * 速度较慢，性能较差
     *
     * @throws FileNotFoundException
     */
    private static void hessianSerial() throws IOException {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {

            User user = new User();
            user.setName("lq")
                    .setAge(12)
                    .setSex("男");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput output = new HessianOutput(os);
            output.writeObject(user);

            byte[] userByte = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(userByte);
            //Hessian的反序列化读取对象
            HessianInput hi = new HessianInput(is);
            User user1 = (User) hi.readObject();
        }
        long e = System.currentTimeMillis();
        log.info("方法名：main,结果{}", (e - l) / 1000);
    }

    /**
     * 跨语言比较复杂，可以实现
     * 速度快，体积小
     * 需要默认的无参构造函数
     * 不是线程安全的可以通过threadlocal实现
     *
     * @throws IOException
     */
    private static void kryoSerial() throws IOException {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("user.txt"));
        User user = new User();
        user.setName("ewqeq")
                .setAge(11)
                .setSex("nan");
        kryo.writeObject(output, user);
        output.close();
        Input input = new Input(new FileInputStream("user.txt"));
        User user1 = kryo.readObject(input, User.class);
        input.close();
        System.out.println(user1.toString());
        assert "idea".equals(user1.getName());
    }

    /*
    facebook的thrift  IDL中间语言  开源的rpc矿建
    支持多种语言，
    适合大型系统内部通讯  ，相对json和xml 在性能和传输上具有优势
    支持三种比较常见的编码方式（1.二进制编码，2 压缩二进制编码，3 可选字段的压缩编码）

    google 的protobuf 支持java  c++  python
    与语言无关，可扩展性好
    结构化存储  xml   json
    高性能编解码

    fst （fast-serialization） 重新实现的java序列化开发包
    仅支持java
    体积是jdk序列化的1/3  速度是jdk序列化的4到10倍

    序列化成json

    1. jackson: 存在依赖包，复杂类型的json转bean 会出现问题
    复杂类型的bean转json会出现不是标准的json
    2. gson  直接在jdk 运行 fromjson tojson 功能完美，效率较低
    3. fastjson 直接在jdk运行，不需要依赖
    对于复杂类型的bean转json会出现引用类型，需要定制引用
    速度最快 ，定制的json，超过上面两个类库


    protostuff也许是最佳选择。protostuff相比于kyro还有一个额外的好处，就是如果序列化之后，
    反序列化之前这段时间内，java class增加了字段（这在实际业务中是无法避免的事情），kyro就废了。
    但是protostuff只要保证新字段添加在类的最后，而且用的是sun系列的JDK, 是可以正常使用的。
    因此，如果序列化是用在缓存等场景下，序列化对象需要存储很久，也就只能选择protostuff了。

作者：jiangmo
链接：https://www.jianshu.com/p/937883b6b2e5
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

     */
}
