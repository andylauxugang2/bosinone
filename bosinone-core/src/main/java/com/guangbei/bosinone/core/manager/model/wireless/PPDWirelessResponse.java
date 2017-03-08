package com.guangbei.bosinone.core.manager.model.wireless;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
public class PPDWirelessResponse {
    @JsonProperty(value = "Result")
    private int result; //响应状态码
    @JsonProperty(value = "ResultMessage")
    private String resultMessage; //响应信息

}
