import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestSome {

    static class MyMsgDecoder extends MessageToMessageDecoder<String>{
        @Override
        protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

        }
    }
    public static void main(String[] args) {
        char a='啊';
        String b="啊";

        char[] chars = b.toCharArray();
        new MyMsgDecoder();
        Class<? super TestSome> superclass = TestSome.class.getSuperclass();
//        byte[] b2 = new byte[3];
//        b2[0] = (byte) ((a & 0xFF0000) >> 16);
//        b2[1] = (byte)  ((a & 0xFF00) >> 8);
//        b2[2] = (byte)  ((a & 0xFF) >> 0);


        String c=new String(b.getBytes(),Charset.forName("utf-8"));
        char[] chars1 = c.toCharArray();
        byte[] cBytes = c.getBytes(Charset.forName("utf-8"));

        System.out.println(chars.length);

        int threadCount=100;
        TestAutomic testAutomic=new TestAutomic();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                testAutomic.retain();
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(testAutomic.count);

    }

    private static java.util.concurrent.atomic.AtomicIntegerFieldUpdater<TestAutomic> updater=AtomicIntegerFieldUpdater.newUpdater(TestAutomic.class,"count");
    public static class TestAutomic{
        public volatile int count;
        public TestAutomic(){
            count=1;
        }


        public void retain(){
            for (;;)
            {
                int count=this.count;
                int next=count+1;
                if(updater.compareAndSet(this,count,next)){
                    break;
                }
            }

        }

        public AtomicInteger atomicInteger;

    }


    class InBoundDecoder extends MessageToByteEncoder<Long>{
        @Override
        protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        }


    }
}
