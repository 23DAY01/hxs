package com.lypc.hxs;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.lypc.hxs.constant.StatusCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class HxsApplicationTests {

    @Test
    void contextLoads() {

        List<String> s = new ArrayList<>();
        s.add("admin");
        s.add("article");
        s.add("article_tag_relation");
        s.add("category");
        s.add("comment");
        s.add("contact");
        s.add("image");
        s.add("student");
        s.add("tag");
        s.add("teacher");
        s.add("user");


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/hxs?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "20010823GTH")
                .globalConfig(builder -> {
                    builder.disableOpenDir() //禁止打开输出目录
                            .fileOverride()
                            .outputDir("./src/main/java")// 指定输出目录
                            .author("23DAY")// 设置作者
                            .enableSwagger()// 开启swagger
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("com.lypc.hxs") // 设置父包名
                            .entity("pojo.domain")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapperxml")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/mapper"))//文件的输出路径
                            .build();
                })
                .strategyConfig(builder -> {
                    builder.addInclude(s)
                            .entityBuilder()
                            .disableSerialVersionUID()//禁用生成 serialVersionUID
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)//主键策略
                            .formatFileName("%s")//文件名
                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void t1(){
        Integer code = StatusCode.SUCCESS.OK.getCode();
    }

}
