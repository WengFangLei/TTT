package org.tis.tools.abf.module.ac.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.ac.dao.AcAppMapper;
import org.tis.tools.abf.module.ac.entity.AcApp;
import org.tis.tools.abf.module.ac.entity.enums.AcAppType;
import org.tis.tools.abf.module.ac.exception.AcExceptionCodes;
import org.tis.tools.abf.module.ac.exception.AcManagementException;
import org.tis.tools.abf.module.ac.service.IAcAppService;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.tis.tools.core.utils.BasicUtil.wrap;

/**
 * acApp的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AcAppServiceImpl extends ServiceImpl<AcAppMapper, AcApp> implements IAcAppService {

    @Autowired
    IAcAppService acAppService;

    /**
     * 应用新增
     */
    @Override
    public AcApp creatRootApp(String appCode, String appName, String appType, String url, String ipAddr, String ipPort, String appDesc,String isopen,String openDate) throws AcManagementException {
        AcApp app = new AcApp();

        /**
         * 是否开通:取值来自业务菜单： DICT_YON
         * 默认为N，新建后，必须执行应用开通操作，才被开通。
         */

        AcAppType appTypeNew = null;
        if ("local".equals(appType) || "LOCAL".equals(appType)){
            appTypeNew = AcAppType.LOCAL;
        }else if("remote".equals(appType) || "REMOTE".equals(appType)){
            appTypeNew = AcAppType.REMOTE;
        }

        YON isopenNew = null;
        if("YES".equals(isopen) || "yes".equals(isopen) || "Y".equals(isopen) || "y".equals(isopen)){
            isopenNew = YON.YES;
        }else if ("NO".equals(isopen) || "no".equals(isopen) || "N".equals(isopen) || "n".equals(isopen)){
            isopenNew =YON.NO;
        }else {
            isopenNew = YON.NO;
        }

        try{
            //处理时间格式
            Date openDateNew = null;
            if (null == openDate || "".equals(openDate)){
                openDateNew = new Date();
            }else {
                openDateNew = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(openDate);
            }

            //收集入参
            app.setAppType(appTypeNew);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);
            app.setIsopen(isopenNew);
            app.setOpenDate(openDateNew);

            insert(app);
        }catch (AcManagementException ae){
            throw ae;
        } catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_CREATE_AC_APP,
                    wrap(AcApp.COLUMN_APP_CODE,appCode),appCode
            );
        }
        return app;
    }


    /**
     * 修改应用
     * @throws AcManagementException
     */
    @Override
    public AcApp changeById(String guid,String appCode, String appName, String appType,String isopen,String openDate ,String url, String ipAddr, String ipPort, String appDesc) throws AcManagementException {
        AcApp app = new AcApp();


        AcAppType appTypeNew = null;
        if ("local".equals(appType) || "LOCAL".equals(appType)){
            appTypeNew = AcAppType.LOCAL;
        }else if("remote".equals(appType) || "REMOTE".equals(appType)){
            appTypeNew = AcAppType.REMOTE;
        }

        YON isopenNew = null;
        if("YES".equals(isopen) || "yes".equals(isopen) || "Y".equals(isopen) || "y".equals(isopen)){
            isopenNew = YON.YES;
        }else if ("NO".equals(isopen) || "no".equals(isopen) || "N".equals(isopen) || "n".equals(isopen)){
            isopenNew =YON.NO;
        }

        try{
            //处理时间格式
            Date openDateNew = null;
            if (null == openDate || "".equals(openDate)){
                openDateNew = null;
            }else {
                openDateNew = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").parse(openDate);
            }

            //收集入参
            app.setGuid(guid);
            app.setIsopen(isopenNew);
            app.setOpenDate(openDateNew);
            app.setAppType(appTypeNew);
            app.setAppName(appName);
            app.setAppCode(appCode);
            app.setAppDesc(appDesc);
            app.setUrl(url);
            app.setIpAddr(ipAddr);
            app.setIpPort(ipPort);

            updateById(app);
        }catch (AcManagementException ae){
            throw ae;
        }catch (Exception e){
            e.printStackTrace();
            throw new AcManagementException(
                    AcExceptionCodes.FAILURE_WHRN_UPDATE_AC_APP,
                    wrap(AcApp.COLUMN_GUID,guid),guid
            );
        }
        return app;
    }

}

