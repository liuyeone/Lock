package com.example.lock.dao;

import com.example.lock.entity.AccountWallet;
import com.example.lock.entity.AccountWalletExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AccountWalletMapper {
    int countByExample(AccountWalletExample example);

    int deleteByExample(AccountWalletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountWallet record);

    int insertSelective(AccountWallet record);

    List<AccountWallet> selectByExample(AccountWalletExample example);

    AccountWallet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountWallet record, @Param("example") AccountWalletExample example);

    int updateByExample(@Param("record") AccountWallet record, @Param("example") AccountWalletExample example);

    int updateByPrimaryKeySelective(AccountWallet record);

    int updateByPrimaryKey(AccountWallet record);

    int updateAccountWallet(AccountWallet record);
}