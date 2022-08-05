package com.lypc.hxs.service;

import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.pojo.domain.TeacherCurriculumRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 23DAY
 * @since 2022-08-05
 */
public interface TeacherCurriculumRelationService extends IService<TeacherCurriculumRelation> {

    void addTeacherCurriculumRelation(List<Integer> curriculums,Integer teacherId);

    List<String> addCurriculums2Teacher(Integer teacherId);

    void addCurriculums2Teachers(List<Teacher> teachers);

}
