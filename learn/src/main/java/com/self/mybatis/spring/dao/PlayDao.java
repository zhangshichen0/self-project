package com.self.mybatis.spring.dao;

import com.self.mybatis.spring.model.Play;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public interface PlayDao {

    /**
     * 获取列表
     *
     * @return
     */
    @Select("SELECT * FROM play")
    List<Play> listPlay();

}
