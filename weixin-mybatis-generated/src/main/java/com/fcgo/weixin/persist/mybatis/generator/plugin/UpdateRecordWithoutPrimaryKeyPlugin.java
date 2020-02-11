package com.fcgo.weixin.persist.mybatis.generator.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 修改默认的update行为，update时不更新主键。(没有考虑set语句只有一行的情况)
 * 
 * @author guonan 2014-9-26
 * @version QB_LV_1014_07
 */
public class UpdateRecordWithoutPrimaryKeyPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 修改updateByCriteria
     */
    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        removeSetPrimaryKeyLine(element, introspectedTable);
        return true;
    }

    /**
     * 修改updateByCriteriaWithBLOBs
     */
    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        removeSetPrimaryKeyLine(element, introspectedTable);
        return true;
    }

    /**
     * 在生成好的update语句中移除set primaryKey=？？？的行
     * 
     * @param element
     * @param introspectedTable
     */
    private void removeSetPrimaryKeyLine(XmlElement element, IntrospectedTable introspectedTable) {
        if (CollectionUtils.isNotEmpty(element.getElements())) {

            List<String> primaryKeys = new ArrayList<String>();
            if (CollectionUtils.isNotEmpty(introspectedTable.getPrimaryKeyColumns())) {
                for (IntrospectedColumn pkCol : introspectedTable.getPrimaryKeyColumns()) {
                    primaryKeys.add(pkCol.getActualColumnName() + " =");
                }
            }

            if (primaryKeys.size() > 0) {
                List<Element> elements = element.getElements();
                for (int i = 0; i < elements.size(); i++) {

                    if (isSetPrimaryKeyElement(primaryKeys, elements.get(i))) {
                        String replacer = elements.get(i).getFormattedContent(0);
                        // 如果是set最后一行：移除本行，给上一行补“,”,指针退一步
                        if (!replacer.endsWith(",") && i > 0) {
                            elements.set(i - 1, new TextElement(elements.get(i - 1).getFormattedContent(0)
                                    .replaceFirst(",$", "")));
                            elements.remove(i--);
                        }
                        // 如果是set首行，只要留下set即可，但为了格式一致，把下一行挪上来替换本行,指针退一步
                        if (StringUtils.containsIgnoreCase(replacer, "set ")) {
                            replacer = "set " + elements.get(i + 1).getFormattedContent(0).replaceFirst("^\\s*", "");
                            elements.set(i, new TextElement(replacer));
                            elements.remove(i + 1);
                            i--;
                        }

                    }
                }
            }
        }
    }

    private boolean isSetPrimaryKeyElement(List<String> strs, Element element) {
        for (String str : strs) {
            if (element.getFormattedContent(0).contains(str)) {
                return true;
            }
        }
        return false;
    }
}
