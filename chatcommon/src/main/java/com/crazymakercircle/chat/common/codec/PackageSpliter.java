package com.crazymakercircle.chat.common.codec;

import com.crazymakercircle.chat.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class PackageSpliter extends LengthFieldBasedFrameDecoder
{

    public PackageSpliter() {
        super(Integer.MAX_VALUE, Constants.LENGTH_OFFSET,Constants.LENGTH_BYTES_COUNT);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        return super.decode(ctx, in);
    }
}
