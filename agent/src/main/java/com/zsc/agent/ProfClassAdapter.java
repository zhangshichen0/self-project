package com.zsc.agent;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author shichen
 * @create 2019-09-11
 * @desc
 */
public class ProfClassAdapter extends ClassAdapter {

    /**
     * 类名
     */
    private String className;

    /**
     * 文件名
     */
    private String mFileName;



    public ProfClassAdapter(ClassVisitor classVisitor, String className) {
        super(classVisitor);
        this.className = className;
    }

    @Override
    public void visitSource(final String source, final String debug) {
        super.visitSource(source, debug);
        this.mFileName = source;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

    }

    @Override
    public MethodVisitor visitMethod(int arg, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(arg, name, descriptor, signature, exceptions);


        return new ProfMethodAdapter(methodVisitor, this.mFileName, this.className, name);
    }
}
