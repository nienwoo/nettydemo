package com.crazymakercircle.chat.common.codec;

import com.crazymakercircle.chat.Constants;
import com.crazymakercircle.chat.common.bean.msg.ProtoMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 解码器
 * 
 */
public class ProtobufDecoder extends ByteToMessageDecoder {
	/**
	 * 日志
	 */
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// 标记一下当前的readIndex的位置
		in.markReaderIndex();
		// 判断包头长度
		if (in.readableBytes() < Constants.PROTOCOL_HEADLENGTH) {
			// 不够包头
			LOG.error("告警：读到的消息不够包头");
			return;
		}

		// 读取传送过来的消息的版本
		int version = in.readUnsignedShort();

		// 长度如果小于0
		if (version != Constants.PROTOCOL_VERSION) {
			// 版本不对，关闭连接
			ctx.close();
		}

		// 读取传送过来的消息的长度。
		int length = in.readUnsignedShort();

		// 长度如果小于0
		if (length < 0) {// 非法数据，关闭连接
			ctx.close();
		}

		if (length > in.readableBytes()) {
			// 读到的消息体长度如果小于传送过来的消息长度
			// 重置读取位置
			in.resetReaderIndex();
			LOG.error("告警：读到的消息体长度小于传送过来的消息长度");
			return;
		}

		ByteBuf frame = Unpooled.buffer(length);
		in.readBytes(frame);

		try {
			byte[] inByte = frame.array();


			// 字节转成对象
			ProtoMsg.Message msg = ProtoMsg.Message.parseFrom(inByte);
			/*LOG.info("[APP-SERVER][RECV][remoteAddress:"
					+ ctx.channel().remoteAddress() + "][total length:"
					+ length + "][bare length:" + msg.getSerializedSize()
					+ "]:\r\n" + msg.toString());*/

			if (msg != null) {
				// 获取业务消息头
				out.add(msg);
			}
		} catch (Exception e) {
			LOG.info(ctx.channel().remoteAddress() + ",decode failed.", e);
		}

	}
}
