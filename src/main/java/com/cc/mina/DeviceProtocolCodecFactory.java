package com.cc.mina;

import com.cc.hex.StringTools;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * project  xab-io
 *
 * @author 鸟叔
 * @description
 * @date 2017-09-05 21:11
 * @since v1.0
 * <p>
 * Copyright ©  2017-09-05~  All rights reserved.
 */
public class DeviceProtocolCodecFactory implements ProtocolCodecFactory {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {

        return new CumulativeProtocolDecoder() {

            private int headLen = 4;
            private final int magicLen = 2;

            @Override
            protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
                return false;
            }


        };
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new ProtocolEncoder() {

            private Logger logger = LoggerFactory.getLogger(getClass());

            public void encode(IoSession session, Object msg, ProtocolEncoderOutput out) throws Exception {
                //添加标志位
                String messageStr = "aa55235100230003043818483635303637303236313635363733343630303431313038353036313538";

                IoBuffer message = IoBuffer.allocate(messageStr.length() + 4, false);
                byte[] target = StringTools.string2Byte(messageStr);
//                byte[] target = new byte[bytes.length + 2];
//
//                byte[] len = new byte[2];
//                System.arraycopy(bytes, 2, len, 0, 2);
//
//                byte[] magic = {(byte) 0xAA, 0x55};
//                System.arraycopy(magic, 0, target, 0, 2);
//                System.arraycopy(bytes, 0, target, 2, bytes.length);
//                System.arraycopy(ByteTools.revers(len), 0, target, 4, 2);
                message.put(target);
//                logger.debug("发送报文:\n {}", StringTools.toHexTable(target));
                message.flip();
                out.write(message);
            }

            public void dispose(IoSession session) throws Exception {

            }
        };

    }
}
