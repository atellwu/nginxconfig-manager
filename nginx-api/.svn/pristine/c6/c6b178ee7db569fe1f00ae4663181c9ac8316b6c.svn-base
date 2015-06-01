package com.yeahmobi.loadbalance_manager.helper;

import java.lang.annotation.Annotation;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

@SuppressWarnings("serial")
public class JsonValidationProcessor extends AbstractAdvisingBeanPostProcessor implements InitializingBean {

    private Class<? extends Annotation> validatedAnnotationType = Validated.class;

    private ResourceBundleMessageSource messageSource;

    /**
     * Set the 'validated' annotation type. The default validated annotation type is the {@link Validated} annotation.
     * <p>
     * This setter property exists so that developers can provide their own (non-Spring-specific) annotation type to
     * indicate that a class is supposed to be validated in the sense of applying method validation.
     *
     * @param validatedAnnotationType the desired annotation type
     */
    public void setValidatedAnnotationType(Class<? extends Annotation> validatedAnnotationType) {
        Assert.notNull(validatedAnnotationType, "'validatedAnnotationType' must not be null");
        this.validatedAnnotationType = validatedAnnotationType;
    }

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void afterPropertiesSet() {
        Pointcut pointcut = new AnnotationMatchingPointcut(this.validatedAnnotationType, true);
        JsonValidationInterceptor advice = new JsonValidationInterceptor();
        advice.setMessageSource(this.messageSource);

        this.advisor = new DefaultPointcutAdvisor(pointcut, advice);
    }

}
