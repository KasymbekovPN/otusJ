package ru.otus.kasymbekovPN.HW04;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;


public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader,
                                    String className,
                                    Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain,
                                    byte[] classfileBuffer) throws IllegalClassFormatException {

                if (className.equals("ru/otus/kasymbekovPN/HW04/accumulator/Accumulator")){
                    return addLogs(classfileBuffer);
                }
                return classfileBuffer;
            }
        });
    }

    private static byte[] addLogs(byte[] originalClass){
        return originalClass;
    }
}
