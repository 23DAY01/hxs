package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Student;
import com.lypc.hxs.mapper.StudentMapper;
import com.lypc.hxs.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
