package org.tis.tools.abf.module.ac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.tis.tools.abf.module.ac.dao.AcAppConfigMapper;
import org.tis.tools.abf.module.ac.entity.AcAppConfig;
import org.springframework.transaction.annotation.Transactional;
import static org.tis.tools.core.utils.BasicUtil.wrap;

import java.math.BigDecimal;

/**
 * acAppConfig的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppConfigServiceImpl extends ServiceImpl<AcAppConfigMapper, AcAppConfig> implements IAcAppConfigService {

    @Autowired
    IAcAppConfigService acAppConfigService;

    /**
     * 新增应用个性化配置
     */
    @Override
    public AcAppConfig createRootAppConfig(String guidApp, String configType, String configName, String configDict, String configStyle, String configValue, String
            enabled, String displayOrder, String configDesc) {
        AcAppConfig acAppConfig = new AcAppConfig();


        try {
            //转换displayOrder类型
            BigDecimal displayOrderNew;
            if ("".equals(displayOrder) || null == displayOrder){
                displayOrderNew = null;
            }else {
                displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));
            }
            //收集参数
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrderNew);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.insert(acAppConfig);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_APPCONFIG,
                    wrap(AcAppConfig.COLUMN_CONFIG_TYPE,configType),configType
            );
        }

        return acAppConfig;
    }


    /**
     * 修改个性配置
     */
    @Override
    public AcAppConfig changeById(String guid, String guidApp, String configType, String configName, String configDict, String configStyle,
                                  String configValue, String enabled, String displayOrder, String configDesc) {

        AcAppConfig acAppConfig = new AcAppConfig();

        try {
            //转换displayOrder类型
            BigDecimal displayOrderNew;
            if ("".equals(displayOrder) || null == displayOrder){
                displayOrderNew = null;
            }else {
                displayOrderNew = BigDecimal.valueOf(Double.valueOf(displayOrder));
            }

            //收录参数
            acAppConfig.setGuid(guid);
            acAppConfig.setGuidApp(guidApp);
            acAppConfig.setConfigType(configType);
            acAppConfig.setConfigName(configName);
            acAppConfig.setConfigDict(configDict);
            acAppConfig.setConfigStyle(configStyle);
            acAppConfig.setConfigValue(configValue);
            acAppConfig.setEnabled(enabled);
            acAppConfig.setDisplayOrder(displayOrderNew);
            acAppConfig.setConfigDesc(configDesc);

            acAppConfigService.updateById(acAppConfig);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_APPCONFIG,
                    wrap(AcAppConfig.COLUMN_GUID,guid),guid
            );
        }
        return acAppConfig;
    }
}

