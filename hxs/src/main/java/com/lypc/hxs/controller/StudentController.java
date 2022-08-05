package com.lypc.hxs.controller;

import com.lypc.hxs.constant.StatusCode;
import com.lypc.hxs.exception.BusinessException;
import com.lypc.hxs.pojo.domain.Student;
import com.lypc.hxs.service.StudentService;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-27
 */
@Api(tags = "学生controller")
@RestController
@RequestMapping("/admin/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @ApiOperation(value = "添加学生信息")
    @PostMapping("/addStudent")
    public ResponseAPI<?> addStudent(
            @ApiParam(name = "student", value = "学生信息")
            @RequestBody Student student) {
        studentService.save(student);
        return ResponseAPI.success(StatusCode.SUCCESS.OK.getCode());
    }


    /**
     * 获取学生信息
     * @return
     */
    @ApiOperation(value = "获取学生信息")
    @GetMapping("/getStudents")
    public ResponseAPI<?> getStudents(){
        List<Student> students = studentService.list();
        return ResponseAPI.success(students);
    }

    /**
     * 通过id获取信息
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id获取学生信息")
    @GetMapping("/getStudentById")
    public ResponseAPI<?> getStudentById(
            @ApiParam(name = "id", value = "学生id", required = true)
            @RequestParam(name = "id")
                    Integer id){
        Student student = studentService.getById(id);
        return ResponseAPI.success(student);
    }

    /**
     * 通过身份卡获取信息
     * @param id 身份卡
     * @return
     */
    @ApiOperation("通过身份卡id获取学生信息")
    @GetMapping("/getStudentByCardId")
    public ResponseAPI<?> getStudentByCardId(
            @ApiParam(name = "cardId", value = "学生身份卡id", required = true)
            @RequestParam(name = "cardId")
                    Integer id){
        Student student = studentService.getStudentByCardId(id);
        return ResponseAPI.success(student);
    }
}
