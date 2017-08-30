package tech.ganyaozi.dicegirl.command;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 * @date 2017/8/30 16:26
 **/
public enum IMCommand {
    IM_COMMAND_UNKNOWN(Module.DEAD_LETTER, 0, true),

    IM_PING_PONG(Module.PING_PONG, 0, true),

    IM_SECURE_KEY(Module.SECURE, 0, true),

    /******************************* room related start***************************/
    IM_CREATE_ROOM_REQ(Module.ROOM, 0, true),
    IM_CREATE_ROOM_RES(Module.ROOM, 0, false),

    IM_ENTER_ROOM_REQ(Module.ROOM, 1, true),
    IM_ENTER_ROOM_RES(Module.ROOM, 1, false),

    IM_UPDATE_ROOM_INFO_REQ(Module.ROOM, 2, true),
    IM_UPDATE_ROOM_INFO_RES(Module.ROOM, 2, false),


    IM_MODIFY_ROOM_INFO_REQ(Module.ROOM, 3, true),
    IM_MODIFY_ROOM_INFO_RES(Module.ROOM, 3, false);


    /******************************* room related  end ***************************/


    private final Module module;
    private final int action;
    private final boolean isReq;

    IMCommand(Module module, int action, boolean isReq) {
        this.module = module;
        this.action = action;
        this.isReq = isReq;
    }

    public static IMCommand fromValue(int value) {
        for (IMCommand cmd : IMCommand.values()) {
            if (cmd.getValue() == value) {
                return cmd;
            }
        }
        return IM_COMMAND_UNKNOWN;
    }

    public static void main(String... args) {
        for (IMCommand cmd : IMCommand.values()) {
            System.out.println(" cmd : " + cmd.name() + "   value : " + cmd.getValue());
        }
    }

    public int getValue() {
        return (this.module.value << 4) + (this.action << 2) + (this.isReq ? 0 : 1);
    }

    public Module getModule() {
        return module;
    }

    public int getAction() {
        return action;
    }

    public boolean isReq() {
        return isReq;
    }

    public enum Module {
        DEAD_LETTER(0),
        PING_PONG(1),
        SECURE(2),
        ROOM(3);

        private final int value;

        Module(int value) {
            this.value = value;
        }
    }
}
