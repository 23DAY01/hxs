package com.lypc.hxs.controller;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Teacher;
import com.lypc.hxs.service.TeacherService;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    private TeacherService teacherService;

    @ApiOperation(value = "添加教师信息")
    @PostMapping("/addTeacher")
    public ResponseAPI<?> addTeacher(
            @ApiParam(name = "teacher", value = "教师信息")
            @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());
    }

    @ApiOperation(value = "获取教师信息")
    @GetMapping("/getTeachers")
    public ResponseAPI<?> getTeachers(){
        List<Teacher> teachers = teacherService.list();
        return ResponseAPI.success(teachers);
    }

    @ApiOperation(value = "获取教师信息")
    @GetMapping("/getTeacher")
    public ResponseAPI<?> getTeacherById(
            @ApiParam(name = "id", value = "教师id", required = true)
            @RequestParam(name = "id")
                    Integer id){
        Teacher teacher = teacherService.getById(id);
        return ResponseAPI.success(teacher);
    }
}
