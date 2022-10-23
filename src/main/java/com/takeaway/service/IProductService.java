package com.takeaway.service;

import com.takeaway.entity.Product;

import java.util.List;

/**
 * @author kafka
 */
public interface IProductService {
    /**
     * 显示最新八个商品
     * @return
     */
    List<Product> findProduct();

    /**
     * 显示热销前四名的商品
     * @return
     */
    List<Product> HotProduct();

    /**
     * 商品的详情
     * @param pid 商品id
     * @return
     */
    Product findPid(Integer pid);

    /**
     * 查询一共有多少页数，传给前端
     * @return
     */
    Integer pageLimit();


    /**
     * 分页显示商品
     * @param currentPage
     * @return
     */
    List<Product>  showPages (Integer currentPage);
}
