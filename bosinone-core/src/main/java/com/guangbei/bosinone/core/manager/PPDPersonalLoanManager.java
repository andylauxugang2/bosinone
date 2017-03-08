package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListRequest;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListResponse;

/**
 * 拍拍贷 散标 相关 接口层操作
 * 查询散标 投标
 * Created by xugang on 17/1/4.
 */
public interface PPDPersonalLoanManager {

    /**
     * 获取拍拍贷无线api 散标标的列表
     *
     * @param userId 系统用户id
     * @param ppduname 系统用户id
     * @param request ppd 无线接口 请求参数
     * @return
     * @throws PPDException
     */
    PPDWirelessGetPersonalLoanListResponse bidProductList(Long userId, String ppduname, PPDWirelessGetPersonalLoanListRequest request) throws PPDException;
}
