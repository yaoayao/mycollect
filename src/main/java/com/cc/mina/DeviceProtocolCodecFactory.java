package com.cc.mina;

import com.cc.hex.StringTools;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 */
public class DeviceProtocolCodecFactory implements ProtocolCodecFactory {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {

        return new CumulativeProtocolDecoder() {

            @Override
            protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
                return false;
            }


        };
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new ProtocolEncoder() {

            public void encode(IoSession session, Object msg, ProtocolEncoderOutput out) throws Exception {
                //添加标志位
                String messageStr = "aa55235100230003043818483635303637303236313635363733343630303431313038353036313538";

                IoBuffer message = IoBuffer.allocate(messageStr.length() + 4, false);
                byte[] target = StringTools.string2Byte(messageStr);
                message.put(target);
                message.flip();
                out.write(message);
            }

            public void dispose(IoSession session) throws Exception {

            }
        };

    }
}
