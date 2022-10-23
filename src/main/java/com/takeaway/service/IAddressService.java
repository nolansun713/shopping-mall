package com.takeaway.service;

import com.takeaway.entity.Address;

import java.util.List;

/**
 * 收货地址
 * @author kafka
 */
public interface IAddressService {
    /**
     * 添加收货地址
     * @param uid
     * @param username
     * @param address
     * @return
     */
    void  addAddress(Integer uid ,String username,Address address);

    /**
     * 显示收货地址
     * @param uid
     * @return
     */
    List<Address>  getByUid(Integer uid);

    /**
     *  设置默认收货地址
     * @param uid
     * @param aid
     * @param username
     */
    void  setDefault(Integer uid,Integer aid,String username);

    /**
     * 根据aid删除收货地址
     * @param uid
     * @param aid
     * @param username
     */
    void  deleteByAid(Integer uid,Integer aid,String username);

    /**
     * 修改收货地址
     * @param uid
     * @param aid
     * @param username
     * @param address
     */
    void  updateAddress(Integer uid,Integer aid,String username,Address address);

    /**
     * 查询aid收货地址
     * @param aid
     * @return
     */
    Address getByAid(Integer aid);
}
