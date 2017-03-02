package com.guangbei.bosinone.client.param;

import java.util.Map;

/**
 * Created by xugang on 16/11/2.
 */
public class PPDGetValidCodeParam extends PPDParam {
    private static final long serialVersionUID = -5245959038010912359L;

    private Map<String, Object> params; //额外参数

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
