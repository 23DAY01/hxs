package com.lypc.hxs.controller;

import cn.hutool.core.io.FileUtil;
import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.service.ImageService;
import com.lypc.hxs.service.TeacherCurriculumRelationService;
import com.lypc.hxs.service.TeacherService;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * <p>
 * 教师表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-27
 */
@Api(tags = "教师controller")
@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCurriculumRelationService teacherCurriculumRelationService;

    @ApiOperation(value = "添加教师信息")
    @PostMapping("/addTeacher")
    public ResponseAPI<?> addTeacher(
            @ApiParam(name = "teacher", value = "教师信息")
            @RequestBody
                    Teacher teacher,
            @ApiParam(name = "img", value = "教师图片")
            @RequestParam(name = "img", required = false)
                    MultipartFile img,
            @ApiParam(name = "curriculumIds", value = "课程id")
            @RequestParam
                    List<Integer> curriculumIds) {

        //图片上传
        if (img != null) {
            String url = imageService.uploadImage(img, 0);
            teacher.setTeacherImage(url);
        }

        //保存实体类
        teacherService.save(teacher);

        //设置课程与老师的关系
        teacherCurriculumRelationService.addTeacherCurriculumRelation(curriculumIds, teacher.getTeacherId());

        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());
    }

    @ApiOperation(value = "获取教师信息")
    @GetMapping("/getTeachers")
    public ResponseAPI<?> getTeachers() {
        //获取老师信息
        List<Teacher> teachers = teacherService.list();

        //获取教授课程信息这边应该有一个dto->vo的转变否则会比较复杂
        teacherCurriculumRelationService.addCurriculums2Teachers(teachers);

        return ResponseAPI.success(teachers);
    }

    @ApiOperation(value = "获取教师信息")
    @GetMapping("/getTeacher")
    public ResponseAPI<?> getTeacherById(
            @ApiParam(name = "id", value = "教师id", required = true)
            @RequestParam(name = "id")
                    Integer id) {
        Teacher teacher = teacherService.getById(id);
        List<String> curriculumNames = teacherCurriculumRelationService.addCurriculums2Teacher(id);
        teacher.setCurriculumNames(curriculumNames);
        return ResponseAPI.success(teacher);
    }

    @ApiOperation("通过身份卡id获取教师信息")
    @GetMapping("/getTeacherByCardId")
    public ResponseAPI<?> getTeacherByCardId(
            @ApiParam(name = "cardId", value = "教师身份卡id", required = true)
            @RequestParam(name = "cardId")
                    Integer id) {
        Teacher teacher = teacherService.getTeacherByCardId(id);
        List<String> curriculumNames = teacherCurriculumRelationService.addCurriculums2Teacher(id);
        teacher.setCurriculumNames(curriculumNames);
        return ResponseAPI.success(teacher);
    }

    @ApiOperation("上传教师图片")
    @PostMapping("/uploadTeacherImage")
    public ResponseAPI<?> uploadTeacherImage(
            @ApiParam(name = "img", value = "图片", required = true)
            @RequestParam(name = "img")
                    MultipartFile img,
            @ApiParam(name = "id", value = "教师id", required = true)
            @RequestParam(name = "id")
                    Integer id) {
        Teacher teacher = teacherService.getById(id);
        String url = imageService.uploadImage(img, 0);
        teacher.setTeacherImage(url);
        teacherService.updateById(teacher);
        return ResponseAPI.success(url);
    }


    @ApiOperation("修改教师信息")
    @PostMapping("modifyTeacher")
    public ResponseAPI<?> modifyTeacher(
            @ApiParam(name = "teacher", value = "教师类")
            @RequestBody
                    Teacher teacher) {
        teacherService.updateById(teacher);
        return ResponseAPI.success(teacher);
    }
}
