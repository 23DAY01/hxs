package com.lypc.hxs.service.impl;

import com.lypc.hxs.pojo.domain.Contact;
import com.lypc.hxs.mapper.ContactMapper;
import com.lypc.hxs.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 23DAY
 * @since 2022-07-30
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

}
