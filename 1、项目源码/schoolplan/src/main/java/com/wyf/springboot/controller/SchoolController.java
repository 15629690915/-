package com.wyf.springboot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyf.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.wyf.springboot.service.ISchoolService;
import com.wyf.springboot.entity.School;

import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyf
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private ISchoolService schoolService;


    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody School school) {
        schoolService.saveOrUpdate(school);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        schoolService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        schoolService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(schoolService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(schoolService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String school,
                                @RequestParam(defaultValue = "") Integer subType,
                                @RequestParam(defaultValue = "") String city,
                                @RequestParam(defaultValue = "") String profess) {

        return Result.success(schoolService.findPage(new Page<>(pageNum, pageSize), school, subType, city, profess));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<School> list = schoolService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("sid", "学校编号");
        writer.addHeaderAlias("school", "学校名称");
        writer.addHeaderAlias("city_code", "省份号码");
        writer.addHeaderAlias("city", "省份");
        writer.addHeaderAlias("sub_type", "文理科");
        writer.addHeaderAlias("major_code", "专业代码");
        writer.addHeaderAlias("profess", "专业");
        writer.addHeaderAlias("plan", "计划人数");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学校表单", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

}

