package com.rookie.im.core.codec.proto;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2621:01
 */
@Data
public class Message {

    private MessageHeader messageHeader;

    private Object messagePack;

    @Override
    public String toString() {
        return "Message{" +
                "messageHeader=" + messageHeader +
                ", messagePack=" + messagePack +
                '}';
    }
}
