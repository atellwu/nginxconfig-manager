package com.yeahmobi.loadbalance_manager.dao;

import java.lang.reflect.Field;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;
import com.mongodb.WriteConcern;
import com.yeahmobi.loadbalance_manager.model.BaseEntity;

/**
 */
public class MongoBaseDao<T extends BaseEntity, K> extends BasicDAO<T, K> {

    @Autowired
    protected SeqGeneratorService seqGeneratorService;

    public MongoBaseDao(Datastore ds) {
        super(ds);
    }

    @Override
    public Key<T> save(T entity) {
        generateKeyAndVersionUpdate(entity);
        return this.ds.save(entity);
    }

    @Override
    public Key<T> save(T entity, WriteConcern wc) {
        generateKeyAndVersionUpdate(entity);
        return this.ds.save(entity, wc);
    }

    @Override
    public UpdateResults<T> updateFirst(final Query<T> q, final UpdateOperations<T> ops) {
        updateVersion(ops);
        return this.ds.updateFirst(q, ops);
    }

    @Override
    public UpdateResults<T> update(final Query<T> q, final UpdateOperations<T> ops) {
        updateVersion(ops);
        return this.ds.update(q, ops);
    }

    /**
     * 如果key是Long型，且key为null，则自动生成. 自动更新version
     */
    private void generateKeyAndVersionUpdate(T entity) {
        BeanWrapper wrapper = new BeanWrapperImpl(entity);
        String name = getIdPropertyName(entity.getClass());
        Class<?> type = wrapper.getPropertyType(name);

        // 对于Long型的id，自增
        if (type == Long.class) {
            Object id = wrapper.getPropertyValue(name);
            if (id == null) {
                wrapper.setPropertyValue(name, this.seqGeneratorService.nextSeq(entity.getClass().getName()));
            }
        }

        // 更新version
        if (entity.getVersion() == null) {
            entity.setVersion(1L);
        } else {
            entity.setVersion(entity.getVersion() + 1);
        }
    }

    private String getIdPropertyName(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                return field.getName();
            }
        }
        throw new IllegalStateException("Class " + clazz + " have no @Id field");
    }

    /**
     * @param entity
     */
    private void updateVersion(final UpdateOperations<T> ops) {
        ops.inc("version");
    }

}
