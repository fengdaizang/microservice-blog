package com.fdzang.microservice.blog.ucenter.api.service.impl;

import com.fdzang.microservice.blog.ucenter.api.service.OptionsService;
import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;
import com.fdzang.microservice.blog.ucenter.dao.domain.OptionsDO;
import com.fdzang.microservice.blog.ucenter.dao.mapper.OptionsMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:14
 */
@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public List<OptionsDTO> getAllOptions() {
        List<OptionsDO> optionsDOS=optionsMapper.selectByExampleWithBLOBs(null);
        if(CollectionUtils.isNotEmpty(optionsDOS)){
            List<OptionsDTO> optionsDTOS=new ArrayList<>();
            for (OptionsDO option:optionsDOS) {
                OptionsDTO optionsDTO=new OptionsDTO();
                BeanUtils.copyProperties(option,optionsDTO);
                optionsDTOS.add(optionsDTO);
            }
            return optionsDTOS;
        }
        return null;
    }

    @Override
    public OptionsDTO getOptionById(String id) {
        OptionsDO optionsDO=optionsMapper.selectByPrimaryKey(id);
        OptionsDTO optionsDTO=new OptionsDTO();
        BeanUtils.copyProperties(optionsDO,optionsDTO);

        return optionsDTO;
    }

    @Override
    public Boolean updateOption(String id, String value) {
        OptionsDO optionsDO=optionsMapper.selectByPrimaryKey(id);
        optionsDO.setOptionValue(value);
        int count=optionsMapper.updateByPrimaryKeyWithBLOBs(optionsDO);

        return count>0;
    }

    @Override
    public Boolean incrementById(String id,Integer num) {
        OptionsDO optionsDO=optionsMapper.selectByPrimaryKey(id);
        int count = 0;
        try {
            count = Integer.parseInt(optionsDO.getOptionValue())+num;
        } catch (NumberFormatException e) {
            return false;
        }
        optionsDO.setOptionValue(String.valueOf(count));

        count=optionsMapper.updateByPrimaryKeyWithBLOBs(optionsDO);

        return  count>0;
    }
}
