package com.example.learn.gp.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public abstract class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M, T> {
}
