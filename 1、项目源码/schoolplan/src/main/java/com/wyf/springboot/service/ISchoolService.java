package com.wyf.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.springboot.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.springboot.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyf
 * @since 2022-07-27
 */
public interface ISchoolService extends IService<School> {

    Page<School> findPage(Page<School> objectPage, String school, Integer subType, String city, String profess);
}
