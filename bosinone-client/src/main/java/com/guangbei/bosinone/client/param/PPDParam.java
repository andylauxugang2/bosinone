package com.guangbei.bosinone.client.param;

import java.util.Map;

/**
 * Created by xugang on 16/11/2.
 */
public class PPDParam extends BaseParam {
    private static final long serialVersionUID = -3148889180208129245L;
    private Map<String, Object> params; //额外参数

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
