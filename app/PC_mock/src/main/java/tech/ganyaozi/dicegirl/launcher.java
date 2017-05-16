package tech.ganyaozi.dicegirl;

import org.apache.commons.lang3.StringUtils;
import tech.ganyaozi.dicegirl.client.IMClient;
import tech.ganyaozi.dicegirl.proto.BaseMessage;
import tech.ganyaozi.dicegirl.utils.ConsoleTool;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;

public class launcher {

    private static ArrayList<InetSocketAddress> serverList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        initServerList();
        ArrayList<String> strs = new ArrayList<>();
        serverList.forEach(inetSocketAddress -> strs.add(inetSocketAddress.toString()));
        int index = ConsoleTool.showInConsole(strs);
        IMClient client = new IMClient();
        Executors.newSingleThreadExecutor().submit(() -> client.connect(serverList.get(index)));
        int count = 0;
        while (true) {
            String cmd = ConsoleTool.readLine();
            if (!StringUtils.equals(cmd, "q")) {
                count++;
                client.channel.writeAndFlush(BaseMessage.baseMessage.newBuilder()
                        .setId(count)
                        .setContent(new String(cmd.getBytes(), Charset.forName("UTF-8")))
                        .setTimeStamp(new Date().toString())
                        .build());
            } else {
                break;
            }
        }
    }

    private static void initServerList() {
        serverList.add(new InetSocketAddress("localhost", 44444));
    }

}
