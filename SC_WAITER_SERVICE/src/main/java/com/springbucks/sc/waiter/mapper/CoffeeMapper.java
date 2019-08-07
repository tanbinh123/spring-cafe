package com.springbucks.sc.waiter.mapper;

import com.springbucks.sc.waiter.model.Coffee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CoffeeMapper {
    @Insert("INSERT INTO t_coffee (name, price, create_time, update_time)"
            + "VALUES (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int insert(Coffee coffee);

    @Select("SELECT * FROM t_coffee WHERE id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true # is equivalent
            // @Result(column = "update_time", property = "updateTime"),
    })
    Coffee getById(@Param("id") Long id);

    @Select("SELECT * FROM t_coffee WHERE name = #{name} ORDER BY create_time ASC LIMIT 1")
    Coffee getByName(@Param("name") String name);

    @Select("SELECT * FROM t_coffee WHERE name = #{name} ORDER BY create_time")
    public List<Coffee> getAllByName(String name);

    @Select("select * from t_coffee order by id")
    List<Coffee> getAll();
}
