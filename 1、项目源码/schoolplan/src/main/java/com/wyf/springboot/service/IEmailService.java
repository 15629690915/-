package com.wyf.springboot.service;

import cn.hutool.core.date.DateTime;
import com.wyf.springboot.entity.Email;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wyf
 * @since 2022-07-26
 */
public interface IEmailService extends IService<Email> {

    void saveCode(String email, String code, DateTime offsetMinute);
}
