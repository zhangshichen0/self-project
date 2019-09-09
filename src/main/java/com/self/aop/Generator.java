package com.self.aop;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author shichen
 * @create 2019-09-09
 * @desc
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("com/self/aop/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);

        byte[] data = classWriter.toByteArray();

        File file = new File("/Users/shichen/program/worker/self-project/target/classes/com/self/aop/Base.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println("now generator cc success!!!!!");

        Base base = new Base();
        base.process();
    }

}
