package com.cenyol.example.utils;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

import java.util.*;

/**
 * Description:RedisUtil
 * Project: dmall-personal
 * Package: com.dmall.personal.common.utils
 * File: RedisUtil
 * Date: 2018-12-19 18:31
 * Copyright (c) 2018, RichLiuChude All Rights Reserved.
 */

public class RedisUtil {
    private Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static XStream xstream = new XStream();
    private ShardedJedisPool shardedJedisPool;
    private boolean use;
    private int defaultExpireTime = 10800;

    public RedisUtil() {
    }

    public void rpush(String key, String... val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.rpush(key, val);
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }

    }

    public void lpush(String key, String... val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.lpush(key, val);
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public Long llen(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Long var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long llen = shardedJedis.llen(key);
                var4 = llen;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public List lrange(String key, int start, int end) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            List var6;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                List<String> lrange = shardedJedis.lrange(key, (long) start, (long) end);
                var6 = lrange;
                return var6;
            } catch (Exception var10) {
                log.error("redis操作发生异常", var10);
                var6 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var6;
        }
    }

    public void lset(String key, int index, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.lset(key, (long) index, value);
            return;
        } catch (Exception var9) {
            log.error("redis操作发生异常", var9);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }

    }

    public String lpop(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            String var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String lpop = shardedJedis.lpop(key);
                var4 = lpop;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public String rpop(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            String var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String rpop = shardedJedis.rpop(key);
                var4 = rpop;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public boolean exists(String key) {
        ShardedJedis shardedJedis = null;
        boolean var4;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            Boolean exists = shardedJedis.exists(key);
            var4 = exists.booleanValue();
            return var4;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
            var4 = false;
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }

        return var4;
    }

    public Long del(String key) {
        ShardedJedis shardedJedis = null;
        Long var4;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            Long var3 = shardedJedis.del(key);
            return var3;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
            var4 = 0L;
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
        return var4;
    }

    public void set(String key, String val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.setex(key, this.defaultExpireTime, val);
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void set(String key, String val, boolean expire) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            if (expire) {
                shardedJedis.setex(key, this.defaultExpireTime, val);
            } else {
                shardedJedis.set(key, val);
            }
            return;
        } catch (Exception var9) {
            log.error("redis操作发生异常", var9);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public String get(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;

            String var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String string = shardedJedis.get(key);
                if (StringUtils.isNotBlank(string) && string.startsWith("<") && string.endsWith(">")) {
                    string = null;
                    this.del(key);
                }
                var4 = string;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public String getset(String key, String value) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            String var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String set = shardedJedis.getSet(key, value);
                var5 = set;
                return var5;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var5;
        }
    }

    public void lrem(String key, Integer count, String val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.lrem(key, (long) count.intValue(), val);
            return;
        } catch (Exception var9) {
            log.error("redis操作发生异常", var9);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void sadd(String key, String val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.sadd(key, new String[]{val});
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public String spop(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            String var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String value = shardedJedis.spop(key);
                var4 = value;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public Set<String> smembers(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Set var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Set<String> value = shardedJedis.smembers(key);
                var4 = value;
                return var4;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public int scard(String key) {
        if (!this.use) {
            return 0;
        } else {
            ShardedJedis shardedJedis = null;
            byte var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long value = shardedJedis.scard(key);
                int var10 = value.intValue();
                return var10;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = 0;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public void expire(String key, int num) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.expire(key, num);
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void setex(String key, int seconds, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.setex(key, seconds, value);
            return;
        } catch (Exception var9) {
            log.error("redis操作发生异常", var9);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void srem(String key, String val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.srem(key, new String[]{val});
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public boolean sismember(String key, String member) {
        if (!this.use) {
            return false;
        } else {
            ShardedJedis shardedJedis = null;
            boolean var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                boolean var4 = shardedJedis.sismember(key, member).booleanValue();
                return var4;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = false;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var5;
        }
    }

    public Long incr(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long var3 = shardedJedis.incr(key);
                return var3;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Long) var4;
        }
    }

    public Long decr(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long var3 = shardedJedis.decr(key);
                return var3;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Long) var4;
        }
    }

    public Long hincrBy(String key, String field, long value) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var7;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long var6 = shardedJedis.hincrBy(key, field, value);
                return var6;
            } catch (Exception var11) {
                log.error("redis操作发生异常", var11);
                var7 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Long) var7;
        }
    }

    public boolean hmexite(String key, String field) {
        if (!this.use) {
            return false;
        } else {
            ShardedJedis shardedJedis = null;
            boolean var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                boolean var4 = shardedJedis.hexists(key, field).booleanValue();
                return var4;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = false;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var5;
        }
    }

    public Long hgetNum(String key, String field) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Long var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String s = shardedJedis.hget(key, field);
                if (StringUtils.isNotBlank(s)) {
                    var5 = Long.valueOf(s);
                    return var5;
                }
                var5 = 0L;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = null;
                return var5;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var5;
        }
    }

    public Long hsetnx(String key, String field, String value) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var6;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long var5 = shardedJedis.hsetnx(key, field, value);
                return var5;
            } catch (Exception var10) {
                log.error("redis操作发生异常", var10);
                var6 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Long) var6;
        }
    }

    public Map<String, String> hgetAll(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Map var3 = shardedJedis.hgetAll(key);
                return var3;
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Map) var4;
        }
    }

    public boolean isUse() {
        return this.use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public Object getBySerialize(String key) {
        if (!this.use) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String string = shardedJedis.get(key);
                if (string == null) {
                    var4 = null;
                    return var4;
                }
                var4 = xstream.fromXML(string);
            } catch (Exception var8) {
                log.error("redis操作发生异常", var8);
                var4 = null;
                return var4;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public void setBySerialize(String key, Object val) {
        ShardedJedis shardedJedis = null;
        try {
            if (val == null) {
                return;
            }
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.setex(key, this.defaultExpireTime, xstream.toXML(val));
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void setexBySerialize(String key, int seconds, Object val) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.setex(key, seconds, val == null ? null : xstream.toXML(val));
            return;
        } catch (Exception var9) {
            log.error("redis操作发生异常", var9);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void zadd(String key, double score, String member) {
        if (this.use) {
            if (!StringUtils.isBlank(key)) {
                ShardedJedis shardedJedis = null;
                try {
                    shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                    shardedJedis.zadd(key, score, member);
                    return;
                } catch (Exception var10) {
                    log.error("redis操作发生异常", var10);
                } finally {
                    if (shardedJedis != null) {
                        this.shardedJedisPool.returnResource(shardedJedis);
                    }
                }
            }
        }
    }

    public void zrem(String key, String member) {
        if (this.use) {
            if (!StringUtils.isBlank(key)) {
                ShardedJedis shardedJedis = null;
                try {
                    shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                    shardedJedis.zrem(key, new String[]{member});
                    return;
                } catch (Exception var8) {
                    log.error("redis操作发生异常", var8);
                } finally {
                    if (shardedJedis != null) {
                        this.shardedJedisPool.returnResource(shardedJedis);
                    }
                }
            }
        }
    }

    public Double zscore(String key, String member) {
        Double result = null;
        if (!this.use) {
            return null;
        } else if (StringUtils.isBlank(key)) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            Object var6;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                result = shardedJedis.zscore(key, member);
                return result;
            } catch (Exception var10) {
                log.error("redis操作发生异常", var10);
                var6 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Double) var6;
        }
    }

    public List<String> zquery(String key, long start, long end, boolean isAsc) {
        if (!this.use) {
            return null;
        } else if (StringUtils.isBlank(key)) {
            return null;
        } else {
            ShardedJedis shardedJedis = null;
            try {
                ArrayList list;
                try {
                    shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                    Set<String> set = null;
                    if (isAsc) {
                        set = shardedJedis.zrange(key, start, end);
                    } else {
                        set = shardedJedis.zrevrange(key, start, end);
                    }
                    if (null == set || set.size() <= 0) {
                        list = null;
                        return list;
                    } else {
                        list = new ArrayList();
                        Iterator i$ = set.iterator();

                        while (i$.hasNext()) {
                            String s = (String) i$.next();
                            list.add(s);
                        }

                        ArrayList var17 = list;
                        return var17;
                    }
                } catch (Exception var15) {
                    log.error("redis操作发生异常", var15);
                    list = null;
                    return list;
                }
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
        }
    }

    public long zcard(String key) {
        if (!this.use) {
            return 0L;
        } else if (StringUtils.isBlank(key)) {
            return 0L;
        } else {
            ShardedJedis shardedJedis = null;
            long var4;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                long var3 = shardedJedis.zcard(key).longValue();
                return var3;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var4 = 0L;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var4;
        }
    }

    public void setDefaultExpireTime(int defaultExpireTime) {
        this.defaultExpireTime = defaultExpireTime;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public String hget(String key, String field) {
        if (!this.use) {
            return null;
        } else if (!StringUtils.isBlank(key) && !StringUtils.isBlank(field)) {
            ShardedJedis shardedJedis = null;
            Object var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                String var4 = shardedJedis.hget(key, field);
                return var4;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (String) var5;
        } else {
            return null;
        }
    }

    public Long hdel(String key, String... fields) {
        if (!this.use) {
            return null;
        } else if (!StringUtils.isBlank(key) && fields != null && fields.length > 0) {
            ShardedJedis shardedJedis = null;
            Object var5;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                Long var4 = shardedJedis.hdel(key, fields);
                return var4;
            } catch (Exception var9) {
                log.error("redis操作发生异常", var9);
                var5 = null;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return (Long) var5;
        } else {
            return null;
        }
    }

    public Long hset(String key, String field, String value) {
        if (!this.use) {
            return 0L;
        } else if (!StringUtils.isBlank(key) && !StringUtils.isBlank(field) && !StringUtils.isBlank(value)) {
            ShardedJedis shardedJedis = null;
            Long result = 0L;
            Long var7;
            try {
                shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
                result = shardedJedis.hset(key, field, value);
                shardedJedis.expire(key, this.defaultExpireTime);
                return result;
            } catch (Exception var11) {
                log.error("redis操作发生异常", var11);
                var7 = 0L;
            } finally {
                if (shardedJedis != null) {
                    this.shardedJedisPool.returnResource(shardedJedis);
                }
            }
            return var7;
        } else {
            return 0L;
        }
    }

    public void sadd(String key, String... member) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.sadd(key, member);
            return;
        } catch (Exception var8) {
            log.error("redis操作发生异常", var8);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public List<String> hmget(String key, String... fields) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            return shardedJedis.hmget(key, fields);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
            return Collections.emptyList();
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public boolean hexists(String key, String field) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            return shardedJedis.hexists(key, field);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
            return false;
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public void hmset(String key, Map map) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            shardedJedis.hmset(key, map);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public Set<String> zrevrange(String key, long start, long end) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            return shardedJedis.zrevrange(key, start, end);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
            return Collections.emptySet();
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            return shardedJedis.zrevrangeWithScores(key, start, end);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
            return Collections.emptySet();
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

    public String getString(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = (ShardedJedis) this.shardedJedisPool.getResource();
            return shardedJedis.get(key);
        } catch (Exception ex) {
            log.error("redis操作发生异常", ex);
            return "";
        } finally {
            if (shardedJedis != null) {
                this.shardedJedisPool.returnResource(shardedJedis);
            }
        }
    }

}

