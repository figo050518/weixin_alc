/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.persist.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.plugins.RenameExampleClassPlugin;

/**
 * ReplaceExamplePlugin.java
 * 
 * @author zhangxu
 */
public class ReplaceExamplePlugin extends RenameExampleClassPlugin {
    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);

        String po = introspectedTable.getBaseRecordType();
        introspectedTable.setBaseRecordType(po + context.getProperty("poSuffix"));

        String dao = introspectedTable.getDAOInterfaceType();
        dao = dao.substring(0, dao.lastIndexOf("."));

        String example = introspectedTable.getExampleType();
        example = example.substring(example.lastIndexOf("."), example.length());

        introspectedTable.setExampleType(dao + ".criteria" + example);

        String myBatisDaoName = introspectedTable.getMyBatis3JavaMapperType();
        myBatisDaoName = myBatisDaoName.replaceAll("\\.(?=[^.]+$)", ".I");
        introspectedTable.setMyBatis3JavaMapperType(myBatisDaoName);

        introspectedTable.setUpdateByExampleSelectiveStatementId(introspectedTable
                .getUpdateByExampleSelectiveStatementId().replace("Example", "Criteria"));
        introspectedTable.setUpdateByExampleStatementId(introspectedTable.getUpdateByExampleStatementId().replace(
                "Example", "Criteria"));
        introspectedTable.setUpdateByExampleWithBLOBsStatementId(introspectedTable
                .getUpdateByExampleWithBLOBsStatementId().replace("Example", "Criteria"));

        introspectedTable.setDeleteByExampleStatementId(introspectedTable.getDeleteByExampleStatementId().replace(
                "Example", "Criteria"));
        introspectedTable.setSelectByExampleStatementId(introspectedTable.getSelectByExampleStatementId().replace(
                "Example", "Criteria"));
        introspectedTable.setSelectByExampleWithBLOBsStatementId(introspectedTable
                .getSelectByExampleWithBLOBsStatementId().replace("Example", "Criteria"));

        introspectedTable.setCountByExampleStatementId(introspectedTable.getCountByExampleStatementId().replace(
                "Example", "Criteria"));
    }
}
