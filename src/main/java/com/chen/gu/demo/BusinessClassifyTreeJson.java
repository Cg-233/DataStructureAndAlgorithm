/*
package com.chen.gu.demo;

import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.taobao.diamond.client.Diamond;

*/
/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/6 16:42
 *//*

//@Configuration
@Slf4j
@Data
public class BusinessClassifyTreeJson {

    private static final String DATA_ID = "com.huijin.irs:risk.nature.type";
    private static final String GROUP_ID = "OPTION_GROUP";

    */
/**
     * 全面风险分类标准树状结构JSON
     *//*

    private String businessClassify;

    @PostConstruct
    public void init() {
        initTree();
    }

    private void initTree() {
        // 启动时主动获取前端版本号
        try {
            String treeJson = Diamond.getConfig(DATA_ID, GROUP_ID, 1000);
            if (StringUtils.isNotBlank(treeJson)) {
                log.info("Init Business Classify Tree succeed. The treeJson is {}", treeJson);
                refreshConfig(treeJson);
            } else {
                log.error("Init Business Classify Tree failed. The treeJson is null");
                System.exit(-1); }
        } catch (IOException e) {
            log.error("Init vemit design config error ", e);
        }
        // 监听前端版本号变化的推送
        Diamond.addListener(DATA_ID, GROUP_ID, new ManagerListenerAdapter() {
            @Override
            public void receiveConfigInfo(String treeJson) {
                if (StringUtils.isBlank(treeJson)) {
                    log.error("Receive Init Business Classify error. The treeJson is blank");
                } else {
                    log.info("Receive Init Business Classify succeed. The treeJson is {}", treeJson);
                    refreshConfig(treeJson);
                }
            }}
            );
    }

    private void refreshConfig(String treeJson) {
        BusinessClassifyTreeVO treeVO = FastJsonTool.deserialize(treeJson, BusinessClassifyTreeVO.class);
        this.businessClassify = treeVO.getBusinessClassify();
    }
}
@Data
class BusinessClassifyTreeVO {

    private String businessClassify;
}


*/
