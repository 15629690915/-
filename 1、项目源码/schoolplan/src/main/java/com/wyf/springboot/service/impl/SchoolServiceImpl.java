package com.wyf.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.springboot.entity.School;
import com.wyf.springboot.entity.User;
import com.wyf.springboot.mapper.SchoolMapper;
import com.wyf.springboot.mapper.UserMapper;
import com.wyf.springboot.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyf
 * @since 2022-07-27
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    @Resource
    private SchoolMapper schoolMapper;


    @Override
    public Page<School> findPage(Page<School> page, String school, Integer subType, String city, String profess) {
        return schoolMapper.findPage(page,school, subType, city, profess);
    }
}
