package com.example.tcpprotocol;

/**
 * @author liangqing
 * @since 2020/12/17 10:47
 */
public class Protocol {

    private int length;

    private byte[] bytes;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Protocol(int length, byte[] bytes) {
        this.length = length;
        this.bytes = bytes;
    }

    public Protocol() {

    }
}
