package com.self.proxy.staticproxy;

/**
 * @author shichen
 * @create 2018/3/5
 * @desc
 */
public class ZhangDad implements People {

    private Zhang zhang;

    public ZhangDad(Zhang zhang) {
        this.zhang = zhang;
    }

    /**
     * 找对象
     */
    @Override
    public void zhaoDuiXiang() {
        this.before();
        zhang.zhaoDuiXiang();
        this.after();
    }

    private void before() {
        System.out.println("我是张的爸爸，检查学历，身高等");
    }

    private void after(){
        System.out.println("你合格了");
    }
}
