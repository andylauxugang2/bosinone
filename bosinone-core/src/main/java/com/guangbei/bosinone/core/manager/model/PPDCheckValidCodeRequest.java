package com.guangbei.bosinone.core.manager.model;

import com.guangbei.bosinone.core.common.http.param.AbstractParamMap;

/**
 * Created by xugang on 17/1/4.
 */
public class PPDCheckValidCodeRequest extends AbstractParamMap {
    public static final String PARAM_NAME_VALUE = "imgvalidatecode";

    private String name; //imgvalidatecode
    private String value; //7515
    private String _; //1488337464254

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String get_() {
        return _;
    }

    public void set_(String _) {
        this._ = _;
    }
}
