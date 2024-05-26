package com.wyf.springboot.service.impl;

import cn.hutool.core.date.DateTime;
import com.wyf.springboot.entity.Email;
import com.wyf.springboot.mapper.EmailMapper;
import com.wyf.springboot.service.IEmailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wyf
 * @since 2022-07-26
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements IEmailService {
    @Override
    public void saveCode(String email, String code, DateTime offsetMinute) {

    }
}
