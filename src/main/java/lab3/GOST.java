package lab3;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class GOST {
    private Mode mode;
    private static String username = System.getProperty("user.name");
    private static File decodedFile = new File("/home/"+username+"/decoded.txt");
    private static File codedFile = new File("/home/"+username+"/coded.txt");
    private DataOutputStream dos;
    private DataInputStream dis;
    GOST(Mode mode, File file, String key){
        this.mode = mode;
        try {
            dis = new DataInputStream(new FileInputStream(file));
            if (mode.equals(Mode.DECRYPT)) {
                dos = new DataOutputStream(new FileOutputStream(decodedFile));
            }else if(mode.equals(Mode.ENCRYPT)){
                dos = new DataOutputStream(new FileOutputStream(codedFile));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        setKey(key);
    }
    private final static byte[][] table = {
            {4, 10, 9, 2, 13, 8, 0, 14, 6, 11, 1, 12, 7, 15, 5, 3},
            {14, 11, 4, 12, 6, 13, 15, 10, 2, 3, 8, 1, 0, 7, 5, 9},
            {5, 8, 1, 13, 10, 3, 4, 2, 14, 15, 12, 7, 6, 0, 9, 11},
            {7, 13, 10, 1, 0, 8, 9, 15, 14, 4, 6, 12, 11, 2, 5, 3},
            {6, 12, 7, 1, 5, 15, 13, 8, 4, 10, 9, 14, 0, 3, 11, 2},
            {4, 11, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, 12, 15, 14},
            {13, 11, 4, 1, 3, 15, 5, 9, 0, 10, 14, 7, 6, 8, 2, 12},
            {1, 15, 13, 0, 5, 7, 10, 4, 9, 2, 3, 14, 6, 11, 8, 12}
    }; //Числовая таблица для шифрования

    private byte[][] key = new byte[8][4];//ключ в виде битов (8 байт)

    public void setKey(String key) {
        int counter = 0;
        byte [][]keyBytes = new byte[8][4];
        for (int i = 0; i < this.key.length; i++) {
            for (int j = 0; j < this.key[i].length; j++) {
                if (counter>=key.length()) counter=0;
                keyBytes[i][j] = (byte) key.charAt(counter);
                counter++;
            }
        }
        this.key = keyBytes;
        System.out.println(Arrays.deepToString(this.key));
    }

    private final static int[] keyMap = {0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            7, 6, 5, 4, 3, 2, 1, 0};

    public void rpz() throws Exception {
        byte[] data = new byte[8];
        int count = dis.read(data);
        while (count != -1) {
            if (count % 8 > 0) {
                for (int i = 8 - count % 8; i < count; i++) {
                    data[i] = 0x0f;
                }
            }
            byte[] B = new byte[4], A = new byte[4];
            System.arraycopy(data, 0, B, 0, 4);
            System.arraycopy(data, 4, A, 0, 4);

            for (int k = 0; k < 32; k++) {
                byte[] K = Mode.ENCRYPT.equals(this.mode) ? key[keyMap[k]] : key[keyMap[31 - k]];
                int buf = ByteBuffer.wrap(A).getInt() + ByteBuffer.wrap(K).getInt();
                buf &= 0xffffffff; // A + K (mod 2^32)
                int[] s = {
                        (buf & 0xF0000000) >>> 28,
                        (buf & 0x0F000000) >>> 24,
                        (buf & 0x00F00000) >>> 20,
                        (buf & 0x000F0000) >>> 16,
                        (buf & 0x0000F000) >>> 12,
                        (buf & 0x00000F00) >>> 8,
                        (buf & 0x000000F0) >>> 4,
                        (buf & 0x0000000F)
                };
                buf = 0x00000000;
                for (int b = 0; b < 8; b++) {
                    buf <<= 4;
                    buf += table[b][s[b] & 0x0000000f];
                }
                buf = ((buf << 11) | (buf >>> 21));
                byte[] resBytes = ByteBuffer.allocate(4).putInt(buf).array();
                byte[] newB = {0x00, 0x00, 0x00, 0x00};

                System.arraycopy(A, 0, newB, 0, 4);
                for (int b = 0; b < 4; b++) {
                    A[b] = (byte) (resBytes[b] ^ B[b]);
                }
                System.arraycopy(newB, 0, B, 0, 4);
            }

            dos.write(A, 0, A.length);
            dos.write(B, 0, B.length);
            count = dis.read(data);
        }
        dis.close();
        dos.close();
    }
}
enum Mode {
    ENCRYPT,
    DECRYPT
}