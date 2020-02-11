package com.fcgo.weixin.controller.finance.covert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fcgo.weixin.convert.Convert;
import com.fcgo.weixin.dto.BindCardDTO;
import com.fcgo.weixin.persist.po.FinanceBankCardPO;

@Service
public class FinanceCardConvert implements Convert<FinanceBankCardPO, BindCardDTO> {

    @Override
    public BindCardDTO convertToDTO(FinanceBankCardPO domain) {
        return null;
    }

    @Override
    public FinanceBankCardPO convertToDomain(BindCardDTO bindCardDTO) {
        FinanceBankCardPO financeBankCardPO = new FinanceBankCardPO();
        financeBankCardPO.setBankName(bindCardDTO.getCardName());
        financeBankCardPO.setCardNum(bindCardDTO.getCardNumber());
        financeBankCardPO.setOwnerName(bindCardDTO.getOwnerName());
        financeBankCardPO.setIsDelete(0);
        return financeBankCardPO;
    }

    @Override
    public List<BindCardDTO> convertCollectionToDTO(List<FinanceBankCardPO> domains) {
        return null;
    }

    @Override
    public List<FinanceBankCardPO> convertCollectionToDomain(List<BindCardDTO> clients) {
        return null;
    }

}
