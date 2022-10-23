package com.takeaway.mapper;

import com.takeaway.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**
 * 收货地址
 * @author kafka
 */
@Mapper
public interface AddressMapper {
    /**
     * 添加收货地址
     * @param address
     * @return
     */
    Integer addAddress (Address address);
    /**
     *查询收货地址有多少个
     * @param uid
     * @return
     */
    Integer  countByUid(Integer uid);

    /**
     * 根据uid查询用户收货地址
     * @param uid
     * @return
     */
    List<Address>  findByUid(Integer uid);

    /**
     * 将所有收货地址设为非默认
     * @param uid
     * @return
     */
    Integer updateNonDefaultByUid(Integer uid);
    /**
     * 将指定的收货地址设置为默认值
     * @param aid
     * @return
     */
    Integer setDefault(@Param("aid") Integer aid,
                       @Param("modifiedUser") String modifiedUser,
                       @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据aid查询指定收货地址是否存在,以及操作权限
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);
    /**
     * 用户最后修改的收货地址
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);

    /**
     * 修改收货地址
     * @param aid
     * @param address
     * @return
     */
    Integer updateAddress(@Param("aid") Integer aid,
                          @Param("name") String name,
                          @Param("provinceName") String  provinceName,
                          @Param("cityName") String cityName,
                          @Param("areaName") String areaName,
                          @Param("address") String address,
                          @Param("zip")String zip,
                          @Param("phone")String phone,
                          @Param("modifiedUser")String modifiedUser,
                          @Param("modifiedTime") Date modifiedTime);
}
