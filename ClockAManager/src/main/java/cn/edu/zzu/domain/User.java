package cn.edu.zzu.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String uid;
    private String upw;
    private String myvs_1;
    private String myvs_2;
    private String myvs_3;
    private String myvs_4;
    private String myvs_5;
    private String myvs_6;
    private String myvs_7;
    private String myvs_8;
    private String myvs_9;
    private String myvs_10;
    private String myvs_11;
    private String myvs_12;
    private String myvs_13a;
    private String myvs_13b;
    private String myvs_13c;
    private String myvs_14;
    private String record;
    private String email;
    private String send;


    /**
     * id	int	主键
     * uid	varchar	用户账号
     * upw	varchar	用户疫情登录密码
     * myvs_1	varchar	1、您今天是否有发热症状
     * myvs_2	varchar	2、您今天是否有咳嗽症状
     * myvs_3	varchar	3、您今天是否有乏力或轻微乏力症状
     * myvs_4	varchar	4、您今天是否有鼻塞、流涕、咽痛或者腹泻等症状
     * myvs_5	varchar	5、您今天是否被所在地医疗机构确定为确诊病例?
     * myvs_6	varchar	6、您今天是否被所在地医疗机构确定为疑似病例?
     * myvs_7	varchar	7、您今天是否被所在地政府或医疗卫生部门确定为密切接触者?
     * myvs_8	varchar	8、您今天是否被所在地医疗机构进行院内隔离观察治疗?
     * myvs_9	varchar	9、您今天是否被要求在政府集中隔离点进行隔离观察?
     * myvs_10	varchar	10、您今天是否被要求在政府集中隔离点进行隔离观察?
     * myvs_11	varchar	11、所在小区（村）是否有确诊病例?(以当地政府公开信息为准)
     * myvs_12	varchar	12、共同居住人是否有确诊病例?
     * myvs_13a	varchar	当前居住地：省份（自治区）
     * myvs_13b	varchar	当前居住地：地市
     * myvs_13c	varchar	当前居住地：具体
     * myvs_14	varchar	14、您是否为当日返郑人员
     * record	varchar	0 表示未打卡，1表示打卡
     * email	varchar	qq邮箱
     * send	varchar	0 表示未发送，1表示发送
     */
}
