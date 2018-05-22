package tech.ganyaozi.bean.consts;

/**
 * 微信API中的各项基本常量
 *
 * @author Derek.P.Dai[dp419936514@gmail.com] at 2018/5/18 14:35
 **/

public class WechatConsts {

    public static final String WX_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    /**
     * 获取用户授权时，返回结果存放 错误信息的 key
     */
    public static final String RESPONSE_PARAM_NAME_ERROR_MSG = "errmsg";
    /**
     * 刷新用户授权时，返回结果存放 错误代码的 key
     */
    public static final String RESPONSE_PARAM_NAME_ERROR_CODE = "errcode";

    /**
     * 微信各个API返回结果的错误码
     */
    @SuppressWarnings("unused")
    public enum WechatErrorCode {
        /**
         * 未知错误
         */
        UNKNOWN(-1, "系统繁忙，此时请开发者稍候再试"),
        SUCCESS(0, "请求成功"),

        BAD_ACCESS_TOKEN(40001, "获取 access_token 时 AppSecret 错误，或者 access_token 无效。请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的公众号调用接口"),
        INVALID_GRANT_TYPE(40002, "不合法的凭证类型"),
        INVALID_OPEN_ID(40003, "不合法的 OpenID ，请开发者确认 OpenID （该用户）是否已关注公众号，或是否是其他公众号的 OpenID"),
        INVALID_MEDIA_TYPE(40004, "不合法的媒体文件类型"),
        INVALID_FILE_TYPE(40005, "不合法的文件类型"),
        INVALID_FILE_SIZE(40006, "不合法的文件大小"),
        INVALID_MEDIA_ID(40007, "不合法的媒体文件 id"),
        INVALID_MESSAGE_TYPE(40008, "不合法的消息类型"),
        INVALID_IMAGE_SIZE(40009, "不合法的图片文件大小"),
        INVALID_VOICE_SIZE(40010, "不合法的语音文件大小"),
        INVALID_VIDEO_SIZE(40011, "不合法的视频文件大小"),
        INVALID_THUMBNAIL_SIZE(40012, "不合法的缩略图文件大小"),
        INVALID_APP_ID(40013, "不合法的 AppID ，请开发者检查 AppID 的正确性，避免异常字符，注意大小写"),
        INVALID_ACCESS_TOKEN(40014, "不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
        INVALID_MENU_TYPE(40015, "不合法的菜单类型"),
        INVALID_BUTTON_NUMBER(40016, "不合法的按钮个数"),
        INVALID_BUTTON_TYPE(40017, "不合法的按钮类型"),
        INVALID_BUTTON_NAME_LENGTH(40018, "不合法的按钮名字长度"),
        INVALID_BUTTON_KEY_LENGTH(40019, "不合法的按钮 KEY 长度"),
        INVALID_BUTTON_URL_LENGTH(40020, "不合法的按钮 URL 长度"),
        INVALID_MENU_VERSION(40021, "不合法的菜单版本号"),
        INVALID_SUB_MENU_LEVEL(40022, "不合法的子菜单级数"),
        INVALID_SUB_MENU_BUTTON_COUNT(40023, "不合法的子菜单按钮个数"),
        INVALID_SUB_MENU_BUTTON_TYPE(40024, "不合法的子菜单按钮类型"),
        INVALID_SUB_MENU_BUTTON_NAME_LENGTH(40025, "不合法的子菜单按钮名字长度"),
        INVALID_SUB_MENU_BUTTON_KEY_LENGTH(40026, "不合法的子菜单按钮 KEY 长度"),
        INVALID_SUB_MENU_BUTTON_URL_LENGTH(40027, "不合法的子菜单按钮 URL 长度"),
        INVALID_MENU_USER(40028, "不合法的自定义菜单使用用户"),
        INVALID_OAUTH_CODE(40029, "不合法的 oauth_code"),
        INVALID_REFRESH_TOKEN(40030, "不合法的 refresh_token"),
        INVALID_OPEN_ID_LIST(40031, "不合法的 openid 列表"),
        INVALID_OPEN_ID_LIST_SIZE(40032, "不合法的 openid 列表长度"),
        INVALID_PARAM_RESERVED_WORD(40033, "不合法的请求字符，不能包含\\uxxxx 格式的字符"),
        INVALID_PARAM(40035, "不合法的参数"),
        INVALID_REQUEST_TYPE(40038, "不合法的请求格式"),
        INVALID_URL_LENGTH(40039, "不合法的 URL 长度"),
        INVALID_GROUP_ID(40050, "不合法的分组 id"),
        INVALID_GROUP_NAME(40051, "分组名字不合法"),
        INVALID_ARTICLE_IDX(40060, "删除单篇图文时，指定的 article_idx 不合法"),
        INVALID_GROUP(40117, "分组名字不合法"),
        INVALID_MEDIA_ID_SIZE(40118, "media_id 大小不合法"),
        INVALID_BUTTON_TYPE_1(40119, "button 类型错误"),
        INVALID_BUTTON_TYPE_2(40120, "button 类型错误"),
        INVALID_MEDIA_ID_TYPE(40121, "不合法的 media_id 类型"),
        INVALID_APP_NAME(40132, "微信号不合法"),
        INVALID_IMAGE_TYPE(40137, "不支持的图片格式"),

        FORBID_ADD_OTHER_APP_INDEX(40155, "请勿添加其他公众号的主页链接"),

        MISSING_ACCESS_TOKEN(41001, "缺少 access_token 参数"),
        MISSING_APP_ID(41002, "缺少 appid 参数"),
        MISSING_REFRESH_TOKEN(41003, "缺少 refresh_token 参数"),
        MISSING_SECRET(41004, "缺少 secret 参数"),
        MISSING_MEDIA_DATA(41005, "缺少多媒体文件数据"),
        MISSING_MEDIA_ID(41006, "缺少 media_id 参数"),
        MISSING_SUB_MENU(41007, "缺少子菜单数据"),
        MISSING_OAUTH_CODE(41008, "缺少 oauth code"),
        MISSING_OPEN_ID(41009, "缺少 openid"),

        EXPIRED_ACCESS_TOKEN(42001, "access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对 access_token 的详细机制说明"),
        EXPIRED_REFRESH_TOKEN(42002, "refresh_token 超时"),
        EXPIRED_OAUTH_CODE(42003, "oauth_code 超时"),
        EXPIRED_ACCESS_TOKEN_AND_REFRESH_TOKEN(42007, "用户修改微信密码， accesstoken 和 refreshtoken 失效，需要重新授权"),

        NEED_GET_METHOD(43001, "需要 GET 请求"),
        NEED_POST_METHOD(43002, "需要 POST 请求"),
        NEED_HTTPS_PROTOCOL(43003, "需要 HTTPS 请求"),
        NEED_SUBSCRIBE(43004, "需要接收者关注"),
        NEED_FRIEND_RELATIONSHIP(43005, "需要好友关系"),
        NEED_MOVE_FROM_BLACK_LIST(43019, "需要将接收者从黑名单中移除"),

        EMPTY_MEDIA_FILE(44001, "多媒体文件为空"),
        EMPTY_POST_DATA(44002, "POST 的数据包为空"),
        EMPTY_NEWS(44003, "图文消息内容为空"),
        EMPTY_TEXT(44004, "文本消息内容为空"),

        OVER_LIMIT_MEDIA(45001, "多媒体文件大小超过限制"),
        OVER_LIMIT_MESSAGE(45002, "消息内容超过限制"),
        OVER_LIMIT_TITLE(45003, "标题字段超过限制"),
        OVER_LIMIT_DESCRIPTION(45004, "描述字段超过限制"),
        OVER_LIMIT_LINK(45005, "链接字段超过限制"),
        OVER_LIMIT_IMAGE_LINK(45006, "图片链接字段超过限制"),
        OVER_LIMIT_VOICE_LENGTH(45007, "语音播放时间超过限制"),
        OVER_LIMIT_NEWS(45008, "图文消息超过限制"),
        OVER_LIMIT_API_DAILY_COUNT(45009, "接口调用超过限制"),
        OVER_LIMIT_MENU_SIZE(45010, "创建菜单个数超过限制"),

        API_REQUEST_TOO_FREQUENT(45011, "API 调用太频繁，请稍候再试"),
        TOO_LATE_TO_REPLY(45015, "回复时间超过限制"),
        IMMUTABLE_SYSTEM_GROUP(45016, "系统分组，不允许修改"),

        OVER_LIMIT_GROUP_NAME(45017, "分组名字过长"),
        OVER_LIMIT_GROUP_SIZE(45018, "分组数量超过上限"),
        OVER_LIMIT_CUSTOMER_SERVICE_REPLY(45047, "客服接口下行条数超过上限"),

        NON_EXISTENT_MEDIA(46001, "不存在媒体数据"),
        NON_EXISTENT_MENU_VERSION(46002, "不存在的菜单版本"),
        NON_EXISTENT_MENU_DATA(46003, "不存在的菜单数据"),
        NON_EXISTENT_USER(46004, "不存在的用户"),

        FAIL_TO_PARSE_JSON_XML(47001, "解析 JSON/XML 内容错误"),
        API_NOT_AUTHORISED(48001, "api 功能未授权，请确认公众号已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限"),
        SUBSCRIBER_REFUSE_MESSAGE(48002, "粉丝拒收消息（粉丝在公众号选项中，关闭了 “ 接收消息 ” ）"),

        API_FORBID(48004, "api 接口被封禁，请登录 mp.weixin.qq.com 查看详情"),
        API_FORBID_TO_DELETE_REFEREE_MATERIAL(48005, "api 禁止删除被自动回复和自定义菜单引用的素材"),
        API_FORBID_TO_FLUSH_COUNT(48006, "api 禁止清零调用次数，因为清零次数达到上限"),

        NOT_AUTHORISED_MESSAGE_TYPE(48008, "没有该类型消息的发送权限"),
        NOT_AUTHORISED_API_BY_USER(50001, "用户未授权该 api"),
        NOT_AUTHORISED_USER(50002, "用户受限，可能是违规后接口被封禁"),
        NOT_SUBSCRIBE(50005, "用户未关注公众号"),

        KF_INVALID_PARAM(61451, "参数错误 (invalid parameter)"),
        KF_INVALID_KF_ACCOUNT(61452, "无效客服账号 (invalid kf_account)"),
        KF_EXISTED_ACCOUNT(61453, "客服帐号已存在 (kf_account exsited)"),
        KF_OVER_LIMIT_ACCOUNT_NAME(61454, "客服帐号名长度超过限制 ( 仅允许 10 个英文字符，不包括 @ 及 @ 后的公众号的微信号 )(invalid kf_acount length)"),
        KF_INVALID_ACCOUNT_NAME(61455, "客服帐号名包含非法字符 ( 仅允许英文 + 数字 )(illegal character in kf_account)"),
        KF_ACCOUNT_COUNT_EXCEEDED(61456, "客服帐号个数超过限制 (10 个客服账号 )(kf_account count exceeded)"),
        KF_INVALID_FILE_TYPE(61457, "无效头像文件类型 (invalid file type)"),
        KF_SYSTEM_ERROR(61450, "系统错误 (system error)"),

        INVALID_DATE_FORMAT(61500, "日期格式错误"),
        MENU_NON_EXISTENT_MENU_ID(65301, "不存在此 menuid 对应的个性化菜单"),
        MENU_NON_EXISTENT_USER_ACCOUNT(65302, "没有相应的用户"),
        MENU_FAIL_TO_CREATE_MENU_WITHOUT_DEFAULT(65303, "没有默认菜单，不能创建个性化菜单"),
        MENU_EMPTY_MATCH_RULE(65304, "MatchRule 信息为空"),
        MENU_COUNT_LIMITED(65305, "个性化菜单数量受限"),
        MENU_UNSUPPORTED_ACCOUNT(65306, "不支持个性化菜单的帐号"),
        MENU_EMPTY_MENU_INFO(65307, "个性化菜单信息为空"),
        MENU_BUTTON_OF_NO_TYPE(65308, "包含没有响应类型的 button"),
        MENU_FORBID(65309, "个性化菜单开关处于关闭状态"),

        EMPTY_COUNTRY_INFO(65310, "填写了省份或城市信息，国家信息不能为空"),
        EMPTY_PROVINCE_INFO(65311, "填写了城市信息，省份信息不能为空"),
        INVALID_COUNTRY_INFO(65312, "不合法的国家信息"),
        INVALID_PROVINCE_INFO(65313, "不合法的省份信息"),
        INVALID_CITY_INFO(65314, "不合法的城市信息"),
        OVER_LIMIT_FOREIGN_DOMAIN(65316, "该公众号的菜单设置了过多的域名外跳（最多跳转到 3 个域名的链接）"),
        INVALID_URL(65317, "不合法的 URL"),

        NEED_UPDATE_TO_NEW_KF_SYSTEM(65400, "API不可用，即没有开通或升级到新版客服功能"),
        KF_INVALID_ACCOUNT(65401, "无效客服帐号"),
        KF_ACCOUNT_NOT_BINDED(65402, "客服帐号尚未绑定微信号，不能投入使用"),
        KF_NON_EXISTENT_USER_SESSION(65413, "不存在对应用户的会话信息"),
        KF_SUBSCRIBER_ACCEPTED_BY_OTHERS(65414, "粉丝正在被其他客服接待"),
        KF_ACCOUNT_OFFLINE(65415, "指定的客服不在线"),
        KF_INVALID_OPEN_ID(40003, "非法的openid"),
        INVALID_POST_DATA(90010, "	POST 数据参数不合法"),

        UNAVAILABLE_REMOTE_SERVICE(9001002, "	远端服务不可用"),
        INVALID_TICKET(9001003, "Ticket 不合法"),
        FAIL_TO_GET_SURROUNDING_USER(9001004, "获取摇周边用户信息失败"),
        FAIL_TO_GET_SHOP_INFO(9001005, "获取商户信息失败"),
        FAIL_TO_GET_OPEN_ID(9001006, "获取 OpenID 失败"),

        UPLOAD_FILE_MISSING(9001007, "上传文件缺失"),
        UPLOAD_FILE_TYPE_INVALID(9001008, "上传素材的文件类型不合法"),
        UPLOAD_FILE_SIZE_INVALID(9001009, "上传素材的文件尺寸不合法"),
        UPLOAD_FAIL(9001010, "上传失败"),

        DEVICE_INVALID_ACCOUNT(9001020, "帐号不合法"),
        DEVICE_FAIL_TO_ADD_DEVICE(9001021, "已有设备激活率低于 50% ，不能新增设备"),
        DEVICE_INVALID_COUNT(9001022, "设备申请数不合法，必须为大于 0 的数字"),
        DEVICE_ID_EXISTED(9001023, "已存在审核中的设备 ID 申请"),
        DEVICE_QUERY_COUNT_OVER_50(9001024, "一次查询设备 ID 数量不能超过 50"),
        DEVICE_INVALID_DEVICE_ID(9001025, "设备 ID 不合法"),
        DEVICE_INVALID_PAGE_ID(9001026, "页面 ID 不合法"),
        DEVICE_INVALID_PAGE_PARAM(9001027, "页面参数不合法"),
        DEVICE_DELETE_PAGE_OVER_10(9001028, "一次删除页面 ID 数量不能超过 10"),
        DEVICE_BINDED_BEFORE_DELETE(9001029, "页面已应用在设备中，请先解除应用关系再删除"),
        DEVICE_QUERY_PAGE_USER_50(9001030, "一次查询页面 ID 数量不能超过 50"),
        DEVICE_INVALID_TIME_ZONE(9001031, "时间区间不合法"),
        DEVICE_INVALID_PARAM_BIND_DEVICE_PAGE(9001032, "保存设备与页面的绑定关系参数错误"),
        DEVICE_INVALID_SHOP_ID(9001033, "门店 ID 不合法"),
        DEVICE_INVALID_DEVICE_COMMENT_LENGTH(9001034, "设备备注信息过长"),
        DEVICE_INVALID_ADD_PARAM(9001035, "设备申请参数不合法"),
        DEVICE_INVALID_QUERY_PARAM_BEGIN(9001036, "查询起始值 begin 不合法");

        private final int code;
        private final String description;


        WechatErrorCode(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getValue() {
            return this.code;
        }

        @Override
        public String toString() {
            return "WechatErrorCode : { code = " + code + " , description = '" + description + "' }";
        }
    }
}
