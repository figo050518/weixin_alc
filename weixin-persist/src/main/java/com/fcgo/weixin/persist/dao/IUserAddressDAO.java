package com.fcgo.weixin.persist.dao;


import com.fcgo.weixin.persist.generate.IUserAddressMapper;

public interface IUserAddressDAO extends IUserAddressMapper {

    void deleteById(Integer userAddrId);
}