package com.self.mybatis.spring.service;

import com.self.mybatis.spring.dao.PlayDao;
import com.self.mybatis.spring.model.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
@Service
public class PlayService {

    @Autowired
    private PlayDao playDao;

    public void listPlay() {
        List<Play> playList = playDao.listPlay();
        System.out.println(playList);
    }

}
