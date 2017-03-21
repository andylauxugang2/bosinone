package com.guangbei.bosinone.core.manager.model.wireless;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guangbei.bosinone.core.common.model.json.RangeModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 {
     "Months": [{ //期限:1-5个月
        "Begin": 1,
        "End": 5
     }],
     "BorrowCount": [{成功借款次数:2-5次 和 >=6次
        "Begin": 2,
        "End": 5
     }, {
        "Begin": 6,
        "End": 0
     }],
     "CreditCodes": ["AAA"], //魔镜等级:AAA
     "LoanCategory": 8, //风险等级:低风险
     "Amount": { //借款金额:100-500
        "Begin": 100,
        "End": 500
     },
     "ListTypes": [1], //列表类型:安标
     "SortId": 1,
     "PageSize": 10, //每页大小
     "Rates": [{ //利率:14-17%
        "Begin": 14,
        "End": 17
     }],
     "PageIndex": 1,//起始页
     "AuthInfo": [2,3]//认证信息:户籍 和 视频
 }

 * Created by xugang on 2017/3/8.
 */
@Data
public class PPDWirelessGetPersonalLoanListRequest {
    public static final int PAGE_INDEX_START  = 1;
    public static final int PAGE_SIZE_DEFAULT  = 20;

    @JsonProperty(value = "Months")
    private List<RangeModel> months = new ArrayList<>(2); //期限
    @JsonProperty(value = "BorrowCount")
    private List<RangeModel> borrowCount = new ArrayList<>(2); //成功借款次数
    @JsonProperty(value = "Rates")
    private List<RangeModel> rates = new ArrayList<>(2); //利率 %
    @JsonProperty(value = "CreditCodes")
    private List<String> creditCodes = new ArrayList<>(2); //魔镜等级
    @JsonProperty(value = "LoanCategory")
    private int loanCategory; //风险等级
    @JsonProperty(value = "Amount")
    private RangeModel amount = new RangeModel(); //借款金额
    @JsonProperty(value = "ListTypes")
    private List<Integer> listTypes = new ArrayList<>(2); //列表类型 安标-1 赔标-2
    @JsonProperty(value = "AuthInfo")
    private List<Integer> authInfo = new ArrayList<>(2); //认证信息 学历-1 户籍-2 视频-3 人行征信-4
    @JsonProperty(value = "PageSize")
    private int pageSize; //每页大小
    @JsonProperty(value = "PageIndex")
    private int pageIndex; //起始页

    public enum LoanCategoryEnum {
        LOW_RISK(8, "低风险"), MID_RISK(4, "中风险"), HIGH_RISK(5, "高风险"), UNKOWN(-999, "未知");
        private int code;
        private String desc;
        LoanCategoryEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public int getCode() {return code;}
        public String getDesc() {return desc;}
        public static LoanCategoryEnum getByCode(int code) {
            for (LoanCategoryEnum item : LoanCategoryEnum.values()) {
                if (item.getCode() == code) {
                    return item;
                }
            }
            return UNKOWN;
        }
    }

    public enum CreditCodeEnum {
        AAA("AAA", "魔镜等级AAA-安标"), AA("AA", "魔镜等级AA-赔标"), UNKOWN("-999", "未知");
        private String code;
        private String desc;
        CreditCodeEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public String getCode() {return code;}
        public String getDesc() {return desc;}
        public static CreditCodeEnum getByCode(String code) {
            for (CreditCodeEnum item : CreditCodeEnum.values()) {
                if (item.getCode().equalsIgnoreCase(code)) {
                    return item;
                }
            }
            return UNKOWN;
        }
    }

    public enum ListTypeEnum {
        AN(1, "安标"), PEI(2, "赔标"), PERSONAL_CREDIT(3, "个人信用标"), E_BUSS(4, "网商标"), UNKOWN(-999, "未知");
        private int code;
        private String desc;
        ListTypeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public int getCode() {return code;}
        public String getDesc() {return desc;}
        public static ListTypeEnum getByCode(int code) {
            for (ListTypeEnum item : ListTypeEnum.values()) {
                if (item.getCode() == code) {
                    return item;
                }
            }
            return UNKOWN;
        }
    }

    //认证信息 学历-1 户籍-2 视频-3 人行征信-4
    public enum AuthInfoEnum {
        EDU(1, "学历"), HOUSE(2, "户籍"),VIDEO(3, "视频"), BANK_CREDIT(4, "人行征信"), UNKOWN(-999, "未知");
        private int code;
        private String desc;
        AuthInfoEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public int getCode() {return code;}
        public String getDesc() {return desc;}
        public static AuthInfoEnum getByCode(int code) {
            for (AuthInfoEnum item : AuthInfoEnum.values()) {
                if (item.getCode() == code) {
                    return item;
                }
            }
            return UNKOWN;
        }
    }

}
