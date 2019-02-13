package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.ArchivedateService;
import com.fdzang.microservice.blog.article.api.utils.TimeUtils;
import com.fdzang.microservice.blog.article.common.dto.ArchivedateDTO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateDO;
import com.fdzang.microservice.blog.article.dao.domain.ArchivedateDOExample;
import com.fdzang.microservice.blog.article.dao.mapper.ArchivedateMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 16:53
 */
@Service
public class ArchivedateServiceImpl implements ArchivedateService {
    @Autowired
    private ArchivedateMapper archivedateMapper;

    @Override
    public List<ArchivedateDTO> getArchives() {
        List<ArchivedateDO> archivedateDOS=archivedateMapper.selectByExample(null);
        if(CollectionUtils.isNotEmpty(archivedateDOS)){
            List<ArchivedateDTO> archivedateDTOS=new ArrayList<>();
            for (ArchivedateDO archivedateDO:archivedateDOS) {
                ArchivedateDTO archivedateDTO=new ArchivedateDTO();
                BeanUtils.copyProperties(archivedateDO,archivedateDTO);
                LocalDate localDate= TimeUtils.getByTimeStamp(archivedateDO.getArchiveTime());
                archivedateDTO.setArchiveDateYear(localDate.getYear());
                archivedateDTO.setArchiveDateMonth(localDate.getMonthValue());
                archivedateDTO.setMonthName(localDate.getMonth().name());

                archivedateDTOS.add(archivedateDTO);
            }
            return archivedateDTOS;
        }
        return null;
    }

    @Override
    public ArchivedateDTO getArchiveByTime(Integer year, Integer month) {
        Long timestamp=TimeUtils.getTimeStamp(year, month);
        ArchivedateDOExample example=new ArchivedateDOExample();
        example.createCriteria().andArchiveTimeEqualTo(timestamp);
        List<ArchivedateDO> archivedateDOS=archivedateMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(archivedateDOS)){
            ArchivedateDTO archivedateDTO=new ArchivedateDTO();
            BeanUtils.copyProperties(archivedateDOS.get(0),archivedateDTO);
            LocalDate localDate= TimeUtils.getByTimeStamp(archivedateDTO.getArchiveTime());
            archivedateDTO.setArchiveDateYear(localDate.getYear());
            archivedateDTO.setArchiveDateMonth(localDate.getMonthValue());
            archivedateDTO.setMonthName(localDate.getMonth().name());

            return archivedateDTO;
        }
        return null;
    }
}
