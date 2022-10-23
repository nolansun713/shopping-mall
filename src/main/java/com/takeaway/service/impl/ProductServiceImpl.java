package com.takeaway.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.takeaway.entity.Product;
import com.takeaway.mapper.ProductMapper;
import com.takeaway.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.takeaway.entity.RedisConstants.*;

/**
 * 商品的业务层
 * @author kafka
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 显示最新八个商品
     *
     * @return
     */
    @Override
    public List<Product> findProduct() {
        //1.查询redis是否有缓存
        //  CACHE_PRODUCT_KEY 储存到redis的product的key
        String productJson=stringRedisTemplate.opsForValue().get(CACHE_PRODUCT_KEY);
        // 2.判断是否存在
        if(productJson!=null){
            // 3.存在，直接返回
            //将字符串转换为List
            List<Product> result= JSONUtil.toList(productJson,Product.class);
            return  result;
        }
        // 4.不存在，进入数据库查询
        List<Product> result = productMapper.findProduct();
        //5.存入redis缓存中
        String str=JSONUtil.toJsonStr(result);
        stringRedisTemplate.opsForValue().set(CACHE_PRODUCT_KEY,str,CACHE_SHOP_TIME, TimeUnit.MINUTES);
        return result;
    }

    /**
     * 显示热销前四名的商品
     *
     * @return
     */
    @Override
    public List<Product> HotProduct() {
        //1.查询redis是否有缓存
        String hotProductJson=stringRedisTemplate.opsForValue().get(CACHE_HOT_PRODUCT_KEY);
        //2 .判断redis是否空
        if(StrUtil.isNotBlank(hotProductJson)){
            // 3.不为空,直接返回
            List<Product> result=JSONUtil.toList(hotProductJson,Product.class);
            return result;
        }
        // 4.为空，查询数据库返回
        List<Product> result = productMapper.hotProduct();
        //5.将数据缓存到redis
        String str=JSONUtil.toJsonStr(result);
        // 缓存到redis并设置有效期 30分钟
        stringRedisTemplate.opsForValue().set(CACHE_HOT_PRODUCT_KEY,str,CACHE_SHOP_TIME, TimeUnit.MINUTES);
        return result;
    }

    /**
     * 商品的详情
     *
     * @param pid 商品id
     * @return
     */
    @Override
    public Product findPid(Integer pid) {
       Product result= productMapper.findPid(pid);
        return result;
    }

    /**
     * 查询一共有多少页数，传给前端
     *
     * @return
     */
    @Override
    public Integer pageLimit() {
        Integer pageMax= productMapper.sumProduct();
        Integer row= (pageMax % 4==0)? pageMax/4 : (pageMax/4)+1;
        return row;
    }

    /**
     * 分页显示商品
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Product> showPages(Integer pageSize) {
        if(pageSize<1){
            pageSize=1;
        }
        //1.查询redis是否又改数据
        String shopLimitJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_Limit+pageSize);
        //2.判断redis是否为空
        if(StrUtil.isNotBlank(shopLimitJson)){
            // 3.不为空，返回结果
            List<Product> result=JSONUtil.toList(shopLimitJson,Product.class);
            return result;
        }
        // 4.redis没有缓存,则从数据库查询
        Integer pageMax= productMapper.sumProduct();
        Integer row= (pageMax % 4==0)? pageMax/4 : (pageMax/4)+1;
        if(pageSize>row){
            pageSize=row;
        }
        Integer currentPage=(pageSize-1)*4;
        List<Product> result=productMapper.productList(currentPage);
        // 写入redis 缓存，并设置有效时间
        String str=JSONUtil.toJsonStr(result);
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_Limit+pageSize,str,CACHE_SHOP_TIME, TimeUnit.MINUTES);
        return result;
    }
}
