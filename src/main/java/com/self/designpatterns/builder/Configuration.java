package com.self.designpatterns.builder;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class Configuration {
    private String uid;
    private String name;

    private Configuration(String uid, String name){
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder {
        private Builder(){}

        private String uid;
        private String name;

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Configuration build() {
            return new Configuration(uid, name);
        }
    }
}
