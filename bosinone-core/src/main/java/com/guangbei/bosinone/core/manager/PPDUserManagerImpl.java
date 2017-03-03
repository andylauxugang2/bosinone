package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.common.PropertyConfig;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.PPDGetValidCodeRequest;
import com.guangbei.bosinone.core.manager.model.PPDGetValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 拍拍贷 接口调用 非OpenAPI
 * Created by xugang on 2017/3/2.
 */
@Component
public class PPDUserManagerImpl extends PPDRemoteCallHttpManagerWrapper implements PPDUserManager {

    private static final Logger logger = LoggerFactory.getLogger(PPDUserManagerImpl.class);

    @Resource
    protected PropertyConfig propertyConfig;

    @Override
    public PPDGetValidateCode getValidateCode(Long userId) throws PPDException {
        Map<String, Object> params = new HashMap<>();
        PPDGetValidCodeRequest request = new PPDGetValidCodeRequest();
        request.setTmp(String.valueOf(System.currentTimeMillis()));
        params.putAll(request.toParamMap());

        logger.info("开始调用拍拍贷获取验证码接口,userId={}", userId);
        try {
            ParameterizedTypeReference responseType = new ParameterizedTypeReference<byte[]>() {
            };

            ResponseEntity<byte[]> responseEntity = call(propertyConfig.getPpdWebSiteValidateCodeUrl(), params, HttpMethod.GET, responseType);
            int statusCode = responseEntity.getStatusCode().value();
            if (statusCode != HttpStatus.OK.value()) {
                throw new PPDException(String.format("调用拍拍贷获取验证码接口状态非法,statusCode=%s,userId=%s", statusCode, userId));
            }
            byte[] image = responseEntity.getBody();
            if (image == null || image.length == 0) {
                throw new PPDException("调用拍拍贷查询验证码接口获取结果为空:userId=" + userId);
            }
            PPDGetValidateCode ppdGetValidateCode = new PPDGetValidateCode();
            ppdGetValidateCode.setImage(image);
            logger.info("调用拍拍贷获取验证码接口成功,userId={}", userId);
            return ppdGetValidateCode;
        } catch (Throwable e) {
            throw new PPDException("调用拍拍贷获取验证码接口发生异常,userId=" + userId, e);
        }
    }
}
