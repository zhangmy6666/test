package cn.zmy.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE) 
@Service 
public @interface Path
{
	@Required
	String value();
}
