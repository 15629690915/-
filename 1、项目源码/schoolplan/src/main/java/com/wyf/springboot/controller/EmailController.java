package com.wyf.springboot.controller;


import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyf.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import com.wyf.springboot.service.IEmailService;
import com.wyf.springboot.entity.Email;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyf
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private IEmailService emailService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Email email) {
        emailService.saveOrUpdate(email);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        emailService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        emailService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(emailService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(emailService.getById(id));
    }



}

