package com.self.spring.factorybean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 注入MapperDefinitionConfigurer，用于生成bean实例
 *
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class MapperRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        //nop
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(Mapper.class.getName()));
        if (mapperScanAttrs != null) {
            registerBeanDefinitions(mapperScanAttrs, registry, generateBaseBeanName(importingClassMetadata, 0));
        }
    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata, int index) {
        return importingClassMetadata.getClassName() + "#" + MapperRegistrar.class.getSimpleName() + "#" + index;
    }

    private void registerBeanDefinitions(AnnotationAttributes mapperScanAttrs, BeanDefinitionRegistry registry, String name) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperDefinitionConfigurer.class);
        registry.registerBeanDefinition(name, builder.getBeanDefinition());

    }
}
