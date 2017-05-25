package tech.ganyaozi.dicegirl;

import com.google.protobuf.ByteString;
import org.apache.commons.lang3.StringUtils;
import tech.ganyaozi.dicegirl.client.DiceIMClient;
import tech.ganyaozi.dicegirl.proto.BaseMessage;
import tech.ganyaozi.dicegirl.utils.ClientReconnectTask;
import tech.ganyaozi.dicegirl.utils.ConsoleTool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class launcher {

    private static ArrayList<InetSocketAddress> serverList = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException, IOException {
        initServerList();
        ArrayList<String> strs = new ArrayList<>();
        serverList.forEach(inetSocketAddress -> strs.add(inetSocketAddress.toString()));
        int index = ConsoleTool.showInConsole(strs);
        DiceIMClient client = new DiceIMClient();
        ExecutorService excutor = Executors.newSingleThreadExecutor();
        excutor.submit(new ClientReconnectTask(client, serverList.get(index)));
        excutor.submit(() -> client.connect(serverList.get(index)));

        while (true) {
            System.out.println("Please input you message >> ");
            String cmd = ConsoleTool.readLine();
            if (!StringUtils.equals(cmd, "q")) {
                client.channel.writeAndFlush(BaseMessage.baseMessage.newBuilder()
                        .setCmd(BaseMessage.Commands.IM_CREATE_ROOM_REQ)
                        .setAck(false)
                        .setDstID("-1")
                        .setSrcID(UUID.randomUUID().toString())
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
