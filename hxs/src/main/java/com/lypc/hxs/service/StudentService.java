package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
public interface StudentService extends IService<Student> {

    Student getStudentByCardId(Integer id);


}
