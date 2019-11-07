package com.self.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * java对象布局--在mac上需要授权
 *
 * @author shichen
 * @create 2019-11-07
 * @desc
 */
public class JavaObjectLayout {

    public static void main(String[] args) {

        Object object = new Object();

        ClassLayout.parseInstance(object).toPrintable();

    }

}
