package org.plus.agent.collector;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plus.agent.collector.collection.SpringServiceCollector;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

class SpringServiceCollectorTest {

    @Test
    void isTarget() throws NotFoundException {
        String className = "org.plus.agent.collector.SpringServiceCollectorTest$StringService";
        ClassLoader classLoader = SpringServiceCollectorTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        CtClass ctClass = pool.get(className);
        boolean target = SpringServiceCollector.INSTANCE.isTarget(className, classLoader, ctClass);
        Assertions.assertTrue(target);
    }

    @Test
    void transform() throws Exception {
        SpringServiceCollector ins = SpringServiceCollector.INSTANCE;
        ClassLoader loader = SpringServiceCollector.class.getClassLoader();
        String className = "org.plus.agent.collector.SpringServiceCollectorTest$StringServiceMock";
        byte[] classfileBuffer = null;
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass cclass = pool.get(className);
        ins.transform(loader, className, classfileBuffer, cclass);
        Class<?> cla = cclass.toClass();
        StringServiceMock mock = new StringServiceMock();
        mock.sayHello("hanmeme");
        TimeUnit.MILLISECONDS.sleep(2000);
    }

    // @Service
    @Service(value = "StringServiceMockBean")
    public static class StringServiceMock {

        public void sayHello(String name) {
            System.out.println("hello " + name);
        }
    }
}