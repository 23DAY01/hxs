package com.lypc.hxs.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lypc.hxs.mapper.CurriculumMapper;
import com.lypc.hxs.mapper.TeacherMapper;
import com.lypc.hxs.pojo.domain.Curriculum;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.pojo.domain.TeacherCurriculumRelation;
import com.lypc.hxs.mapper.TeacherCurriculumRelationMapper;
import com.lypc.hxs.service.TeacherCurriculumRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-08-05
 */
@Service
public class TeacherCurriculumRelationServiceImpl extends ServiceImpl<TeacherCurriculumRelationMapper, TeacherCurriculumRelation> implements TeacherCurriculumRelationService {

    @Autowired
    private TeacherCurriculumRelationMapper teacherCurriculumRelationMapper;

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void addTeacherCurriculumRelation(List<Integer> curriculums, Integer teacherId) {
        for (Integer curriculumId : curriculums) {
            TeacherCurriculumRelation relation = new TeacherCurriculumRelation();
            relation.setCurriculumId(curriculumId);
            relation.setTeacherId(teacherId);
            save(relation);
        }
    }

    @Override
    public List<String> addCurriculums2Teacher(Integer teacherId) {

        //查询出关系信息
        LambdaQueryWrapper<TeacherCurriculumRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TeacherCurriculumRelation::getTeacherId, teacherId);
        List<TeacherCurriculumRelation> relations = teacherCurriculumRelationMapper.selectList(wrapper);

        Set<Integer> curriculumIds = relations.stream().map(TeacherCurriculumRelation::getCurriculumId).collect(Collectors.toSet());
        LambdaQueryWrapper<Curriculum> curriculumLambdaQueryWrapper = new LambdaQueryWrapper<>();
        curriculumLambdaQueryWrapper.in(Curriculum::getCurriculumId, curriculumIds);

        //查询名字
        List<Curriculum> curriculums = curriculumMapper.selectList(curriculumLambdaQueryWrapper);
        List<String> curriculumNames = curriculums.stream().map(Curriculum::getCurriculumName).collect(Collectors.toList());
        return curriculumNames;
    }


    @Override
    public void addCurriculums2Teachers(List<Teacher> teachers) {

        ////查出来教师id
        //List<Teacher> teachers = teacherMapper.selectList(new QueryWrapper<>());
        ////Set<Integer> teacherIds = teachers.stream().map(Teacher::getTeacherId).collect(Collectors.toSet());

        //查找关系
        LambdaQueryWrapper<TeacherCurriculumRelation> teacherCurriculumRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherCurriculumRelationLambdaQueryWrapper.in(TeacherCurriculumRelation::getTeacherId);
        List<TeacherCurriculumRelation> teacherCurriculumRelations = teacherCurriculumRelationMapper.selectList(teacherCurriculumRelationLambdaQueryWrapper);

        //查找课程和id
        Set<Integer> curriculumIds = teacherCurriculumRelations.stream().map(TeacherCurriculumRelation::getCurriculumId).collect(Collectors.toSet());
        LambdaQueryWrapper<Curriculum> curriculumLambdaQueryWrapper = new LambdaQueryWrapper<>();
        curriculumLambdaQueryWrapper.in(Curriculum::getCurriculumId, curriculumIds);
        List<Curriculum> curriculumList = curriculumMapper.selectList(curriculumLambdaQueryWrapper);

        //这个用法不了解
        Map<Integer, List<Integer>> listMap = teacherCurriculumRelations.stream().collect(Collectors.groupingBy(TeacherCurriculumRelation::getTeacherId, Collectors.mapping(TeacherCurriculumRelation::getCurriculumId, Collectors.toList())));
        for (Teacher teacher : teachers) {
            //通过流操作从curriculum中获取在teacherId中的curriculum
            List<String> curriculumNames = curriculumList.stream().filter(x -> listMap.get(teacher.getTeacherId()).contains(x.getCurriculumId())).map(Curriculum::getCurriculumName).collect(Collectors.toList());
            teacher.setCurriculumNames(curriculumNames);
        }
    }
}
