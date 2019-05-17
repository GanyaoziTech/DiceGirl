package tech.ganyaozi.warframe.state.consts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author derek.p.dai at 2019/5/17 16:10
 **/
@Data
@Component
@ConfigurationProperties("warframe-stat")
public class WarframeConst {

    public String domain;

    /**
     * warframe游戏对应的主机平台
     */
    public enum Platform{
        /**
         * PC steam 国际服平台
         */
        PC("pc"),
        /**
         * 索尼ps4 国际服平台
         */
        PS4("ps4"),
        /**
         * 微软xbox 和 xbox one 平台
         */
        XBOX_ONE("xb1"),
        /**
         * 任天堂switch平台
         */
        NINTENDO_SWITCH("swi");

        private String alias;

        Platform(String alias) {
            this.alias = alias;
        }

        public String getAlias() {
            return alias;
        }

        public Platform parse(String alias){
            for (Platform platform : Platform.values()){
                if (StringUtils.equalsIgnoreCase(platform.getAlias(),alias)){
                    return platform;
                }
            }
            throw new RuntimeException("cannot parse " + alias + " as a valid warframe platform");
        }
    }
}
