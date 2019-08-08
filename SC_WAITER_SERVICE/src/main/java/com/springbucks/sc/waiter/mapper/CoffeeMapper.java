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
    @Results(id = "coffeeResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price")
    })
    Coffee getById(@Param("id") Long id);

    @Select("SELECT * FROM t_coffee WHERE name = #{name} ORDER BY create_time ASC LIMIT 1")
    @ResultMap("coffeeResultMap")
    Coffee getByName(@Param("name") String name);

    @Select("SELECT * FROM t_coffee WHERE name = #{name} ORDER BY create_time")
    @ResultMap("coffeeResultMap")
    List<Coffee> getAllByName(String name);

    @Select("SELECT * FROM t_coffee ORDER BY id")
    @ResultMap("coffeeResultMap")
    List<Coffee> getAll();

    @Select({
            "<script> ",
                "SELECT * FROM t_coffee c WHERE c.name in",
                    "<foreach collection='names' item='name' open='(' separator=',' close=')'>",
                    "#{name}",
                    "</foreach>",
            "</script>"
    })
    @ResultMap("coffeeResultMap")
    List<Coffee> getByNames(@Param("names") List<String> names);

    @Delete({
            "DELETE FROM t_coffee",
            "WHERE id = #{id,jdbcType=BIGINT}"
    })
    int deleteById(Long id);
}
