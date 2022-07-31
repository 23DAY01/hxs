package com.lypc.hxs.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lypc.hxs.mapper.TeacherMapper;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.pojo.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 教师表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface TeacherService extends IService<Teacher> {

    Teacher getTeacherByCardId(Integer id);

}
