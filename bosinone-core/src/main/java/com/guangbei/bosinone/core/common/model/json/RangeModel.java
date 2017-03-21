package com.guangbei.bosinone.core.common.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by xugang on 2017/3/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangeModel {
    @JsonProperty(value = "Begin")
    private Integer begin;
    @JsonProperty(value = "End")
    private Integer end;
}
