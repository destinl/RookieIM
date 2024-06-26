package com.rookie.im.core.server;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2218:01
 */
@Slf4j
public class ImServer {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private int port;
    private Channel serverChannel;

//    public ImServer(int port){
//        this.port = port;
//    }

    public ImServer(){
        loadConfig();
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        start();

    }

    private void start(){
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 10240)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("http-codec", new HttpServerCodec());
                            pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                            pipeline.addLast("aggregator", new HttpObjectAggregator(65535));
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            serverChannel = future.channel();
            Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
            serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            shutdown();
        }
    }

    private void shutdown(){
        if(serverChannel != null){
            serverChannel.close();
        }
        if(bossGroup != null){
            bossGroup.shutdownGracefully();
        }
        if(workerGroup != null){
            workerGroup.shutdownGracefully();
        }
    }

    private void loadConfig(){
        Properties prop = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(inputStream == null){
                log.error("Sorry, unable to find con.properties");
                return;
            }
            prop.load(inputStream);
            port = Integer.parseInt(prop.getProperty("websocket.port"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
