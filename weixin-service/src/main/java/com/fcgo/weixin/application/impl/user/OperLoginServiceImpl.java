package com.fcgo.weixin.application.impl.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.user.OperLoginService;
import com.fcgo.weixin.common.codec.MD5EncrypterUtil;
import com.fcgo.weixin.persist.dao.IOperInfoDAO;
import com.fcgo.weixin.persist.dao.IOperLoginDAO;
import com.fcgo.weixin.persist.generate.criteria.OperInfoCriteria;
import com.fcgo.weixin.persist.generate.criteria.OperLoginCriteria;
import com.fcgo.weixin.persist.po.OperInfoPO;
import com.fcgo.weixin.persist.po.OperLoginPO;
import com.fcgo.weixin.persist.po.UserLoginPO;

@Service
@Transactional
public class OperLoginServiceImpl implements OperLoginService {
	@Autowired
	private IOperLoginDAO operLoginDAO;

	@Override
	public boolean validateLogin(String userId, String password) {
		// TODO Auto-generated method stub
		
		  OperLoginCriteria operLoginCriteria = new OperLoginCriteria();
		  operLoginCriteria.createCriteria().andOperIdEqualTo(userId)
		  .andPasswordEqualTo(MD5EncrypterUtil.md5PwdEncrypt(password))
		  .andIsDeleteEqualTo(0);
		  
	        List<OperLoginPO> operLoginPOs = operLoginDAO.selectByCriteria(operLoginCriteria);
	        if (CollectionUtils.isNotEmpty(operLoginPOs)) {
	            return true;
	        }
	        return false;
	}

	@Override
	public boolean updateOperLogin(OperLoginPO operLoginPO) {
		 int cnt = operLoginDAO.updateByPrimaryKey(operLoginPO);
	     return true;
	}
	
	@Override
	public OperLoginPO getOperLoginById(String userId) {
		 OperLoginCriteria operLoginCriteria = new OperLoginCriteria();
		 operLoginCriteria.createCriteria().andOperIdEqualTo(userId);
		 List<OperLoginPO> operLoginPOs = operLoginDAO.selectByCriteria(operLoginCriteria);
		 if(CollectionUtils.isEmpty(operLoginPOs)){
			 return null;
		 }
	     return operLoginPOs.get(0);
	}

	@Override
	public OperLoginPO saveOperLogin(OperLoginPO operLoginPO) {
		// TODO Auto-generated method stub
		
		if(operLoginPO.getId() != null){
			operLoginDAO.updateByPrimaryKeySelective(operLoginPO);
			OperLoginCriteria operLoginCriteria = new OperLoginCriteria();
			operLoginCriteria.createCriteria().andOperIdEqualTo(operLoginPO.getOperId());
			List<OperLoginPO> operLoginPOs = operLoginDAO.selectByCriteria(operLoginCriteria);
			return operLoginPOs.get(0);
		}else{
			operLoginDAO.insert(operLoginPO);
			return operLoginPO;
		}
		
	}

}
