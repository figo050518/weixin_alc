package com.fcgo.weixin.application.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.sys.OperInfoService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.generate.criteria.OperInfoCriteria;
import com.fcgo.weixin.persist.po.OperInfoPO;

@Service
@Transactional
public class OperInfoServiceImpl implements OperInfoService {
	@Autowired
	private IOperInfoDAO operInfoDAO;
	
	@Override
	public OperInfoPO queryOperInfoById(String userId) {
		// TODO Auto-generated method stub
		OperInfoCriteria operInfoCriteria = new OperInfoCriteria();
		operInfoCriteria.createCriteria().andOperIdEqualTo(userId);
		List<OperInfoPO> operInfoPOs = operInfoDAO.selectByCriteria(operInfoCriteria);
		if(CollectionUtils.isEmpty(operInfoPOs)){
			return null;
		}
		return operInfoPOs.get(0);
	}
	@Override
	public OperInfoPO queryValidOperInfoById(String userId) {
		// TODO Auto-generated method stub
		OperInfoCriteria operInfoCriteria = new OperInfoCriteria();
		operInfoCriteria.createCriteria().andOperIdEqualTo(userId)
		.andIsDeleteEqualTo(0);
		List<OperInfoPO> operInfoPOs = operInfoDAO.selectByCriteria(operInfoCriteria);
		if(CollectionUtils.isEmpty(operInfoPOs)){
			return null;
		}
		return operInfoPOs.get(0);
	}
	@Override
	public Page queryOperInfoListPage(OperInfoPO operInfoPO,
			int pageIndex) {
		
			//查询记录
		    Page page = new Page();
	        // 更新当前页码
	        if (pageIndex >= 1) {
	            page.setPageIndex(pageIndex);
	        }
	        
	        Map parm = new HashMap();
			if(StringUtils.isNotEmpty(operInfoPO.getOperName())){
				parm.put("operName", "%"+operInfoPO.getOperName()+"%");
			}
	        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
	        parm.put("pageSize", page.getPageSize());
	        // 获取主编分页数据
	        List<OperInfoPO> operInfoPOs = operInfoDAO.getOperInfoListPage(parm);
	        // 查询总数
	        OperInfoCriteria operInfoCriteria = new OperInfoCriteria();
	        if(StringUtils.isNotEmpty(operInfoPO.getOperName())){
	        	operInfoCriteria.createCriteria().andOperNameLike("%"+operInfoPO.getOperName()+"%");
	        }
	        int count = operInfoDAO.countByCriteria(operInfoCriteria);
	        // 组装数据
	        // 返回数据集合
	        page.setRow(operInfoPOs);
	        page.setRecords(count);
	        return page;
	}

	@Override
	public boolean updateOperInfo(OperInfoPO operInfoPO) {
		 int cnt = operInfoDAO.updateByPrimaryKey(operInfoPO);
	     return cnt > 0;
	}
	@Override
	public boolean validateUniqueOperInfo(OperInfoPO operInfoPO) {
		OperInfoCriteria operInfoCriteria = new OperInfoCriteria();
		if(operInfoPO.getId() != null){
			operInfoCriteria.createCriteria().andIdEqualTo(operInfoPO.getId());
		}
		if(StringUtils.isNotEmpty(operInfoPO.getOperId())){
			operInfoCriteria.createCriteria().andOperIdEqualTo(operInfoPO.getOperId());
		}
		if(StringUtils.isNotEmpty(operInfoPO.getTelNum())){
			operInfoCriteria.createCriteria().andTelNumEqualTo(operInfoPO.getTelNum());
		}
		if(StringUtils.isNotEmpty(operInfoPO.getOperEmail())){
			operInfoCriteria.createCriteria().andOperEmailEqualTo(operInfoPO.getOperEmail());
		}
		int count = operInfoDAO.countByCriteria(operInfoCriteria);
		
		return count > 0;
	}
	@Override
	public OperInfoPO saveOperInfo(OperInfoPO operInfoPO) {
		
		if(operInfoPO.getId() != null){
			operInfoDAO.updateByPrimaryKeySelective(operInfoPO);
			OperInfoCriteria operInfoCriteria = new OperInfoCriteria();
			operInfoCriteria.createCriteria().andOperIdEqualTo(operInfoPO.getOperId());
			List<OperInfoPO> operInfoPOs = operInfoDAO.selectByCriteria(operInfoCriteria);
			return operInfoPOs.get(0);
		}else{
			operInfoDAO.insert(operInfoPO);
			return operInfoPO;
		}
	}
}
