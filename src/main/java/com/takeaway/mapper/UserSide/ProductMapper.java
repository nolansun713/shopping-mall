package com.takeaway.mapper.UserSide;

import com.takeaway.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品
 * @author kafka
 */
@Mapper
public interface ProductMapper {
    /**
     * 显示最新添加的八个商品
     * @return
     */
    List<Product> findProduct();

    /**
     * 查询热销前四名
     * @return
     */
    List<Product> hotProduct();

    /**
     * 商品的详情
     * @param pid
     * @return
     */
    Product findPid(Integer pid);

    /**
     * 每一页展示的数据
     * @param currentPage
     * @return
     */
    List<Product> productList(Integer currentPage);

    /**
     * 统计 商品的总个数
     * @return
     */
    Integer sumProduct();
}
