package com.takeaway.controller.User;

import com.takeaway.controller.BaseController;
import com.takeaway.entity.Address;
import com.takeaway.service.IAddressService;
import com.takeaway.until.DataRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kafka
 */

@RestController
@RequestMapping("addresses")
public class AddressController  extends BaseController {
    @Autowired
    IAddressService addressService;
    @PostMapping("add_address")
    public DataRes<Void> addAddress(Address address, HttpSession session){
        Integer uid=getUidSession(session);
        String username=getUsernameSession(session);
        addressService.addAddress(uid,username,address);
        return new DataRes<>(OK);
    }
    @RequestMapping({"","/"})
    public DataRes<List<Address>> findByUid(HttpSession session){
        Integer uid=getUidSession(session);
        List<Address> addressList = addressService.getByUid(uid);
        return new DataRes<List<Address>>(OK,addressList);
    }
    @RequestMapping("{aid}/set_default")
    public DataRes<Void> setDefault(HttpSession session,@PathVariable("aid") Integer aid){
        Integer uid=getUidSession(session);
        String username=getUsernameSession(session);
        addressService.setDefault(uid,aid,username);
        return new DataRes<>(OK);
    }
    @RequestMapping("{aid}/delete_address")
    public DataRes<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer  uid = getUidSession(session);
        String username=getUsernameSession(session);
        addressService.deleteByAid(uid,aid,username);
        return new DataRes<>(OK);
    }
    @PostMapping("{aid}/updateAddress")
    public DataRes<Void> updateAddress(@PathVariable("aid") Integer aid,Address address,HttpSession session){
        System.out.println(address);
        Integer uid = getUidSession(session);
        String  username=getUsernameSession(session);
        addressService.updateAddress(uid,aid,username,address);
        return new DataRes<>(OK);
    }
    @PostMapping ("{aid}/AidAddress")
    public DataRes<Address> getByAid(@PathVariable("aid") Integer aid){
        Address address = addressService.getByAid(aid);
        return new DataRes<>(OK,address);
    }
}
