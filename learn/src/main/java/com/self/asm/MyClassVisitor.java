package com.self.asm;


import org.objectweb.asm.*;

/**
 * @author shichen
 * @create 2019-09-09
 * @desc
 */
public class MyClassVisitor extends ClassAdapter implements Opcodes {

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        super.cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
        if (!name.equals("<init>") && mv != null) {
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }

    class MyMethodVisitor extends MethodAdapter implements Opcodes {

        public MyMethodVisitor(MethodVisitor methodVisitor) {
            super(methodVisitor);
        }

        @Override
        public void visitCode() {
            //进入Code区域时 执行
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }

        @Override
        public void visitInsn(int opcode) {
            //执行不带参指令时，进行判断，即方法返回时，加入代码
            if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
            }
            mv.visitInsn(opcode);
        }
    }
}
