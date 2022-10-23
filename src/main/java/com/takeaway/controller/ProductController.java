package com.takeaway.controller;

import com.takeaway.entity.Product;
import com.takeaway.service.IProductService;
import com.takeaway.until.DataRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kafka
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @RequestMapping("newList")
    public DataRes<List<Product>> findList(){
       List<Product> result= productService.findProduct();
       return new DataRes<>(OK,result);
    }
    @RequestMapping("hotList")
    public DataRes<List<Product>> hotList(){
        List<Product> result= productService.HotProduct();
        return new DataRes<>(OK,result);
    }
    @RequestMapping("{pid}/product")
    public DataRes<Product> findByPid(@PathVariable("pid") Integer pid){
        Product result = productService.findPid(pid);
        return new DataRes<>(OK,result);
    }
    @RequestMapping("toPage")
    public DataRes<Integer> toPages(){
        //返回一共有多少页数据
       Integer row= productService.pageLimit();
        return new DataRes<>(OK,row);
    }
    @GetMapping("{sid}/showProductList")
    public DataRes<List<Product>> PageList(@PathVariable("sid") Integer sid){
        if(sid==null){
            sid=1;
        }
        List<Product> result = productService.showPages(sid);
        return new DataRes<>(OK,result);
    }
}
