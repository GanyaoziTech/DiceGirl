package tech.ganyaozi.dicegirl;

import org.apache.commons.lang3.StringUtils;
import tech.ganyaozi.dicegirl.client.IMClient;
import tech.ganyaozi.dicegirl.proto.BaseMessage;
import tech.ganyaozi.dicegirl.utils.ConsoleTool;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class launcher {

    private static ArrayList<InetSocketAddress> serverList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        initServerList();
        ArrayList<String> strs = new ArrayList<>();
        serverList.forEach(inetSocketAddress -> strs.add(inetSocketAddress.toString()));
        int index = ConsoleTool.showInConsole(strs);
        IMClient client = new IMClient();
        client.connect(serverList.get(index));
        while(true){
            String cmd = ConsoleTool.readLine();
            if (!StringUtils.equals(cmd,"q")){
                client.channel.writeAndFlush(BaseMessage.baseMessage.getDefaultInstance());
            }else {
                break;
            }
        }
    }
    private static void initServerList() {
        serverList.add(new InetSocketAddress("localhost", 44444));
    }

}
