package requisicaosocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 *
 * @author Vinicius Borsato
 */
public class Main {

    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");
        SocketChannel channel = null;

        try {
            InetSocketAddress socketAddr = new InetSocketAddress("localhost", 80);

            channel = SocketChannel.open(socketAddr);
            channel.write(charset.encode("GET /socket-channel.php?msg=Escreva-isso\r\n\r\n"));
            ByteBuffer buffer = ByteBuffer.allocate(2048);

            while ((channel.read(buffer)) != -1) {
                buffer.flip();
                System.err.println(charset.decode(buffer));
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
