package com.gw.account.utils;

import com.alibaba.fastjson.JSON;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.TreeMap;


public class TcpClient implements Closeable {
    private final static SecretKeySpec key = new SecretKeySpec(new byte[]{
            (byte) 0x63, (byte) 0xfa, (byte) 0xc0, (byte) 0xd0,
            (byte) 0x34, (byte) 0xd9, (byte) 0xf7, (byte) 0x93,
            (byte) 0x19, (byte) 0x9e, (byte) 0x9d, (byte) 0x6d,
            (byte) 0xf3, (byte) 0x9a, (byte) 0xa8, (byte) 0x16,
    }, "AES");
    private final static IvParameterSpec iv = new IvParameterSpec(new byte[]{
            (byte) 0x37, (byte) 0x36, (byte) 0x35, (byte) 0x34,
            (byte) 0x33, (byte) 0x32, (byte) 0x31, (byte) 0x20,
            (byte) 0x4e, (byte) 0x6f, (byte) 0x77, (byte) 0x20,
            (byte) 0x69, (byte) 0x73, (byte) 0x20, (byte) 0x74,
    });

    private final static short USERLOGINREQ = 0x3001;
    private final static short USERGETREQ   = 0x300A;
    private final static short ADDUSEREXREQ = 0x3101;
    private final static short UPDPASSREQ   = 0x1004;

    private SocketChannel conn = null;
    private ByteBuffer rb      = null;
    private ByteBuffer wb      = null;

    public TcpClient() {
        rb = ByteBuffer.allocate(16 * 1024);
        rb.order(ByteOrder.LITTLE_ENDIAN);
        wb = ByteBuffer.allocate(16 * 1024);
        wb.order(ByteOrder.LITTLE_ENDIAN);
    }

    public void open(String host, int port) {
        close();
        try {
            conn = SocketChannel.open();
            conn.connect(new InetSocketAddress(host, port));
            rb.clear();
            wb.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public String userget(String json) {
        send(USERGETREQ, (short) 2, json);
        return read();
    }

    public String adduserex(String json) {
        send(ADDUSEREXREQ, (short)3, json);
        return read();
    }

    public String userlogin(String json) {
        send(USERLOGINREQ, (short) 2, json);
        return read();
    }

    public String updpass(String json) {
        send(UPDPASSREQ, (short) 3, json);
        return read();
    }

    public void send(short type, short version, String body) {
        wb.putShort((short) 0x0ABA);
        if (version == 2) {
            wb.putShort(version);
            wb.putShort(type);
            wb.putInt(0);
            wb.putInt(0);
            wb.put((byte) 1);
        } else {
            wb.put((byte) 36);
            wb.put((byte) 0x30);
            wb.putInt(0);
            wb.putInt(0);
            wb.putShort(type);
            wb.putShort((short) 0);
        }

        try {
            byte[] bodyBuffer = body.getBytes(getEncoding(version));
            if (version == 2) {
                wb.putInt(6, bodyBuffer.length);
            } else {
                wb.putInt(4, bodyBuffer.length);
            }
            wb.put(crypt(bodyBuffer, 0, bodyBuffer.length, Cipher.ENCRYPT_MODE));
            wb.flip();
            while (wb.hasRemaining()) {
                conn.write(wb);
            }
            wb.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            readAtLeast(15);
            short version = rb.getShort(2);

            int headerlen = 15;
            int len = rb.getInt(6);
            if (version != 2) {
                headerlen = 16;
                len = rb.getInt(4);
            }
            readAtLeast(headerlen + len);
            String encoding = getEncoding(version);
            byte[] temp = crypt(rb.array(), headerlen, len, Cipher.DECRYPT_MODE);
            String result = new String(temp, encoding);
            rb.flip();
            rb.position(headerlen + len);
            rb.compact();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        TcpClient client = new TcpClient();
        try {
            client.open("10.15.201.106", 32226);
            String req = JSON.toJSONString(
                    M(
                            "uname", "qqww12",
                            "gettp", 12288,
                            "keys", L("email", "mobile")
                    )
            );
            System.out.println(req);
            String resp = client.userget(req);
            System.out.println(resp);

            req = JSON.toJSONString(
                    M(
                            "gensfx", "rand",
                            "keys", L(M("mobile", "13901231234", "flush", true))
                    )
            );
            System.out.println(req);
            resp = client.adduserex(req);
            System.out.println(resp);
        } finally {
            client.close();
        }
    }

    private String getEncoding(short version) {
        return version == 2 ? "gbk" : "utf-8";
    }

    private void readAtLeast(int len) throws IOException {
        while (rb.position() < len) {
            int n = conn.read(rb);
            if (n <= 0) {
                throw new EOFException();
            }
        }
    }


    private byte[] crypt(byte[] buffer, int start, int len, int mode) {
        try {
            ByteBuffer temp = ByteBuffer.allocate(len);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            cipher.init(mode, key, iv);
            int size = len / blockSize * blockSize;
            byte[] encrypted = cipher.doFinal(buffer, start, size);
            temp.put(encrypted);
            int remain = len - size;
            if (remain != 0) {
                temp.put(buffer, start + len - remain, remain);
            }
            return temp.array();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> M(Object... args) {
        Map<String, Object> result = new TreeMap<String, Object>();
        String key = "";
        for (int i = 0; i < args.length; ++i) {
            if (i % 2 == 0) {
                key = args[i].toString();
            } else {
                result.put(key, args[i]);
            }
        }
        return result;
    }

    private static Object[] L(Object... args) {
        return args;
    }
}

