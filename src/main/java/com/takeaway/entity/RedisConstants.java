package com.takeaway.entity;

/**
 * @author kafka
 */
public class RedisConstants {
    /**
     * 商品的Key前缀
     */
    public static final String CACHE_PRODUCT_KEY="cache:product:";
    public static final String CACHE_HOT_PRODUCT_KEY="cache:hotProduct:";
    /**商品的redis有效期**/
    public static final Long CACHE_SHOP_TIME=30L;
    /**分页查询redis 的 key**/
    public static final String CACHE_SHOP_Limit="cache:shopLimit:";
    /**验证码的key**/
    public static final String CACHE_Code_KEY="cache:code:";
    /**设置验证码的时间**/
    public static final Long LOGIN_CODE_TTL = 2L;
}
