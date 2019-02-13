package com.fdzang.microservice.blog.article.api.service.impl;

import com.fdzang.microservice.blog.article.api.service.CommentService;
import com.fdzang.microservice.blog.article.common.dto.CommentDTO;
import com.fdzang.microservice.blog.article.dao.domain.CommentDOExample;
import com.fdzang.microservice.blog.article.dao.domain.CommentDOWithBLOBs;
import com.fdzang.microservice.blog.article.dao.mapper.CommentMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanghu
 * @Date: 2019/1/8 17:01
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getCommentByArticleId(String id) {
        CommentDOExample example=new CommentDOExample();
        example.createCriteria().andCommentArticleIdEqualTo(id);
        List<CommentDOWithBLOBs> commentDOS=commentMapper.selectByExampleWithBLOBs(example);
        if(CollectionUtils.isNotEmpty(commentDOS)){
            List<CommentDTO> commentDTOS=new ArrayList<>();
            for (CommentDOWithBLOBs comment:commentDOS) {
                CommentDTO commentDTO=new CommentDTO();
                BeanUtils.copyProperties(comment,commentDTO);
                if(comment.getCommentOriginalCommentId()!=null
                        &&!comment.getCommentOriginalCommentId().equals("")){
                    commentDTO.setReplyFlag(true);
                }else{
                    commentDTO.setReplyFlag(false);
                }
                commentDTOS.add(commentDTO);
            }
            return commentDTOS;
        }
        return null;
    }
}
