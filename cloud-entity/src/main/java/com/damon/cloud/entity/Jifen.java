package com.damon.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jifen {
    private Integer jifenId;
    private Integer count;
    private String type;
}
