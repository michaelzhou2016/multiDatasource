package com.example.multi.mapper.portal;

import com.example.multi.model.CommonCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonCodeMapper {

    List<Map<String, Object>> selectCommonCodeList(CommonCode commonCode);
}