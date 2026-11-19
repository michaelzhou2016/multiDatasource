package com.example.multi.mapper.dm;

import com.example.multi.model.EaiHmgcOtaResult;
import com.example.multi.vo.QueryUpdateFailedVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EaiHmgcOtaResultMapper {
    int batchInsert(List<EaiHmgcOtaResult> list);

    List<EaiHmgcOtaResult> getHmgcDownloadingOrUpdating(int days);

    int updateHmgcOtaResultByIdSelective(EaiHmgcOtaResult eaiHmgcOtaResult);

    List<EaiHmgcOtaResult> getUpdateFailedList(QueryUpdateFailedVO queryUpdateFailedVO);

}
