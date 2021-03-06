package com.fcgo.weixin.controller.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.finance.FinanceService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.common.util.OrderNumberUtil;
import com.fcgo.weixin.controller.finance.covert.FinanceCardConvert;
import com.fcgo.weixin.dto.BindCardDTO;
import com.fcgo.weixin.persist.po.FinanceBankCardPO;
import com.fcgo.weixin.persist.po.FinanceWithdrawApplyPO;

/**
 * 卖家财务相关
 * 
 * @author xiahanxzh
 */
@RequestMapping(value = "/uc/finance")
@Controller
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @Autowired
    private FinanceCardConvert financeCardConvert;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 首页
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        // 查询账户总额
        model.addAttribute("balance", financeService.getSellerbalance(baseSessionUserDTO.getUserId()));
        // 查询已经提现的总额
        model.addAttribute("withDrawApplyAmount", financeService.getWithDrawApplyAmount(baseSessionUserDTO.getUserId()));
        return "/finance/index";
    }

    /**
     * 初始化提现页面
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/withdrwaApplyInit", method = RequestMethod.GET)
    public String withdrwaApplyInit(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        model.addAttribute("balance", financeService.getSellerbalance(baseSessionUserDTO.getUserId()));
        model.addAttribute("userId", baseSessionUserDTO.getUserId());
        model.addAttribute("telephone", baseSessionUserDTO.getTelephone());
        model.addAttribute("cardList", financeService.getBankCardList(baseSessionUserDTO.getUserId()));
        return "/finance/applayWithDraw";
    }

    /**
     * 提交提现申请
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/withdrwaApply", method = RequestMethod.POST)
    public @ResponseBody
    String withdrwaApply(HttpServletRequest request, HttpServletResponse response, Model model, String cardId,
            String withdrawAmount, String telephone, String code) {
        String message = userInfoService.validateMsgCode(telephone, code);
        if ("success".equals(message)) {
            BaseSessionUserDTO baseSessionUserDTO =
                    (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
            FinanceWithdrawApplyPO financeWithdrawApplyPO = new FinanceWithdrawApplyPO();
            financeWithdrawApplyPO.setCreateTime(new Date());
            financeWithdrawApplyPO.setCreateName(baseSessionUserDTO.getNickName());
            financeWithdrawApplyPO.setUpdateName(baseSessionUserDTO.getNickName());
            financeWithdrawApplyPO.setUpdateTime(new Date());
            financeWithdrawApplyPO.setAuditState(1);
            financeWithdrawApplyPO.setIsDelete(0);
            financeWithdrawApplyPO.setSellerId(baseSessionUserDTO.getUserId());
            financeWithdrawApplyPO.setShopId(baseSessionUserDTO.getShopId());
            financeWithdrawApplyPO.setWithdrawNum(OrderNumberUtil.generateOrderNo());
            // 根据ID查询银行卡信息
            FinanceBankCardPO financeBankCardPO = financeService.getFinanceBankCard(Integer.valueOf(cardId));
            if (financeBankCardPO != null) {
                financeWithdrawApplyPO.setCardNum(financeBankCardPO.getCardNum());
                financeWithdrawApplyPO.setCardOwnerName(financeBankCardPO.getOwnerName());
                financeWithdrawApplyPO.setCardType(financeBankCardPO.getBankName());
            }
            financeWithdrawApplyPO.setWithdrawAmount(new BigDecimal(withdrawAmount));
            financeService.addWithDrawApply(financeWithdrawApplyPO);
        }
        return "success";
    }

    /**
     * 添加银行卡
     * 
     * @param request
     * @param response
     * @param model
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/addCard", method = RequestMethod.GET)
    public @ResponseBody
    String addCard(HttpServletRequest request, HttpServletResponse response, Model model, BindCardDTO bindCardDTO) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        FinanceBankCardPO financeBankCardPO = financeCardConvert.convertToDomain(bindCardDTO);
        financeBankCardPO.setCreateName(baseSessionUserDTO.getNickName());
        financeBankCardPO.setUpdateName(baseSessionUserDTO.getNickName());
        financeBankCardPO.setCreateTime(new Date());
        financeBankCardPO.setUpdateTime(new Date());
        financeBankCardPO.setSellerId(baseSessionUserDTO.getUserId());
        financeBankCardPO.setShopId(baseSessionUserDTO.getShopId());
        boolean result = financeService.addCard(financeBankCardPO);
        if (result) {
            financeBankCardPO.setCardNum(financeBankCardPO.getCardNum().substring(
                    financeBankCardPO.getCardNum().length() - 3));
            BindCardDTO bindCardDTOJson = new BindCardDTO();
            bindCardDTOJson.setCardNumber(financeBankCardPO.getCardNum());
            bindCardDTOJson.setCardName(financeBankCardPO.getBankName());
            bindCardDTOJson.setId(financeBankCardPO.getId());
            JSONObject jsonObject = JSONObject.fromObject(bindCardDTOJson);
            return jsonObject.toString();
        }
        return "error";

    }

    /**
     * 删除银行卡
     * 
     * @param request
     * @param response
     * @param model
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/deleteCard", method = RequestMethod.GET)
    public @ResponseBody
    String deleteCard(HttpServletRequest request, HttpServletResponse response, Model model, String cardId) {
        boolean result = financeService.deleteCard(Integer.valueOf(cardId));
        if (result) {
            return "success";
        }
        return "error";

    }

    /**
     * 银行卡列表
     * 
     * @param request
     * @param response
     * @param model
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/cardList", method = RequestMethod.GET)
    public String cardList(HttpServletRequest request, HttpServletResponse response, Model model) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        model.addAttribute("cardList", financeService.getBankCardList(baseSessionUserDTO.getUserId()));
        return "/finance/mybankCard";

    }

    /**
     * 流水明细页面初始化
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/transaction/init", method = RequestMethod.GET)
    public String transactionListInit(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/finance/transactionList";
    }

    /**
     * 提下记录页面初始化
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/withDrawApply/init", method = RequestMethod.GET)
    public String withDrawApplyInit(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/finance/withDrawApplyList";
    }

    /**
     * 流水明细
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
    public @ResponseBody
    Map transactionList(HttpServletRequest request, HttpServletResponse response, Model model, String pageIndex) {
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        Map result = new HashMap();
        result.put("totalCount", financeService.countTranctaionList(baseSessionUserDTO.getUserId()));
        result.put("transactionList", financeService.getTranctaionList(baseSessionUserDTO.getUserId(), page));
        return result;
    }

    /**
     * 提现记录明细
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/withdrawApply/list", method = RequestMethod.GET)
    public @ResponseBody
    Map withdrawApplyList(HttpServletRequest request, HttpServletResponse response, Model model, String pageIndex) {
        Page page = new Page();
        if (pageIndex == null || pageIndex == "") {
            pageIndex = "1";
        }
        page.setPageIndex(Integer.valueOf(pageIndex));
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        Map result = new HashMap();
        result.put("totalCount", financeService.countWithDrawApplyList(baseSessionUserDTO.getUserId()));
        result.put("withDrawApplyList", financeService.getWithDrawApplyList(baseSessionUserDTO.getUserId(), page));
        return result;
    }
}
