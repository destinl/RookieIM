package com.rookie.im.common.constants;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2714:07
 */
public class Constants {
    public final static String UserId = "userId";

    public final static String AppId = "appId";

    public final static String ClientType = "clientType";

    public final static String IMEI = "imei";

    public static class RedisConstants{
        public static final String UserSessionConstants = ":userSession:";
    }

    public static class RabbitConstants{
        public static final String Im2MessageService = "pipeline2MessageService";

        public static final String Im2UserService = "pipeline2UserService";

        public static final String Im2GroupService = "pipeline2GroupService";

    }
}
