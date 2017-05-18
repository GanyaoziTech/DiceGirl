package tech.ganyaozi.dicegirl;

import com.google.protobuf.ByteString;
import org.apache.commons.lang3.StringUtils;
import tech.ganyaozi.dicegirl.client.DiceIMClient;
import proto.BaseMessage;
import tech.ganyaozi.dicegirl.utils.ConsoleTool;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;

public class launcher {

    private static ArrayList<InetSocketAddress> serverList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        initServerList();
        ArrayList<String> strs = new ArrayList<>();
        serverList.forEach(inetSocketAddress -> strs.add(inetSocketAddress.toString()));
        int index = ConsoleTool.showInConsole(strs);
        DiceIMClient client = new DiceIMClient();
        Executors.newSingleThreadExecutor().submit(() -> client.connect(serverList.get(index)));
        int count = 0;
        while (true) {
            String cmd = ConsoleTool.readLine();
            if (!StringUtils.equals(cmd, "q")) {
                count++;
                client.channel.writeAndFlush(BaseMessage.baseMessage.newBuilder()
                                .setCmd(count)
                                .setUserID(UUID.randomUUID().toString())
                                .setTimeStamp(new Date().getTime())
                                .setContent(ByteString.copyFrom(cmd.getBytes()))
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
