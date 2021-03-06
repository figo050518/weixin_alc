package com.fcgo.weixin.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fcgo.weixin.application.user.UserAddressService;
import com.fcgo.weixin.application.user.UserInfoService;
import com.fcgo.weixin.common.dto.BaseSessionUserDTO;
import com.fcgo.weixin.common.util.HttpSessionProvider;
import com.fcgo.weixin.controller.user.convert.UserAddressConvert;
import com.fcgo.weixin.dto.UserAddressDTO;
import com.fcgo.weixin.persist.po.UserAddressPO;

/**
 * @author 用户地址管理
 */
@Controller
@RequestMapping("/uc/user/address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserAddressConvert userAddressConvert;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取当前用户的地址列表
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        List<UserAddressPO> userAddress = userAddressService.findList(baseSessionUserDTO.getUserId());
        model.addAttribute("addrList", userAddressConvert.convertCollectionToDTO(userAddress));
        if(baseSessionUserDTO.getIsSeller()){
            return "/user/addressMange";
        }
        if(baseSessionUserDTO.getIsBuyer()){
            return "/user/addressMangeBuyer";
        }
        return "/user/addressMange";
    }

    /**
     * @Title: add
     * @Description: 新增地址
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/preAdd", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        return "/user/addressForm";
    }

    /**
     * @Description: 收货地址设为默认
     * @param @param id
     * @param @param model
     * @param @param request
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/setDefault", method = RequestMethod.GET)
    public @ResponseBody
    int addrSetDefault(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        if (baseSessionUserDTO == null) {
            return 0;
        }
        return userAddressService.setDefault(id, baseSessionUserDTO.getUserId());
    }

    /**
     * @Title: add
     * @Description: 新增地址
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(UserAddressDTO userAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserAddressPO userAddressPo = userAddressConvert.convertToDomain(userAddress);
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        userAddressPo.setUpdateName(baseSessionUserDTO.getNickName());
        userAddressPo.setUserId(baseSessionUserDTO.getUserId());
        userAddressService.save(userAddressPo, baseSessionUserDTO.getIsSeller());
        return "success";
    }

    /**
     * @Description:编辑地址
     * @param @param userAddrId userAddressID
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/preEdit", method = RequestMethod.GET)
    public String preEdit(@RequestParam("userAddrId") Integer userAddrId, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        UserAddressPO userAddress = userAddressService.findById(userAddrId);
        model.addAttribute("userAddress", userAddressConvert.convertToDTO(userAddress));
        return "/user/editForm";
    }

    /**
     * @Description:编辑地址
     * @param @param userAddrId userAddressID
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam("userAddrId") Integer userAddrId, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        userAddressService.deleteById(userAddrId);
        return "success";
    }

    /**
     * @Description: edit
     * @param @param userAddress
     * @param @param request
     * @param @param response
     * @param @param model
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    String edit(UserAddressDTO userAddress, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserAddressPO userAddressPo = userAddressConvert.convertToDomain(userAddress);
        BaseSessionUserDTO baseSessionUserDTO =
                (BaseSessionUserDTO) HttpSessionProvider.getAttribute(request, "session_attr_user");
        userAddressPo.setUpdateName(baseSessionUserDTO.getNickName());
        userAddressPo.setUserId(baseSessionUserDTO.getUserId());
        userAddressService.save(userAddressPo, baseSessionUserDTO.getIsSeller());
        return "success";
    }

}
