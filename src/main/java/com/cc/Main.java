package com.cc;

import com.cc.po.User;
import com.cc.vo.UserVo;
import org.dozer.DozerBeanMapper;

import java.util.Arrays;

/**
 * Created by chengwanchao on 2016/6/3.
 */
public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(111);
        user.setUserName("2016-02-10");

        UserVo userVo = new UserVo();
        DozerBeanMapper mapper  = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer.xml"));
        userVo = mapper.map(user,UserVo.class);
        System.out.println(userVo.getUserId());
        System.out.println(userVo.getUserNameVo());


    }
}
