package com.wyf.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.springboot.controller.dto.UserPasswordDTO;
import com.wyf.springboot.entity.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyf
 * @since 2022-07-27
 */
public interface SchoolMapper extends BaseMapper<School> {

    Page<School> findPage(Page<School> page, @Param("school") String school,
                                @Param("subType") Integer subType,
                                @Param("city") String city,
                                @Param("profess") String profess
    );
}
