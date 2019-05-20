package com.example.lock.service;

import com.example.lock.dao.AccountWalletMapper;
import com.example.lock.entity.AccountWallet;
import com.example.lock.entity.AccountWalletExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private AccountWalletMapper accountWalletMapper;

    public AccountWallet selectByOpenId(String openId) {

        AccountWalletExample example = new AccountWalletExample();
        example.createCriteria().andUserOpenIdEqualTo(openId);

        List<AccountWallet> list = accountWalletMapper.selectByExample(example);

        if (list.size() != 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Integer updateAccountWallet(AccountWallet wallet) {
        return accountWalletMapper.updateAccountWallet(wallet);
    }
}
