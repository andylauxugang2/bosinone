package com.guangbei.bosinone.core.test.manager;

import com.guangbei.bosinone.core.common.model.json.RangeModel;
import com.guangbei.bosinone.core.manager.PPDPersonalLoanManager;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListRequest;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xugang on 2017/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合
@ContextConfiguration(locations = "classpath:spring-all-test.xml") // 加载配置
public class PPDPersonalLoanManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(PPDPersonalLoanManagerTest.class);

    @Autowired // 注入
    private PPDPersonalLoanManager ppdPersonalLoanManager;

    @Test
    public void test_bidProductList() throws Exception {
        Long userId = 1L;
        String ppduname = "pdutest1";
        PPDWirelessGetPersonalLoanListRequest request = new PPDWirelessGetPersonalLoanListRequest();
        //低风险
        request.setLoanCategory(PPDWirelessGetPersonalLoanListRequest.LoanCategoryEnum.LOW_RISK.getCode());
        request.setPageIndex(PPDWirelessGetPersonalLoanListRequest.PAGE_INDEX_START);
        request.setPageSize(PPDWirelessGetPersonalLoanListRequest.PAGE_SIZE_DEFAULT);
        //AAA
        request.getCreditCodes().add(PPDWirelessGetPersonalLoanListRequest.CreditCodeEnum.AAA.getCode());
        //赔标
        request.getListTypes().add(PPDWirelessGetPersonalLoanListRequest.ListTypeEnum.AN.getCode());
        //利率 6%-13%,>= 22%
        request.getRates().add(new RangeModel(6, 13));
//        request.getRates().add(new RangeModel(22, 0));
        //期限 1-5个月,6个月,>12个月
        request.getMonths().add(new RangeModel(1, 5));
        request.getMonths().add(new RangeModel(6, 6));
        request.getMonths().add(new RangeModel(13, 0));
        //认证信息 学历认证 户籍认证
        request.getAuthInfo().add(PPDWirelessGetPersonalLoanListRequest.AuthInfoEnum.EDU.getCode());
        request.getAuthInfo().add(PPDWirelessGetPersonalLoanListRequest.AuthInfoEnum.HOUSE.getCode());
        //成功借款次数  首次,>=6次
        request.getBorrowCount().add(new RangeModel(1, 1));
        request.getBorrowCount().add(new RangeModel(6, 0));
        //借款金额
        request.getAmount().setBegin(200);
        request.getAmount().setEnd(500000);
        PPDWirelessGetPersonalLoanListResponse response = ppdPersonalLoanManager.bidProductList(userId, ppduname, request);
        logger.info("test response={}", response);

        if (response.getResult() == -1) {
            return;
        }

        //一个线程 按页读 比较慢 多个线程分页读 比较快 但要注意去重
        logger.info("总标的个数:{}", response.getBidProductCount());
        while (request.getPageIndex() * request.getPageSize() < response.getBidProductCount()) {
            //继续获取
            request.setPageIndex(request.getPageIndex() + 1);
            response = ppdPersonalLoanManager.bidProductList(userId, ppduname, request);
            logger.info("总标的个数:{}", response.getBidProductCount());
        }

    }

}
