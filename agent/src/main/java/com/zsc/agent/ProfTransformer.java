package com.zsc.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shichen
 * @create 2019-09-11
 * @desc
 */
public class ProfTransformer implements ClassFileTransformer {

    /**
     * 不注入的Package集合
     */
    private static Set<String> excludePackage = new HashSet<String>();

    static {
        // 默认不注入的Package
        excludePackage.add("java/");// 包含javax
        excludePackage.add("sun/");// 包含sunw
        excludePackage.add("com/sun/");
        excludePackage.add("org/");// 包含org/xml org/jboss org/apache/xerces org/objectweb/asm
        // 不注入profile本身
        excludePackage.add("com/zsc/agent");
    }

    /**
     * 是否是不需要注入的类加载器
     *
     * @param className
     * @return
     */
    public static boolean isNotNeedInjectPackage(String className) {
        String icaseName = className.toLowerCase().replace('.', '/');
        for (String v : excludePackage) {
            if (icaseName.startsWith(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (isNotNeedInjectPackage(className)) {
            return classfileBuffer;
        }

        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        ClassVisitor classVisitor = new ProfClassAdapter(classWriter, className);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        return classWriter.toByteArray();
    }
}
