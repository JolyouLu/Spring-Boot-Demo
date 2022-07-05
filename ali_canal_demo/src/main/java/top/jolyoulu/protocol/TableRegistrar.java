package top.jolyoulu.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TableRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    private static final Logger log = LoggerFactory.getLogger(TableRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        // 构建一个classPath扫描器
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.setResourceLoader(this.resourceLoader);
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Table.class);
        scanner.addIncludeFilter(annotationTypeFilter);
        // 获取需要扫描的包路径
        List<String> basePackages = new ArrayList<>();
        Map<String, Object> attributes = metadata.getAnnotationAttributes(EnableTableScan.class.getCanonicalName());
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        // 添加当前项目包
        basePackages.add(ClassUtils.getPackageName(metadata.getClassName()));
        log.info("扫描包 => "+Arrays.toString(basePackages.toArray()));
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            // 构建信息
            if (!candidateComponents.isEmpty()) {
                for (BeanDefinition bd : candidateComponents) {
                    try {
                        // 注入数据
                        String className = bd.getBeanClassName();
                        Class<?> aClass = Class.forName(className);
                        Table table = aClass.getAnnotation(Table.class);
                        String tableName = table.name();
                        Class<?> old = TableRegistrarUtils.putIfAbsent(tableName, aClass);
                        if (old != null) {
                            log.error("{} 类被 {} 类覆盖", old, aClass);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
