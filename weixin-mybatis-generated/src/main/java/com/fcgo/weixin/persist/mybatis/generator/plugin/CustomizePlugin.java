/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.fcgo.weixin.persist.mybatis.generator.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InitializationBlock;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.BeanUtils;

/**
 * 自定义创建我们的dto和dao <br/>
 * 任何对model的属性方法有修改的plugin必须配置在本plugin之前。
 * 
 * @author zhangxu
 */
public class CustomizePlugin extends PluginAdapter {
    private List<GeneratedJavaFile> customJavaFiles = new ArrayList<GeneratedJavaFile>();

    private static ShellCallback scb = new DefaultShellCallback(false);

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        List<Attribute> attrs = document.getRootElement().getAttributes();
        // com.focustech.crov.persist.dao com.focustech.crov.persist.generate
        for (int i = attrs.size() - 1; i >= 0; i--) {
            if (attrs.get(i).getName().equals("namespace")) {
                attrs.add(new Attribute("namespace", attrs.remove(i).getValue().replace("generate", "dao")
                        .replaceAll("Mapper$", context.getProperty("daoSuffix"))));
            }
        }
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Interface daoInterface =
                new Interface(context.getProperty("customDaoPkgName") + "."
                        + interfaze.getType().getShortName().replaceAll("Mapper$", context.getProperty("daoSuffix")));
        copyJavaElement(interfaze, daoInterface);
        copyFileCommentLine(interfaze, daoInterface);

        daoInterface.addSuperInterface(interfaze.getType());
        daoInterface.addImportedType(interfaze.getType());

        GeneratedJavaFile gjf = new GeneratedJavaFile(daoInterface, context.getProperty("customDaoPrjDir"));

