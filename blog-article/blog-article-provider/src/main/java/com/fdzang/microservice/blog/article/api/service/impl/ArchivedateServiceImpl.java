package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.ArchivedateService;
import com.fdzang.microservice.blog.article.api.utils.ConvertUtils;
import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateArticleDO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateArticleDOExample;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateDO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateDOExample;
import com.fdzang.microservice.blog.article.dao.mapper.ArchivedateArticleMapper;
import com.fdzang.microservice.blog.article.dao.mapper.ArchivedateMapper;
import com.fdzang.microservice.blog.common.utils.TimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:53
 */
@Service
public class ArchivedateServiceImpl implements ArchivedateService {
    @Autowired
    private ArchivedateMapper archivedateMapper;

    @Autowired
    private ArchivedateArticleMapper archivedateArticleMapper;

    @Override
    public List<ArchivedateDTO> getArchives() {
        ArchivedateDOExample example=new ArchivedateDOExample();
        example.createCriteria().andArchivedatePublishedArticleCountGreaterThan(0);

        List<ArchivedateDO> archivedateDOS=archivedateMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateDOS)){
            return ConvertUtils.convertArchivedateList(archivedateDOS);
        }
        return null;
    }

    @Override
    public ArchivedateDTO getArchiveByTime(Integer year, Integer month) {
        Long timestamp= TimeUtils.getTimeStamp(year, month);
        ArchivedateDOExample example=new ArchivedateDOExample();
        example.createCriteria().andArchiveTimeEqualTo(timestamp)
                .andArchivedatePublishedArticleCountGreaterThan(0);;
        List<ArchivedateDO> archivedateDOS=archivedateMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateDOS)){
            return ConvertUtils.convertArchivedate(archivedateDOS.get(0));
        }
        return null;
    }

    @Override
    public Boolean addArticleAndArchive(String id, Boolean isPush) {
        try {
            Long archivedate=TimeUtils.getCurrentArchivedate();
            ArchivedateDOExample example=new ArchivedateDOExample();
            example.createCriteria().andArchiveTimeEqualTo(archivedate);
            List<ArchivedateDO> archivedateDOS=archivedateMapper.selectByExample(example);

            ArchivedateDO archivedateDO=new ArchivedateDO();
            if(CollectionUtils.isNotEmpty(archivedateDOS)){
                archivedateDO=archivedateDOS.get(0);

                archivedateDO.setArchivedateArticleCount(
                        archivedateDO.getArchivedateArticleCount()+1);
                if(isPush){
                    archivedateDO.setArchivedatePublishedArticleCount(
                            archivedateDO.getArchivedatePublishedArticleCount()+1);
                }
                archivedateMapper.updateByPrimaryKey(archivedateDO);
            }else{
                archivedateDO.setId(TimeUtils.getTimestamp());
                archivedateDO.setArchiveTime(archivedate);
                archivedateDO.setArchivedateArticleCount(1);
                if(isPush){
                    archivedateDO.setArchivedatePublishedArticleCount(1);
                }else{
                    archivedateDO.setArchivedatePublishedArticleCount(0);
                }
                archivedateMapper.insert(archivedateDO);
            }

            ArchivedateArticleDO archivedateArticleDO=new ArchivedateArticleDO();
            archivedateArticleDO.setId(TimeUtils.getTimestamp());
            archivedateArticleDO.setArticleId(id);
            archivedateArticleDO.setArchivedateId(archivedateDO.getId());
            archivedateArticleMapper.insert(archivedateArticleDO);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean updateArticleAndArchive(String id, Boolean oldPush, Boolean newPush) {
        ArchivedateArticleDOExample example=new ArchivedateArticleDOExample();
        example.createCriteria().andArticleIdEqualTo(id);
        List<ArchivedateArticleDO> archivedateArticleDOS=
                archivedateArticleMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateArticleDOS)){
            ArchivedateDO archivedateDO=
                    archivedateMapper.selectByPrimaryKey(
                            archivedateArticleDOS.get(0).getArchivedateId());

            if(oldPush){
                archivedateDO.setArchivedateArticleCount(
                        archivedateDO.getArchivedateArticleCount()-1);
            }
            if(newPush){
                archivedateDO.setArchivedateArticleCount(
                        archivedateDO.getArchivedateArticleCount()+1);
            }

            archivedateMapper.updateByPrimaryKey(archivedateDO);
        }else{
            return false;
        }

        return true;
    }

    @Override
    public Boolean deleteArticleAndArchive(String id, Boolean isPush) {
        ArchivedateArticleDOExample example=new ArchivedateArticleDOExample();
        example.createCriteria().andArticleIdEqualTo(id);
        List<ArchivedateArticleDO> archivedateArticleDOS=
                archivedateArticleMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateArticleDOS)){
            archivedateArticleMapper.deleteByPrimaryKey(archivedateArticleDOS.get(0).getId());

            ArchivedateDO archivedateDO=
                    archivedateMapper.selectByPrimaryKey(
                            archivedateArticleDOS.get(0).getArchivedateId());

            if(isPush){
                archivedateDO.setArchivedateArticleCount(
                        archivedateDO.getArchivedateArticleCount()-1);
            }
            archivedateMapper.updateByPrimaryKey(archivedateDO);
        }else{
            return false;
        }

        return true;
    }
}
