package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Tag;
import com.lypc.hxs.mapper.TagMapper;
import com.lypc.hxs.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
