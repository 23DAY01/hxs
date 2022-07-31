package com.lypc.hxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.mapper.TeacherMapper;
import com.lypc.hxs.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherByCardId(Integer id) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTeacherCardId, id);
        return teacherMapper.selectOne(wrapper);
    }
}
