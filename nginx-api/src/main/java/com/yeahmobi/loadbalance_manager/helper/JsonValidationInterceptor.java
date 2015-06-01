package com.yeahmobi.loadbalance_manager.helper;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.MethodNode;
import javax.validation.Path.Node;
import javax.validation.Path.ParameterNode;
import javax.validation.Path.PropertyNode;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yeahmobi.loadbalance_manager.api.AbstractController.JsonResult;

public class JsonValidationInterceptor extends MethodValidationInterceptor {

    private static LocalVariableTableParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    // private static final String DEFAULT_VALIDATE_MSG = "param.invalid";

    private ResourceBundleMessageSource                      messageSource;

    /**
     * Create a new MethodValidationInterceptor using a default JSR-303 validator underneath.
     */
    public JsonValidationInterceptor() {
        super();
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return super.invoke(invocation);
        } catch (IllegalArgumentException e) {
            return json(e.getMessage());
        } catch (ConstraintViolationException e) {
            return validationResult(invocation, e);
        }
    }

    private Object validationResult(MethodInvocation invocation, ConstraintViolationException e) {
        StringBuilder msgBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (violations != null) {
            for (ConstraintViolation<?> violation : violations) {
                String argName = getErrorArgName(invocation, violation);
                String message = resolveMessage(violation.getMessage(), argName, violation.getInvalidValue());
                msgBuilder.append(message).append(';');
            }
            if (msgBuilder.length() > 0) {
                msgBuilder.deleteCharAt(msgBuilder.length() - 1);
            }
        }

        return json(msgBuilder.toString());
    }

    @SuppressWarnings("unchecked")
    private Object json(String msg) {
        JsonResult result = new JsonResult();
        result.setErrorCode(JsonResult.CODE_PARAM_ERROR);
        result.setMsg(resolveMessage(msg, null, null));
        result.setResult("");

        return JSON.toJSONString(result, SerializerFeature.WriteNullListAsEmpty,
                                 SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue);
    }

    private String resolveMessage(String message, String argName, Object invalidValue) {
        if (this.messageSource != null) {
            try {
                message = this.messageSource.getMessage(message, new Object[] { argName, invalidValue }, null);
                return message;
            } catch (NoSuchMessageException e) {

            }
        }
        if (argName != null) {
            message += ":" + argName + "=" + invalidValue;
        }
        return message;
    }

    private String getErrorArgName(MethodInvocation invocation, ConstraintViolation<?> violation) {
        String[] parameterNames = nameDiscoverer.getParameterNames(invocation.getMethod());
        Iterator<Node> iterator = violation.getPropertyPath().iterator();
        Node node = null;
        while (iterator.hasNext()) {
            node = iterator.next();
        }
        if (node != null) {
            switch (node.getKind()) {
                case METHOD:
                    return node.as(MethodNode.class).getName();
                case PARAMETER:
                    int index = node.as(ParameterNode.class).getParameterIndex();
                    return parameterNames[index];
                case PROPERTY:
                    return node.as(PropertyNode.class).getName();
            }
        }
        return null;
    }

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
