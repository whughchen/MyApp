/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：26 May 2016 17:59:10 
 */
package com.turingcat.netty;



import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class OutboundHandler2 extends ChannelOutboundHandlerAdapter {
    private static Log	logger	= LogFactory.getLog(OutboundHandler2.class);
    
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("OutboundHandler2.write:"+msg);
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}