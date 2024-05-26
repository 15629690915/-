package com.wyf.springboot.service.impl;

import com.wyf.springboot.entity.Article;
import com.wyf.springboot.mapper.ArticleMapper;
import com.wyf.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
