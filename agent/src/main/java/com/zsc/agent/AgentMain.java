package com.zsc.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.concurrent.TimeUnit;

/**
 * @author shichen
 * @create 2019-09-11
 * @desc
 */
public class AgentMain {

    public static void premain(String args, Instrumentation inst) {
        main(args, inst);
    }

    public static void agentmain(String args, Instrumentation inst) {
        main(args, inst);
    }

    private static void main(String args, Instrumentation inst) {
        ClassFileTransformer profTransformer = new ProfTransformer();
        inst.addTransformer(profTransformer, true);
        Class[] classes = inst.getAllLoadedClasses();

        for (Class clazz : classes) {
            try {
                if (clazz.getName().startsWith("com.self.attach")) {
                    inst.retransformClasses(clazz);
                }
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }
        }

        inst.removeTransformer(profTransformer);


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final ClassFileTransformer resetClassFileTransformer = (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> null;
        //运行10s后，清除增强
        inst.addTransformer(resetClassFileTransformer, true);

        for (Class clazz : classes) {
            try {
                if (clazz.getName().startsWith("com.self.attach")) {
                    inst.retransformClasses(clazz);
                }
            } catch (UnmodifiableClassException e) {
                e.printStackTrace();
            }
        }
        inst.removeTransformer(resetClassFileTransformer);
    }

}
