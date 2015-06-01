package org.adclear.dbunit.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;

import org.adclear.dbunit.json.annotations.JsonData;
import org.adclear.dbunit.json.operation.strategy.MongoBaseDataSetLoadStrategy;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.UntypedObjectDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;
import org.dbunit.dataset.IDataSet;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.unitils.util.ReflectionUtils;

import com.mongodb.MongoURI;

/**
 * The {@link MethodRule} implementation for the dbunit mongodb specific rule.
 *
 * @author fit
 */
public class DbUnitRuleMongo implements MethodRule {

    private final Class<?>      resourceBase;

    private MongoDatabaseTester databaseTester;

    @Getter
    private MongoURI            mongoUri;

    public DbUnitRuleMongo(Class<?> resourceBase, String url) {
        this.resourceBase = resourceBase;
        try {
            this.mongoUri = new MongoURI(url);

            this.databaseTester = new MongoDatabaseTester(this.mongoUri);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {

                try {

                    JsonDataResult jsonDataResult = getJsonData(method);

                    if (jsonDataResult.isJsonDataPresent() == false) {
                        base.evaluate();
                        return;
                    }

                    String dataSetFileName = jsonDataResult.getFileName();
                    JsonData data = jsonDataResult.getJsonData();

                    if (data.loadData() == false) {
                        Class<? extends MongoBaseDataSetLoadStrategy> loadStrategyClass = data.loadStrategy();

                        MongoBaseDataSetLoadStrategy mongoBaseDataSetLoadStrategy = ReflectionUtils.createInstanceOfType(loadStrategyClass,
                                                                                                                         false);
                        mongoBaseDataSetLoadStrategy.getLoadOperation().execute(DbUnitRuleMongo.this.databaseTester.getConnection(),
                                                                                null);

                    } else {

                        ObjectMapper mapper = getMapper(data);

                        InputStream resourceAsStream = DbUnitRuleMongo.this.resourceBase.getResourceAsStream(dataSetFileName);

                        if ((resourceAsStream == null) && !dataSetFileName.contains("/")) {
                            File file = new File("src/test/resources/" + dataSetFileName);
                            resourceAsStream = new FileInputStream(file);
                        }

                        if (resourceAsStream != null) {
                            IDataSet ds = new JSONDataSet(resourceAsStream, mapper);
                            DbUnitRuleMongo.this.databaseTester.setDataSet(ds);

                            Class<? extends MongoBaseDataSetLoadStrategy> loadStrategyClass = data.loadStrategy();

                            MongoBaseDataSetLoadStrategy mongoBaseDataSetLoadStrategy = ReflectionUtils.createInstanceOfType(loadStrategyClass,
                                                                                                                             false);

                            DbUnitRuleMongo.this.databaseTester.setSetUpOperation(mongoBaseDataSetLoadStrategy.getLoadOperation());

                            DbUnitRuleMongo.this.databaseTester.onSetup();
                        } else {
                            System.out.println(dataSetFileName + " was not found.");
                            // FIXME logging and error handling is missing
                        }
                    }

                    base.evaluate();
                } finally {
                    DbUnitRuleMongo.this.databaseTester.onTearDown();
                }
            }
        };
    }

    private static JsonDataResult getJsonData(final FrameworkMethod method) {

        // get JsonData annotation from the current test method
        JsonData data = method.getAnnotation(JsonData.class);
        ElementType elementType = ElementType.METHOD;
        if (data == null) {
            // if no JsonData annotation was present on the method level than
            // check class level
            data = method.getMethod().getDeclaringClass().getAnnotation(JsonData.class);
            elementType = ElementType.TYPE;
        }
        return new JsonDataResult(data, elementType, method);
    }

    public ObjectMapper getMapper(JsonData data) {
        final SimpleDateFormat dateSimpleDateFormat = new SimpleDateFormat(data.dateDateFormat());
        SimpleModule module = new SimpleModule("dbunit_mongodb", new Version(1, 0, 0, null));
        ObjectMapper mapper = new ObjectMapper();

        // FIXME maybe find a better way to support Date and NumberLong date
        // type for mongo
        module.addDeserializer(Object.class, new UntypedObjectDeserializer() {

            @Override
            public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
                                                                                 JsonProcessingException {
                switch (jp.getCurrentToken()) {

                    case VALUE_STRING:

                        String text = jp.getText();
                        if (text.startsWith("NumberLong")) {
                            return Long.valueOf(text.substring("NumberLong".length() + 1, text.length() - 1));
                        }
                        if (text.length() >= (dateSimpleDateFormat.toPattern().length() - 6)) try {
                            Date date = dateSimpleDateFormat.parse(text);
                            return date;
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                        }
                        return text;
                    default:
                        return super.deserialize(jp, ctxt);
                }
            }

        });
        mapper = mapper.withModule(module);
        return mapper;
    }

    public MongoURI getMongoUri() {
        return this.mongoUri;
    }

    public void setMongoUri(MongoURI mongoUri) {
        this.mongoUri = mongoUri;
    }

}
