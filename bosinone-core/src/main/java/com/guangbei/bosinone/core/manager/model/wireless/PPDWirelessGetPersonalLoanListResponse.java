package com.guangbei.bosinone.core.manager.model.wireless;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by xugang on 17/1/4.
 *
 {
 "BidProductCount": 3,
 "IsCanOnekeyInvest": true,
 "Items": [
     {
         "Listingid": 36828952,
         "Borrowernumber": 24706138,
         "Investmentterm": 6,
         "Title": "pdu8076121755的应收款安全标",
         "Safegrade": "AAA",
         "Labels": "安",
         "Annualinterestrate": 7.02,
         "Quota": 19999,
         "Rateprogress": 71,
         "Bidamount": 0,
         "Ordernum": 1
     },
     {
         "Listingid": 36835291,
         "Borrowernumber": 8024222,
         "Investmentterm": 6,
         "Title": "pdu61373241的应收款安全标",
         "Safegrade": "AAA",
         "Labels": "安",
         "Annualinterestrate": 7,
         "Quota": 3353,
         "Rateprogress": 40,
         "Bidamount": 0,
         "Ordernum": 2
     },
     {
         "Listingid": 36794628,
         "Borrowernumber": 36872688,
         "Investmentterm": 6,
         "Title": "pdu3556151153的应收款安全标",
         "Safegrade": "AAA",
         "Labels": "安",
         "Annualinterestrate": 7,
         "Quota": 50000,
         "Rateprogress": 6,
         "Bidamount": 0,
         "Ordernum": 3
     }
 ],
 "ActivityProductItems": [],
 "AutoBidStatus": -1,
 "OneInvestCount": 100,
 "Result": 0,
 "ResultMessage": null,
 "ResultCode": null
 }
 */
@Data
public class PPDWirelessGetPersonalLoanListResponse extends PPDWirelessResponse {
    @JsonProperty(value = "BidProductCount")
    private int bidProductCount; //总标的个数
    @JsonProperty(value = "IsCanOnekeyInvest")
    private boolean isCanOnekeyInvest; //是否可以一键投标
    @JsonProperty(value = "Items")
    private List<Item> items;

    @Data
    public static class Item {
        @JsonProperty(value = "Listingid")
        private Integer listingId; //item id用于投标接口使用
        @JsonProperty(value = "Borrowernumber")
        private Integer borrowerNumber;
        @JsonProperty(value = "Investmentterm")
        private int investmentterm; //投资期限 月
        @JsonProperty(value = "Title")
        private String title;
        @JsonProperty(value = "Safegrade")
        private String safeGrade; //AAA AA D
        @JsonProperty(value = "Labels")
        private String labels; //安/赔
        @JsonProperty(value = "Annualinterestrate")
        private Integer annualInterestRate; //年收益率
        @JsonProperty(value = "Rateprogress")
        private Integer rateProgress; //投标进度
        @JsonProperty(value = "Quota")
        private Integer quota; //标的配额
        @JsonProperty(value = "Ordernum")
        private Integer orderNum; //顺序号
    }

}
