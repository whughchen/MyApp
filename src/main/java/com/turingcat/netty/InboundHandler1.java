/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：26 May 2016 17:51:53 
 */
package com.turingcat.netty;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InboundHandler1 extends ChannelInboundHandlerAdapter {
    private static Log logger = LogFactory.getLog(InboundHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("InboundHandler1.channelRead: ctx :" + ctx);
        
        // 通知下游InboundHandler2执行
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("InboundHandler1.channelReadComplete");
        ctx.flush();
    }
}