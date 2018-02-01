package com.ilearn.dubbo.appframework.impl;

import com.ilearn.dubbo.appframework.dao.BaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ilearn.dubbo.appframework.log.LogUtils;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1 0001.
 * 数据访问基础实现类<br>
 *         注：抛异常时不要使用Exception(String message, Throwable cause),否则外面收到的异常会有部分丢失
 *         建议直接使用：throw e;
 */
public class BaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

    private static Logger LOG = LoggerFactory.getLogger(BaseDaoImpl.class);

    public <T, P> T selectOne ( P params, String sqlTag ) throws Exception {
        T rt = null;

        try {
            rt = getSqlSession().selectOne(sqlTag, params);
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, params, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <T, P> List<T> selectList ( P params, String sqlTag ) throws Exception {
        List<T> rt = null;
        try {
            if (null == params) {
                rt = getSqlSession().selectList(sqlTag);
            } else {
                rt = getSqlSession().selectList(sqlTag, params);
            }
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, params, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <T> Integer insert ( T t, String sqlTag ) throws Exception {
        Integer rt = null;
        try {
            rt = getSqlSession().insert(sqlTag, t);
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, t, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <T> Integer insertBatch ( List<T> ts, String sqlTag ) throws Exception {
        Integer rt = null;
        try {
            rt = getSqlSession().insert(sqlTag + "." + sqlTag, ts);
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, ts, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <P> Integer update ( P params, String sqlTag ) throws Exception {
        Integer rt = null;
        try {
            rt = getSqlSession().update(sqlTag, params);
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, params, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <P> Integer delete ( P params, String sqlTag ) throws Exception {
        Integer rt = null;
        try {
            if (null == params) {
                rt = getSqlSession().delete(sqlTag);
            } else {
                rt = getSqlSession().delete(sqlTag, params);
            }
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, params, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }

    public <P> Integer deleteBatch ( List<P> params, String sqlTag ) throws Exception {
        Integer rt = null;
        try {
            if (null == params) {
                rt = getSqlSession().delete(sqlTag);
            } else {
                rt = getSqlSession().delete(sqlTag, params);
            }
        } catch (Exception e) {
            LOG.error("{} error, params = {}, error={}", new Object[] { sqlTag, params, LogUtils.stackTrace(e) });
            throw e;
        }
        return rt;
    }
}
