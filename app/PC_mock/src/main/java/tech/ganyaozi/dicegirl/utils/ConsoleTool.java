package tech.ganyaozi.dicegirl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleTool {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleTool.class);

    public static final boolean ADD_SERIAL_NUMBER = true;
    public static final boolean NO_SERIAL_NUMBER = false;

    private static String banner = "================================================================";
    private static String border = "|";
    private static String seperator = " : ";
    private static int indent = 4; // 向左缩进的字符数
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



    /**
     * @param strs         Strings to be shown
     * @param addSerialNum if need to show serial number before section, default is true
     * @return index the number scanned in, or -1 if input is invalid
     */
    public static int showInConsole(ArrayList<?> strs, boolean addSerialNum) {
        try {
            showTables(strs, addSerialNum);

            String order = bufferedReader.readLine();
            if (order == null) {
                return -1;
            }
            if (order.matches("[0-9]+")) {
                int orderInt = Integer.valueOf(order);
                if (orderInt < strs.size() && orderInt > -1) {
                    logger.info("\n\n");
                    return orderInt;
                } else {
                    logger.error("Invalid number ! please input the number before selection \n");
                    return -1;
                }
            } else if (order.equals("exit") || order.equals("quit")) {
                return -1;
            } else {
                logger.error("Invalid number ! please input the number before selection \n");
                return -1;
            }
        } catch (NumberFormatException e) {
            logger.error("Number is too large");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int showInConsole(ArrayList<?> strs) {
       return showInConsole(strs,true);
    }

    private static void showTables(ArrayList<?> list, boolean AddNum) {
        logger.info(banner);
        for (int i = 0; i < list.size(); i++) {
            StringBuilder name;
            if (AddNum) {
                name = new StringBuilder(i + seperator + list.get(i));
            } else {
                name = new StringBuilder(list.get(i).toString());
            }
            int rightSpace = banner.length() - name.toString().length() - indent - 2;
            String line = border + mkSpacce(indent) + name.toString() + mkSpacce(rightSpace) + border;
            logger.info(line);
        }
        logger.info(banner);
        logger.info("\n->");
    }

    private static String mkSpacce(int i) {
        StringBuilder space = new StringBuilder();
        for (; i > 0; i--) {
            space.append(" ");
        }
        return space.toString();
    }

    public static void getNextCommand() {
        logger.info("Test Complete ! \n\n");
    }

    public static void displayOneLine(String message) {
        logger.info(message);
    }

    public static String readLine() {
        String input = null;
        do {
            try {
                input = bufferedReader.readLine();
                if (input == null) {
                    logger.error("invalid input : null");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (input == null);
        return input;

    }

    public static int readSteamId() {
        int input = -1;
        String temp = null;
        do {
            try {
                temp = bufferedReader.readLine();
                if (temp == null) {
                    logger.error("invalid input : null");
                }
                input = Integer.valueOf(temp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                logger.error("Invalid input :" + temp);
                logger.error("Please type in a valid steamId");
            }
        } while (input == -1);
        return input;
    }
}
