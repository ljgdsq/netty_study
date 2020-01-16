package com.zsl.demo.im.protocol;

public abstract class Packet<T> {
    private Byte version=1;

    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
