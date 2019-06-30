package com.hms.provider.dao;

import com.hms.provider.dto.Update.MmsRSubUpdateDto;
import com.hms.provider.dto.staff_Insert.MmsTokenSubDto;
import com.hms.provider.vo.ManagerVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ManagerDao {
    /**
     * 列出所有用户的信息
     * 查询全部
     * 按照ID查询
     * 按照名字查询
     */
    @Select("select * from employee")
    List<ManagerVo> querManager();

    @Select("select * from employee where id = #{id}")
    ManagerVo querIdManager(@Param("id") int id);

    @Select("select * from employee where name = #{name}")
    List<ManagerVo> querNameManager(@Param("name") String name);

    /**
     * 员工录入
     * 只需填个人基本信息，其余关于公司的信息，不需要填写
     */
    @Insert({"insert into employee (ssn,name,sex,bdate,email,phone,address,salary,position,startdate,enddate,department,seniority) values(#{mmsTokenSubDto.mmsTokenDto.ssn},#{mmsTokenSubDto.mmsTokenDto.name},#{mmsTokenSubDto.mmsTokenDto.sex},#{mmsTokenSubDto.mmsTokenDto.bdate},#{mmsTokenSubDto.mmsTokenDto.email},#{mmsTokenSubDto.mmsTokenDto.phone},#{mmsTokenSubDto.mmsTokenDto.address},#{mmsTokenSubDto.salary},#{mmsTokenSubDto.position},#{mmsTokenSubDto.mmsTokenDto.startdate},#{mmsTokenSubDto.enddate},#{mmsTokenSubDto.mmsTokenDto.department},#{mmsTokenSubDto.seniority})",
    "ON DUPLICATE KEY UPDATE  ssn = #{mmsTokenSubDto.mmsTokenDto.ssn},name = #{mmsTokenSubDto.mmsTokenDto.name},sex = #{mmsTokenSubDto.mmsTokenDto.sex},bdate = #{mmsTokenSubDto.mmsTokenDto.bdate},email = #{mmsTokenSubDto.mmsTokenDto.email},phone = #{mmsTokenSubDto.mmsTokenDto.phone},address = #{mmsTokenSubDto.mmsTokenDto.address},salary = #{mmsTokenSubDto.salary},position = #{mmsTokenSubDto.position},startdate = #{mmsTokenSubDto.mmsTokenDto.startdate},enddate = #{mmsTokenSubDto.enddate},department = #{mmsTokenSubDto.mmsTokenDto.department},seniority = #{mmsTokenSubDto.seniority}"})
    void insertManager(@Param("mmsTokenSubDto") MmsTokenSubDto mmsTokenSubDto);


    /**
     *
     * @param id
     * 修改用户信息
     */
    @Update("update employee set salary = #{mmsRSubUpdateDto.salary},position = #{mmsRSubUpdateDto.position},enddate = #{mmsRSubUpdateDto.enddate},department = #{mmsRSubUpdateDto.department} where id = #{id}")
    void updateManager(@Param("mmsRSubUpdateDto")MmsRSubUpdateDto mmsrSubUpdateDto, @Param("id") int id);
}
