package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/5/16 11:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcFuncAttrRequest extends RestRequest {

    @NotBlank(message = "对应功能GUID不能为空")
    private String guidFunc;

    private String attrType;

    private String attrKey;

    private String attrValue;

    private String memo;
}
