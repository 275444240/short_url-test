package com.cenyol.example.repository;

import com.cenyol.example.model.UrlInfo;
import com.wm.nb.dao.base.BaseDao;

import java.util.List;

/**
 * Created by Brian on 2016/2/24.
 */
public interface UrlDao extends BaseDao<UrlInfo,Long> {
    /**
     * 插入
     * @param urlInfo
     */
    void insert(UrlInfo urlInfo);

    /**
     * 获取list
     * @return
     */
    List<UrlInfo> selectAll();

    /**
     * 获取实体
     * @param url
     * @return
     */
    UrlInfo getEntryByShortKey(String url);
}
