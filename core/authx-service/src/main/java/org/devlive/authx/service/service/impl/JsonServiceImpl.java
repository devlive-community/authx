package org.devlive.authx.service.service.impl;

import org.devlive.authx.common.enums.SystemMessageEnums;
import org.devlive.authx.common.json.JsonUtils;
import org.devlive.authx.common.json.JsonValidateUtils;
import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.service.JsonService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class JsonServiceImpl implements JsonService
{
    @Override
    public CommonResponseModel formatPretty(String source)
    {
        CommonResponseModel response = this.validate(source);
        if (!ObjectUtils.isEmpty(response)) {
            return response;
        }
        return CommonResponseModel.success(JsonUtils.formatPretty(source));
    }

    @Override
    public CommonResponseModel compression(String source)
    {
        CommonResponseModel response = this.validate(source);
        if (!ObjectUtils.isEmpty(response)) {
            return response;
        }
        return CommonResponseModel.success(JsonUtils.compression(source));
    }

    private CommonResponseModel validate(String source)
    {
        CommonResponseModel response = CommonResponseModel.validateCheck(source);
        if (!ObjectUtils.isEmpty(response)) {
            return response;
        }
        if (!JsonValidateUtils.isJSON(source)) {
            return CommonResponseModel.error(SystemMessageEnums.SYSTEM_JSON_ERROR);
        }
        return null;
    }
}
