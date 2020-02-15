package com.fcgo.weixin.application.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.user.ISellerCustomerService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.po.SellerCustomerPO;

/**
 * 卖家客户关系
 * 
 * @author Ww
 */
@Service
@Transactional
public class SellerCustomerServiceImpl implements ISellerCustomerService {
    @Autowired
    private ISellerCustomerDAO sellerCustomerDAO;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Page findCustomeLsitBySellerId(int sellerId, int pageIndex) {
        Page page = new Page();
        if (pageIndex > 1) {
            page.setPageIndex(pageIndex);
        }
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("endPage", page.getPageIndex() * page.getPageSize());
        List<SellerCustomerPO> sellerCustomerPOs = sellerCustomerDAO.findCustomeLsitBySellerId(parm);
        page.setRow(sellerCustomerPOs);
        page.setRecords(sellerCustomerDAO.findCustomeByCount(sellerId));
        return page;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public SellerCustomerPO findCustomeByDetail(int sellerId, int userId) {
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        parm.put("userId", userId);
        return sellerCustomerDAO.findCustomeByDetail(parm);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public int findCountOrderState(int userId, int sellerId, int orderState) {
        int count = 0;
        Map parm = new HashMap();
        parm.put("sellerId", sellerId);
        parm.put("userId", userId);
        parm.put("orderState", orderState);
        count = sellerCustomerDAO.findCountOrderState(parm);
        return count;
    }

}
