package com.guangbei.bosinone.core.common.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by xugang on 2017/3/8.
 */
@Data
public class RangeModel {
    @JsonProperty(value = "Begin")
    private int begin;
    @JsonProperty(value = "End")
    private int end;
}
