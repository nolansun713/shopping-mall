package com.takeaway.service.impl;

import com.sun.corba.se.impl.protocol.AddressingDispositionException;
import com.takeaway.entity.Address;
import com.takeaway.mapper.AddressMapper;
import com.takeaway.service.IAddressService;
import com.takeaway.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.util.Date;
import java.util.List;

/**
 *收货地址
 * @author kafka
 */
 @Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Value("${user.address.max-count}")
    private int maxCount;
    /**
     * 添加收货地址
     *
     * @param uid
     * @param username
     * @param address
     * @return
     */
    @Override
    public void addAddress(Integer uid, String username, Address address) {
       Integer row= addressMapper.countByUid(uid);
       if(row>maxCount){
           throw new AddressCountLimitException("收货地址超过限制");
       }
        address.setUid(uid);
        //默认收货地址
        Integer isDefault=row == 0 ? 1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer result =addressMapper.addAddress(address);
        if(result!=1){
            throw new InsertException("添加收货地址失败");
        }
    }

    /**
     * 显示收货地址
     *
     * @param uid
     * @return
     */
    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> addressList = addressMapper.findByUid(uid);
        return addressList;
    }

    /**
     * 设置默认收货地址
     *
     * @param uid
     * @param aid
     * @param username
     */
    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
       // 1.查询收货地址是否存在
        Address row = addressMapper.findByAid(aid);
        if(row==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 2.校验账号和aid
        if(!row.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        // 3.将所有的地址设置为非默认
        Integer result= addressMapper.updateNonDefaultByUid(uid);
        if(result<1){
            throw new UpdateException("设置默认收货地址异常");
        }
        // 4.设置默认收货地址
        result=addressMapper.setDefault(aid,username,new Date());
        if(result!=1){
            throw new UpdateException("设置收货地址失败");
        }
    }

    /**
     * 根据aid删除收货地址
     *
     * @param uid
     * @param aid
     */
    @Override
    public void deleteByAid(Integer uid, Integer aid,String username) {
       Address address= addressMapper.findByAid(aid);
       if(address == null){
            throw new AddressNotFoundException("收货地址不存在");
       }
       if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
       }
       Integer row = addressMapper.deleteByAid(aid);
       if(row!=1){
           throw new UpdateException("删除失败");
       }
       if(address.getIsDefault()!=0){
           //如果删除的是默认地址，则设置最新修改的地址
             Address result=  addressMapper.findLastModified(uid);
            Integer a= addressMapper.setDefault(result.getAid(),username,new Date());
            if(a!=1){
                throw  new UpdateException("更新收货地址异常");
            }
       }
    }

    /**
     * 修改收货地址
     *
     * @param uid
     * @param aid
     * @param username
     */
    @Override
    public void updateAddress(Integer uid, Integer aid, String username,Address address) {
        Address result= addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer row =  addressMapper.updateAddress(aid,address.getName(),address.getProvinceName(),
                address.getCityName(),address.getAreaName(),address.getAddress(),address.getZip(),
                address.getPhone(),address.getModifiedUser(),address.getModifiedTime());
        if(row!=1){
            throw new UpdateException("修改收货地址失败");
        }
    }

    /**
     * 查询aid收货地址
     *
     * @param aid
     * @return
     */
    @Override
    public Address getByAid(Integer aid) {
        Address row = addressMapper.findByAid(aid);
        if(row==null){
            throw  new AddressNotFoundException("收货地址不存在");
        }
        return row;
    }
}
