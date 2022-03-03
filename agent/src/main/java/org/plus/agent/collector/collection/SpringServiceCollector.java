package org.plus.agent.collector.collection;


import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import org.plus.agent.collector.init.AbstractCollector;
import org.plus.agent.collector.init.AgentLoader;
import org.plus.agent.collector.init.Collector;
import org.plus.agent.collector.init.NotProguard;

/**
 * spring 注解服务收集
 *
 * @author mobingsen
 */
@NotProguard
public class SpringServiceCollector extends AbstractCollector implements Collector {
    @NotProguard
    public static SpringServiceCollector INSTANCE = new SpringServiceCollector();

    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("org.plus.agent.collector.collects.SpringServiceCollects instance= ");
        sbuilder.append("org.plus.agent.collector.collects.SpringServiceCollects.INSTANCE;\r\n");
        sbuilder.append("org.plus.agent.collector.init.AbstractCollects.Statistics statistic =instance.begin(\"%s\",\"%s\");");
        beginSrc = sbuilder.toString();
        sbuilder.setLength(0);
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();
        sbuilder.setLength(0);
        sbuilder.append("instance.error(statistic,e);");
        errorSrc = sbuilder.toString();
    }

    /**
     * 判断是否是可采集目标
     */
    @Override
    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        try {
            for (Object obj : ctclass.getAnnotations()) {
                if (obj.toString().startsWith("@org.springframework.stereotype.Service")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println(String.format("apm run error targetClassName=%s errorMessage=%s", className, e.getClass().getSimpleName() + ":" + e.getMessage()));
        }
        return false;
    }

    @NotProguard
    @Override
    public Statistics begin(String className, String method) {
        ServiceStatistics serviceStatistics = new ServiceStatistics(super.begin(className, method));
        serviceStatistics.serviceName = className;
        serviceStatistics.methodName = method;
        serviceStatistics.logType = "service";
        return serviceStatistics;
    }

    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat, "serviceLog");
    }

    /**
     * 转换字节码。
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctclass) throws
            Exception {
        AgentLoader byteLoade = new AgentLoader(className, loader, ctclass);

        CtMethod[] methods = ctclass.getDeclaredMethods();
        for (CtMethod m : methods) {
            // 屏蔽非公共方法
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            // 屏蔽静态方法
            if (Modifier.isStatic(m.getModifiers())) {
                continue;
            }
            // 屏蔽本地方法
            if (Modifier.isNative(m.getModifiers())) {
                continue;
            }

            AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
            build.setBeginSrc(String.format(beginSrc, className, m.getName()));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            byteLoade.updateMethod(m, build);
        }
        return byteLoade.toByteCode();
    }

    @NotProguard
    public static class ServiceStatistics extends Statistics {
        public String serviceName; //服务名称
        public String methodName;// 方法名称

        public ServiceStatistics(Statistics s) {
            super(s);
        }
    }

    public static void main(String[] args) {
        System.out.println(Math.asin(10 >> 4 / 3));
    }
}
