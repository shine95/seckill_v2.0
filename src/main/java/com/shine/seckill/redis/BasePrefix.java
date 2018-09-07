package com.shine.seckill.redis;

public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(int expireSeconds,String prefix){
        this.expireSeconds=expireSeconds;
        this.prefix=prefix;
    }

    @Override
    public int expireSeconds() {
        return 0;
    }

    public BasePrefix(String prefix) {//0代表永不过期
        this(0,prefix);
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
