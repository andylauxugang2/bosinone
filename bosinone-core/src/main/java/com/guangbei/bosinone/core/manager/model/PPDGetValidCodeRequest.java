package com.guangbei.bosinone.core.manager.model;

import com.guangbei.bosinone.core.common.http.param.AbstractParamMap;

/**
 * Created by xugang on 17/1/4.
 */
public class PPDGetValidCodeRequest extends AbstractParamMap {
    private String tmp; //0.12377910584372076?tmp= 0.12377910584372076

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
}
