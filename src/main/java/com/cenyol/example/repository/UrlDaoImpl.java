package com.cenyol.example.repository;

import com.cenyol.example.model.UrlInfo;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by Brian on 2016/2/24.
 */
@Repository("urlDao")
public class UrlDaoImpl extends SqlSessionDaoSupport implements UrlDao {


    public void insert(UrlInfo urlInfo) {
        SqlSession sqlSession = this.getSqlSession();
        sqlSession.insert("test.insert", urlInfo);
    }

}
