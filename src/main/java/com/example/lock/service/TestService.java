package com.example.lock.service;

import com.example.lock.dao.AccountWalletMapper;
import com.example.lock.entity.AccountWallet;
import com.example.lock.entity.AccountWalletExample;
import com.example.lock.exception.ObjectOptimisticLockingFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Integer updateAccountWallet(AccountWallet wallet) throws Exception {
        Integer editNum = accountWalletMapper.updateAccountWallet(wallet);
        if (0 == editNum) {
            throw new ObjectOptimisticLockingFailureException();
        }
        return editNum;
    }

    @Transactional
    public Object pessimisticTest(String type) throws Exception {

        AccountWallet accountWallet = accountWalletMapper.selectByPrimaryKeyForPessimistic(1);

        if (type.equals("1")) {
            Thread.sleep(12000);
        }

        accountWalletMapper.updateByPrimaryKey(accountWallet);

        return accountWallet;
    }
}
