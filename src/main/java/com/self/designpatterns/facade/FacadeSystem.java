package com.self.designpatterns.facade;


/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class FacadeSystem {

    private SubSystemCd subSystemCd;
    private SubSystem subSystem;
    private String movieName;

    public FacadeSystem(String movieName) {
        this.movieName = movieName;
        subSystem = new SubSystem();
        subSystemCd = new SubSystemCd(movieName);
    }

    public void watchMovie() {
        this.subSystem.turnOnTV();
        this.subSystem.setCd(movieName);
        this.subSystem.watchingTv();
    }
}
