package com.cenyol.example.repository;

import com.cenyol.example.model.UrlInfo;
import com.wm.nb.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Brian on 2016/2/24.
 */
@Repository("urlDao")
public class UrlDaoImpl extends BaseDaoImpl<UrlInfo,Long> implements UrlDao {

    private final static String NAMESPACE = "test.";

    public void insert(UrlInfo urlInfo) {
        this.insert(getNameSpace("insert"), urlInfo);
    }

    @Override
    public List<UrlInfo> selectAll() {
        return this.selectList(getNameSpace("selectAll"),null);
    }

    @Override
    public UrlInfo getEntryByShortKey(String url) {
        return this.select(getNameSpace("getEntryByShortKey"),url);
    }

    public String getNameSpace(String s) {
        return NAMESPACE + s;
    }
}
