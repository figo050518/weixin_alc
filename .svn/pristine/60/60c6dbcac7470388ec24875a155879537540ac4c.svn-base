package com.fcgo.weixin.common.dto;

/**
 * 这个dto是登录之后放session的用户信息
 * 
 * @author xiahanxzh
 */
public class BaseSessionUserDTO implements java.io.Serializable {

    private final String telephone;

    private final String nickName;

    private final Boolean isBuyer;

    private final Boolean isSeller;

    private final int userId;

    private final int shopId;

    public BaseSessionUserDTO(String telephone, String nickName, Boolean isBuyer, Boolean isSeller, int userId,
            int shopId) {
        this.telephone = telephone;
        this.nickName = nickName;
        this.isBuyer = isBuyer;
        this.isSeller = isSeller;
        this.userId = userId;
        this.shopId = shopId;
    }

    public int getShopId() {
        return shopId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNickName() {
        return nickName;
    }

    public Boolean getIsBuyer() {
        return isBuyer;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public int getUserId() {
        return userId;
    }

}