        try {
            String path = scb.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage()).getAbsolutePath();
            if (!new File(path + File.separator + gjf.getFileName()).exists()) {
                customJavaFiles.add(gjf);
            }
        }
        catch (ShellException e) {
            e.printStackTrace();
        }

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        return super.contextGenerateAdditionalJavaFiles(introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 生成对应的dto
        String dtoName = topLevelClass.getType().getShortName() + context.getProperty("dtoSuffix");

        TopLevelClass dtoClazz = new TopLevelClass(context.getProperty("customDtoPkgName") + "." + dtoName);

        // copy dest class
        copyTopLevelClass(topLevelClass, dtoClazz, new FieldCopyHandle() {
            @Override
            public org.mybatis.generator.api.dom.java.Field custom(org.mybatis.generator.api.dom.java.Field src) {
                org.mybatis.generator.api.dom.java.Field result = new Field();
                copyField(src, result);

                if (src.getType().getPackageName().endsWith("po")) {
                    result.setType(new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                            + src.getType().getShortName() + context.getProperty("dtoSuffix")));
                }
                else if (src.getType() instanceof FullyQualifiedJavaTypeExt
                        && ((FullyQualifiedJavaTypeExt) src.getType()).getParameterType() != null
                        && ((FullyQualifiedJavaTypeExt) src.getType()).getParameterType().getPackageName()
                                .endsWith("po")) {
                    result.setType(new FullyQualifiedJavaTypeExt(src.getType().getFullyQualifiedName(),
                            new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                                    + ((FullyQualifiedJavaTypeExt) src.getType()).getParameterType().getShortName()
                                    + context.getProperty("dtoSuffix"))));
                }

                return result;
            }
        }, new MethodCopyHandle() {
            @Override
            public Method custom(Method src) {
                Method result = new Method();
                copyMethod(src, result);

                customReturnType(src, result);
                customParameter(src, result);

                return result;
            }

            private void customReturnType(Method src, Method target) {
                if (null == src.getReturnType()) {
                    return;
                }

                FullyQualifiedJavaType type = src.getReturnType();

                if (type instanceof FullyQualifiedJavaTypeExt) {
                    if (null != ((FullyQualifiedJavaTypeExt) type).getParameterType()) {
                        target.setReturnType(new FullyQualifiedJavaTypeExt(type.getFullyQualifiedName(),
                                new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                                        + ((FullyQualifiedJavaTypeExt) type).getParameterType().getShortName()
                                        + context.getProperty("dtoSuffix"))));
                    }
                }
                else {
                    if (type.getPackageName().endsWith("po")) {
                        target.setReturnType(new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                                + type.getShortName() + context.getProperty("dtoSuffix")));
                    }
                }
            }

            private void customParameter(Method src, Method target) {
                if (CollectionUtils.isEmpty(target.getParameters())) {
                    return;
                }

                FullyQualifiedJavaType type = src.getParameters().get(0).getType();

                if (type instanceof FullyQualifiedJavaTypeExt) {
                    if (null != ((FullyQualifiedJavaTypeExt) type).getParameterType()) {
                        target.getParameters().clear();
                        target.getParameters().add(
                                new Parameter(new FullyQualifiedJavaTypeExt(type.getFullyQualifiedName(),
                                        new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                                                + ((FullyQualifiedJavaTypeExt) type).getParameterType().getShortName()
                                                + context.getProperty("dtoSuffix"))), src.getParameters().get(0)
                                        .getName()));
                    }
                }
                else {
                    if (type.getPackageName().endsWith("po")) {
                        target.getParameters().clear();
                        target.getParameters().add(
                                new Parameter(new FullyQualifiedJavaType(context.getProperty("customDtoPkgName") + "."
                                        + type.getShortName() + context.getProperty("dtoSuffix")), src.getParameters()
                                        .get(0).getName()));
                    }
                }
            }
        });

        // add po convent method
        dtoClazz.addImportedType(new FullyQualifiedJavaType(BeanUtils.class.getName()));
        dtoClazz.addImportedType(topLevelClass.getType());
        Method poMethod = new Method("toPo");
        poMethod.setFinal(false);
        poMethod.setReturnType(topLevelClass.getType());
        poMethod.setStatic(false);
        poMethod.setVisibility(JavaVisibility.PUBLIC);

        poMethod.addBodyLine(poMethod.getReturnType().getShortName() + " po = new "
                + poMethod.getReturnType().getShortName() + "();");
        poMethod.addBodyLine("BeanUtils.copyProperties(this, po);");
        poMethod.addBodyLine("return po;");
        dtoClazz.addMethod(poMethod);

        // customJavaFiles.add(new GeneratedJavaFile(dtoClazz, context.getProperty("customDtoPrjDir")));

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return customJavaFiles;
    }

    public void copyJavaElement(JavaElement src, JavaElement dest) {
        for (String javaDocLine : src.getJavaDocLines()) {
            dest.addJavaDocLine(javaDocLine);
        }

        for (String annotation : src.getAnnotations()) {
            dest.addAnnotation(annotation);
        }

        dest.setVisibility(src.getVisibility());
        dest.setFinal(src.isFinal());
        dest.setStatic(src.isStatic());
    }

    public void copyInnerClass(InnerClass src, InnerClass dest, FieldCopyHandle fhandle, MethodCopyHandle mhandle) {
        copyJavaElement(src, dest);

        for (InnerClass innerClass : src.getInnerClasses()) {
            dest.addInnerClass(innerClass);
        }

        for (InnerEnum innerEnum : src.getInnerEnums()) {
            dest.addInnerEnum(innerEnum);
        }

        for (FullyQualifiedJavaType superInterface : src.getSuperInterfaceTypes()) {
            dest.addSuperInterface(superInterface);
        }

        for (InitializationBlock initializationBlock : src.getInitializationBlocks()) {
            dest.addInitializationBlock(initializationBlock);
        }

        for (org.mybatis.generator.api.dom.java.Field f : src.getFields()) {
            if (null == fhandle) {
                dest.addField(f);
            }
            else {
                dest.addField(fhandle.custom(f));
            }
        }

        for (Method method : src.getMethods()) {
            if (null == mhandle) {
                dest.addMethod(method);
            }
            else {
                dest.addMethod(mhandle.custom(method));
            }
        }

        dest.setSuperClass(src.getSuperClass());
        dest.setAbstract(src.isAbstract());
    }

    public void copyTopLevelClass(TopLevelClass src, TopLevelClass dest, FieldCopyHandle fhandle,
            MethodCopyHandle mhandle) {
        copyInnerClass(src, dest, fhandle, mhandle);

        copyCompilationUnit(src, dest);
    }

    public void copyCompilationUnit(CompilationUnit src, CompilationUnit dest) {
        for (FullyQualifiedJavaType type : src.getImportedTypes()) {
            dest.addImportedType(type);
        }

        for (String staticImport : src.getStaticImports()) {
            dest.addStaticImport(staticImport);
        }

        copyFileCommentLine(src, dest);
    }

    public void copyFileCommentLine(CompilationUnit src, CompilationUnit dest) {
        for (String commentLine : src.getFileCommentLines()) {
            dest.addFileCommentLine(commentLine);
        }
    }

    public void copyField(org.mybatis.generator.api.dom.java.Field src, org.mybatis.generator.api.dom.java.Field dest) {
        copyJavaElement(src, dest);

        dest.setName(src.getName());
        dest.setType(src.getType());
        dest.setInitializationString(src.getInitializationString());
    }

    public void copyMethod(Method src, Method dest) {
        copyJavaElement(src, dest);

        for (String line : src.getBodyLines()) {
            dest.addBodyLine(line);
        }

        for (FullyQualifiedJavaType exception : src.getExceptions()) {
            dest.addException(exception);
        }

        for (Parameter parameter : src.getParameters()) {
            dest.addParameter(parameter);
        }

        dest.setReturnType(src.getReturnType());

        dest.setName(src.getName());
    }

    private interface FieldCopyHandle {
        org.mybatis.generator.api.dom.java.Field custom(org.mybatis.generator.api.dom.java.Field src);
    }

    private interface MethodCopyHandle {
        Method custom(Method src);
    }

}
