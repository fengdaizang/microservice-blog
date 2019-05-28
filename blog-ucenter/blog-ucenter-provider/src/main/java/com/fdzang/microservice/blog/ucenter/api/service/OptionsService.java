package com.fdzang.microservice.blog.ucenter.api.service;

import com.fdzang.microservice.blog.ucenter.common.dto.OptionsDTO;

import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:13
 */
public interface OptionsService {
    List<OptionsDTO> getAllOptions();

    OptionsDTO getOptionById(String id);

    Boolean updateOption(String id,String value);

    Boolean incrementById(String id,Integer num);
}
