package com.self.spring.factorybean;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Arrays;
import java.util.Set;

import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_TYPE;

/**
 * @author shichen
 * @create 2019-10-29
 * @desc
 */
public class ClassPathScanner extends ClassPathBeanDefinitionScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathScanner.class);

    /**
     * 只有匹配后缀的才生成
     */
    private String suffix;

    public ClassPathScanner(BeanDefinitionRegistry registry, String suffix) {
        super(registry);
        this.suffix = suffix;
    }

    public ClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, String suffix) {
        super(registry, useDefaultFilters);
        this.suffix = suffix;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolderSet = super.doScan(basePackages);
        if (beanDefinitionHolderSet.isEmpty()) {
            LOGGER.warn(() -> "No MyBatis mapper was found in '" + Arrays.toString(basePackages)
                    + "' package. Please check your configuration.");
        } else {
            processBeanDefinition(beanDefinitionHolderSet);
        }
        return beanDefinitionHolderSet;
    }

    /**
     * 处理bean
     * @param beanDefinitionHolderSet
     */
    private void processBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolderSet) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolderSet) {
            definition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            //获取原来的beanClassName
            String beanClassName = definition.getBeanClassName();

            //把相应的beanCLass设置为自定义的FactoryBean，使用我们自定义的FactoryBean生成对象
            definition.setBeanClass(ProxyBeanFactoryBean.class);
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

            //按照接口类型进行查找
            definition.setAutowireMode(AUTOWIRE_BY_TYPE);

        }
    }

    /**
     * 重写对类的判断
     *
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    /**
     * 注册自己定义的过滤器
     */
    public void registerFilters() {

        //匹配suffix后缀
        addIncludeFilter((metadataReader, metadataReaderFactory) -> metadataReader.getClassMetadata().getClassName().endsWith(suffix));

        // exclude package-info.java
        addExcludeFilter((metadataReader, metadataReaderFactory) -> {
            String className = metadataReader.getClassMetadata().getClassName();
            return className.endsWith("package-info");
        });
    }
}
