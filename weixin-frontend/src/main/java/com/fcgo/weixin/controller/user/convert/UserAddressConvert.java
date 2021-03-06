package com.fcgo.weixin.controller.user.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.UserAddressDTO;
import com.fcgo.weixin.persist.po.UserAddressPO;

@Service
public class UserAddressConvert implements Convert<UserAddressPO, UserAddressDTO> {

    @Override
    public UserAddressDTO convertToDTO(UserAddressPO domain) {
        UserAddressDTO userAddr = new UserAddressDTO();
        userAddr.setArea(domain.getArea());
        userAddr.setCity(domain.getCity());
        userAddr.setContactNum(domain.getContactNum());
        userAddr.setDetailAddr(domain.getDetailAddr());
        userAddr.setId(domain.getId());
        userAddr.setIsDefault(domain.getIsDefault());
        userAddr.setProvince(domain.getProvince());
        userAddr.setReceiverName(domain.getReceiverName());
        userAddr.setUserId(domain.getUserId());
        return userAddr;
    }

    @Override
    public UserAddressPO convertToDomain(UserAddressDTO client) {
        UserAddressPO userAddr = new UserAddressPO();
        userAddr.setArea(client.getArea());
        userAddr.setCity(client.getCity());
        userAddr.setContactNum(client.getContactNum());
        userAddr.setDetailAddr(client.getDetailAddr());
        userAddr.setId(client.getId());
        userAddr.setIsDefault(client.getIsDefault());
        userAddr.setProvince(client.getProvince());
        userAddr.setReceiverName(client.getReceiverName());
        userAddr.setUserId(client.getUserId());
        userAddr.setAreacode(client.getAreaCode());
        return userAddr;
    }

    @Override
    public List<UserAddressDTO> convertCollectionToDTO(List<UserAddressPO> domains) {
        List<UserAddressDTO> list = new ArrayList<UserAddressDTO>();
        for (UserAddressPO userAddressPO : domains) {
            list.add(this.convertToDTO(userAddressPO));
        }
        return list;
    }

    @Override
    public List<UserAddressPO> convertCollectionToDomain(List<UserAddressDTO> clients) {
        List<UserAddressPO> list = new ArrayList<UserAddressPO>();
        for (UserAddressDTO userAddress : clients) {
            list.add(this.convertToDomain(userAddress));
        }
        return list;
    }

}
