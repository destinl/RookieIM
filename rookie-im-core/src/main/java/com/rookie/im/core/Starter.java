package com.rookie.im.core;

import com.rookie.im.core.config.AppConfig;
import com.rookie.im.core.server.ImServer;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;
import com.rookie.im.core.server.utils.reids.RedisManager;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2218:02
 */

public class Starter {
    public static void main(String[] args){
        if(args.length > 0){
            start(args[0]);
        }
    }

    public static void start(String path){
        Yaml yaml = new Yaml();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(path);
            AppConfig appConfig = yaml.loadAs(inputStream, AppConfig.class);
            RedisManager.init(appConfig);
            new ImServer(appConfig.getRookie());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

