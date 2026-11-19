package com.example.multi.runner;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.example.multi.mapper.dm.EaiHmgcOtaResultMapper;
import com.example.multi.mapper.portal.CommonCodeMapper;
import com.example.multi.model.CommonCode;
import com.example.multi.model.EaiHmgcOtaResult;
import com.example.multi.util.HMap;
import com.example.multi.vo.QueryUpdateFailedVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class MyRunner implements CommandLineRunner {
    @Resource
    private CommonCodeMapper commonCodeMapper;

    @Resource
    private EaiHmgcOtaResultMapper eaiHmgcOtaResultMapper;

    @Resource
    private JsonMapper jsonMapper;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        CommonCode commonCode = new CommonCode();
        commonCode.setPrntCd("UpdateErrCd");
        commonCode.setLangCd("zh");
        List<Map<String, Object>> updateCdList = commonCodeMapper.selectCommonCodeList(commonCode);
        List<String> updateErrCodeList = updateCdList.stream()
                .map(map -> (String) map.get("cdVal")).toList();
        log.info("updateErrCodeList: {}", updateErrCodeList);
        QueryUpdateFailedVO queryUpdateFailedVO = new QueryUpdateFailedVO();
        queryUpdateFailedVO.setDays(365);
        queryUpdateFailedVO.setUpdateErrCodeList(updateErrCodeList);
        List<EaiHmgcOtaResult> updateFailedList = eaiHmgcOtaResultMapper.getUpdateFailedList(queryUpdateFailedVO);
        if (CollectionUtils.isNotEmpty(updateFailedList)) {
            for (EaiHmgcOtaResult eaiHmgcOtaResult : updateFailedList) {
                log.info("eaiHmgcOtaResult: {}", jsonMapper.writeValueAsString(eaiHmgcOtaResult));
                HMap hMap = getTransactionLogSuccess(eaiHmgcOtaResult);
                if (Objects.nonNull(hMap)) {
                    log.info("hMap: {}", hMap);
//                    EaiHmgcOtaResult update = new EaiHmgcOtaResult();
//                    update.setId(eaiHmgcOtaResult.getId());
//                    update.setUpdateResult(UPDATE_SUCCESS_CODE);
//                    update.setLinkYn(LINK_N);
//                    eaiHmgcOtaResultDao.updateHmgcOtaResultByIdSelective(update);
                }
            }
        }
    }

    public HMap getTransactionLogSuccess(EaiHmgcOtaResult eaiHmgcOtaResult) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        if (Objects.nonNull(eaiHmgcOtaResult.getUpdateTime())) {
            String updateTimeStr = DateUtil.format(eaiHmgcOtaResult.getUpdateTime(), DatePattern.PURE_DATETIME_FORMATTER);
            criteriaList.add(Criteria.where("updatetime").gte(updateTimeStr));
        }
        criteriaList.add(Criteria.where("vin").is(eaiHmgcOtaResult.getVin()));
        if (StringUtils.isNotBlank(eaiHmgcOtaResult.getSourceVersion())) {
            criteriaList.add(Criteria.where("sourceversion").is(eaiHmgcOtaResult.getSourceVersion()));
        }
        criteriaList.add(Criteria.where("dmresult").is("201"));
        criteria.andOperator(criteriaList);

        Aggregation agg = Aggregation.newAggregation(Aggregation.match(criteria),
                        Aggregation.group("vin").last("$$ROOT").as("doc"),
                        Aggregation.replaceRoot("doc"))
                .withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());

        AggregationResults<HMap> aggregate = mongoTemplate.aggregate(agg, "TransactionCHN", HMap.class);
        List<HMap> result = aggregate.getMappedResults();
        if (CollectionUtils.isNotEmpty(result)) {
            return result.getFirst();
        } else {
            return null;
        }
    }
}
