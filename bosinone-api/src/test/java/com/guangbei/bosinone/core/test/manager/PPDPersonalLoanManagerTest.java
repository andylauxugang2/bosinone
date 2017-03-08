package com.guangbei.bosinone.core.test.manager;

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
        PPDWirelessGetPersonalLoanListResponse response = ppdPersonalLoanManager.bidProductList(userId, ppduname, request);
        logger.info("test response={}", response);
    }

}
