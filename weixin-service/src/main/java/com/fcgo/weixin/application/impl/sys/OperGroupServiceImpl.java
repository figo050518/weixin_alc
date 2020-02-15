package com.fcgo.weixin.application.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcgo.weixin.application.sys.OperGroupService;
import com.fcgo.weixin.common.dto.Page;
import com.fcgo.weixin.persist.generate.criteria.FunctionAuthCriteria;
import com.fcgo.weixin.persist.generate.criteria.OperGroupCriteria;
import com.fcgo.weixin.persist.generate.criteria.OperGroupRelationCriteria;
import com.fcgo.weixin.persist.po.OperGroupPO;

@Service
@Transactional
public class OperGroupServiceImpl implements OperGroupService {
	@Autowired
	private IOperGroupDAO operGroupDAO;
	
	@Autowired
	private IOperGroupRelationDAO operGroupRelationDAO;
	
	@Autowired
	private IFunctionAuthDAO functionAuthDAO;

	@Override
	public OperGroupPO queryOperGroupById(Integer id) {
		// TODO Auto-generated method stub
		return operGroupDAO.selectByPrimaryKey(id);
	}

	@Override
	public Page queryOperGroupListPage(OperGroupPO operGroupPO, int pageIndex) {
		// TODO Auto-generated method stub

		//查询记录
	    Page page = new Page();
	    page.setPageSize(10);
        // 更新当前页码
        if (pageIndex >= 1) {
            page.setPageIndex(pageIndex);
        }
        
        Map parm = new HashMap();
		if(StringUtils.isNotEmpty(operGroupPO.getGroupName())){
			parm.put("groupName", "%"+operGroupPO.getGroupName()+"%");
		}
        parm.put("startPage", (page.getPageIndex() - 1) * page.getPageSize());
        parm.put("pageSize", page.getPageSize());
        // 获取主编分页数据
        List<OperGroupPO> operGroupPOs = operGroupDAO.getOperGroupListPage(parm);
        // 查询总数
        OperGroupCriteria operGroupCriteria = new OperGroupCriteria();
        if(StringUtils.isNotEmpty(operGroupPO.getGroupName())){
        	operGroupCriteria.createCriteria().andGroupNameLike("%"+operGroupPO.getGroupName()+"%");
        }
        int count = operGroupDAO.countByCriteria(operGroupCriteria);
        // 组装数据
        // 返回数据集合
        page.setRow(operGroupPOs);
        page.setRecords(count);
        return page;
	}

	@Override
	public boolean saveOperGroup(OperGroupPO operGroupPO) {
		// TODO Auto-generated method stub
		if(operGroupPO.getId() == null ){
			operGroupDAO.insert(operGroupPO);
		}else{
			operGroupDAO.updateByPrimaryKeySelective(operGroupPO);
		}
		return true;
	}

	@Override
	public boolean deleteOperGroupById(Integer id) {
 		//删除 用户组-用户关系
		OperGroupRelationCriteria groupRelationCriteria = new OperGroupRelationCriteria();
		groupRelationCriteria.createCriteria().andGroupIdEqualTo(id);
		
		FunctionAuthCriteria functionAuthCriteria = new FunctionAuthCriteria();
		functionAuthCriteria.createCriteria().andGroupIdEqualTo(id);
		
		operGroupRelationDAO.deleteByCriteria(groupRelationCriteria);
		functionAuthDAO.deleteByCriteria(functionAuthCriteria);
		
		operGroupDAO.deleteByPrimaryKey(id);
		return true;
	}
	
}
