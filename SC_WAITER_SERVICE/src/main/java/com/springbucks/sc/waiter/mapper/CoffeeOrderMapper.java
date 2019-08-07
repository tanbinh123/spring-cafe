package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.CoffeeOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CoffeeOrderMapper {
    @Insert("insert into t_order (customer, state, create_time, update_time)"
            + "values (#{customer}, #{state}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(CoffeeOrder coffeeOrder);

    @Select("select * from t_order where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true # is equivalent
            // @Result(column = "update_time", property = "updateTime"),
    })
    CoffeeOrder getById(@Param("id") Long id);
}
