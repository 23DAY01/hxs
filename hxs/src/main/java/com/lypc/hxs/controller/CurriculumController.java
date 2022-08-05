package com.lypc.hxs.controller;

import com.lypc.hxs.pojo.domain.Curriculum;
import com.lypc.hxs.service.CurriculumService;
import com.lypc.hxs.utils.ResponseAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 23DAY
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    /**
     * 获得全部课程
     *
     * @return
     */
    @ApiOperation("获取全部的课程")
    @GetMapping("/getCurriculums")
    public ResponseAPI<?> getCurriculums(){
        List<Curriculum> curriculums = curriculumService.list();
        return ResponseAPI.success(curriculums);
    }




}
