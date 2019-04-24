package com.lmm.netty;

import com.lmm.enums.MsgActionEnum;
import com.lmm.pojo.DataContent;
import com.lmm.service.UserService;
import com.lmm.utils.JsonUtils;
import com.lmm.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于检测channel的心跳handler
 * 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 *
 */
public class HeartHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartHandler.class);

    /**
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        //判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲)
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;//强制类型转换

            if (event.state() == IdleState.READER_IDLE){
                LOGGER.info("进入读空闲...");
            }else  if (event.state() == IdleState.WRITER_IDLE){
                LOGGER.info("进入写空闲...");
            }else if (event.state() == IdleState.ALL_IDLE){
                LOGGER.info("进入读写空闲...");
                LOGGER.info("channel关闭前，users的数量为：{}",ChatHandler.users.size());
                Channel channel = ctx.channel();
                //关闭无用的channel，以防资源浪费
                channel.close();

                LOGGER.info("channel关闭后，users的数量为：{}",ChatHandler.users.size());
            }
        }
    }

}
