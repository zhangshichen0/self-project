package com.zsc.agent;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author shichen
 * @create 2019-09-11
 * @desc
 */
public class ProfMethodAdapter extends MethodAdapter implements Opcodes {

    private String mFileName;

    private String mClassName;

    private String methodName;

    public ProfMethodAdapter(MethodVisitor methodVisitor, String mFileName, String mClassName, String methodName) {
        super(methodVisitor);
        this.mClassName = mClassName;
        this.mFileName = mFileName;
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        //进入Code区域时 执行
        super.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("start " + this.mClassName + " " + this.methodName);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }

    @Override
    public void visitInsn(int opcode) {
        //执行不带参指令时，进行判断，即方法返回时，加入代码
        if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("end " + this.mClassName + " " + this.methodName);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
