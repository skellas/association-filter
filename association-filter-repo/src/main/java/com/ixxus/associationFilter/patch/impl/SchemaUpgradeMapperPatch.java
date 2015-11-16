package com.ixxus.associationFilter.patch.impl;

import org.alfresco.repo.admin.patch.AbstractPatch;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * Created by Ixxus on 11/16/2015.
 */
public class SchemaUpgradeMapperPatch extends AbstractPatch{

    private SqlSessionTemplate sqlSessionTemplate;

    private static final String MSG_SUCCESS             = "Views successfully installed.";
    private static final String MSG_ERROR               = "Views were not successfully installed.";


    @Override protected String applyInternal() throws Exception {

        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession();
        IxxusAssociationFilterMapper mapper = sqlSession.getMapper(IxxusAssociationFilterMapper.class);
        String message = "";
        try {
            mapper.createNodeRefByIdView();
            mapper.createAssociationByTypeView();
            message = MSG_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            message = MSG_ERROR;
        } finally {
            sqlSession.close();
        }
        return message;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}
