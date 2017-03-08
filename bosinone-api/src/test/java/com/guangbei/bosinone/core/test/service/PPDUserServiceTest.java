package com.guangbei.bosinone.core.test.service;

import com.guangbei.bosinone.client.param.PPDGetValidCodeParam;
import com.guangbei.bosinone.client.param.PPDLoginParam;
import com.guangbei.bosinone.client.param.PPDValidCodeParam;
import com.guangbei.bosinone.client.result.PPDUserResult;
import com.guangbei.bosinone.core.service.PPDUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by xugang on 2017/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 整合
@ContextConfiguration(locations = "classpath:spring-all-test.xml") // 加载配置
public class PPDUserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(PPDUserServiceTest.class);

    @Autowired // 注入
    private PPDUserService ppdUserService;

    @Test
    public void test_getValidateCode() throws Exception {
        PPDGetValidCodeParam ppdGetValidCodeParam = new PPDGetValidCodeParam();
        ppdGetValidCodeParam.setUserId(2L);
        PPDUserResult result = ppdUserService.getValidateCode(ppdGetValidCodeParam);
        logger.info("test result={}", result.toString());
        if (result.isSuccess()) {
            Files.write(Paths.get("/Users/xugang/Desktop/image.jpg"), result.getValidCode());
        }
    }

    @Test
    public void test_checkValidateCode() throws Exception {
        PPDValidCodeParam param = new PPDValidCodeParam();
        param.setUserId(2L);
        param.setValidCode("4403");
        PPDUserResult result = ppdUserService.checkValidateCode(param);
        logger.info("test result={}", result.toString());
    }

    @Test
    public void test_login() throws Exception {
        PPDLoginParam param = new PPDLoginParam();
        param.setUserId(3L);
        param.setUserName("18618122260");
        param.setPassword("xialei030");
        param.setRememberMe(false);
        param.setValidateCode(null);
        PPDUserResult result = ppdUserService.login(param);
        logger.info("test result={}", result.toString());
        if (result.isSuccess()) {
            logger.info("test result PpDaiUniqueId={}", result.getPpdLoginResult().getPpDaiUniqueId());
            logger.info("test result Redirect={}", result.getPpdLoginResult().getRedirect());
        }
    }

    @Test
    public void test_manyloginPasswordErrorFail() throws Exception {
        PPDLoginParam param = new PPDLoginParam();
        param.setUserId(2L);
        param.setUserName("13313066864");
        param.setPassword("g");
        param.setRememberMe(false);
        param.setValidateCode(null);
        for (int i = 0; i < 10; i++) {
            PPDUserResult result = ppdUserService.login(param);
            logger.info("test result={}", result.toString());
            if (result.isSuccess()) {
                logger.info("test result PpDaiUniqueId={}", result.getPpdLoginResult().getPpDaiUniqueId());
                logger.info("test result Redirect={}", result.getPpdLoginResult().getRedirect());
            }
        }
    }
}
