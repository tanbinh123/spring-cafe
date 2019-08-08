package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.CoffeeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CoffeeOrderMapper {
    @Select("SELECT * FROM t_order ORDER BY id")
    @Results(id = "orderResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "customer", column = "customer"),
            @Result(property = "state", column = "state",
                    jdbcType = JdbcType.INTEGER, typeHandler = EnumOrdinalTypeHandler.class)
    })
    List<CoffeeOrder> getAll();

    @Insert("INSERT INTO t_order (customer, state, create_time, update_time)"
            + "VALUES (#{customer}, #{state, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}, now(), now())")
    @Options(useGeneratedKeys = true)
    int insert(CoffeeOrder coffeeOrder);

    @Select("SELECT * FROM t_order WHERE id = #{id}")
    @ResultMap("orderResultMap")
    CoffeeOrder getById(@Param("id") Long id);

    @Select("SELECT * FROM t_order WHERE customer = #{customer} ORDER BY id")
    @ResultMap("orderResultMap")
    List<CoffeeOrder> getAllByCustomer(String customer);

    @Delete("DELETE FROM t_order WHERE id = #{id,jdbcType=BIGINT}")
    int deleteById(Long id);
}
