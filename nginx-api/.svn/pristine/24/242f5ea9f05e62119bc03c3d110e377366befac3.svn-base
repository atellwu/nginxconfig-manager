package org.adclear.dbunit.json.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.adclear.dbunit.json.operation.strategy.MongoBaseDataSetLoadStrategy;
import org.adclear.dbunit.json.operation.strategy.MongoDbCleanInsertLoadStrategy;

/**
 * The Annotation used at the junit class or method level to specify th
 * 
 * @author fit
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface JsonData {

	/**
	 * The filename to load the json test data from. If no filename is specified
	 * than the filename will be generated depending on the location of the
	 * JsonData annotation. The generated Filename
	 * 
	 * @return
	 */
	String fileName() default "";

	/**
	 * If true means load the test data into the specified mongodb instance
	 * otherwise is false than don't load any test data into the mongodb.
	 * 
	 * @return true to load the test data false to don't load any test data.
	 *         True is default.
	 */
	boolean loadData() default true;

	/**
	 * The strategy that needs to be used to get the dbunit dataset into the
	 * database
	 * 
	 * @return An implementation class of {@link MongoBaseDataSetLoadStrategy}.
	 *         The default value is {@link MongoDbCleanInsertLoadStrategy}.
	 */
	Class<? extends MongoBaseDataSetLoadStrategy> loadStrategy() default MongoDbCleanInsertLoadStrategy.class;

	String dateDateFormat() default "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	String calendarDateFormat() default "yyyy-MM-dd'T'HH:mm:ss.SSS";
}