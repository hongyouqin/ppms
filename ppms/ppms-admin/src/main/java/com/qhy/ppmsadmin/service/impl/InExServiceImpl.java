package com.qhy.ppmsadmin.service.impl;

import java.util.Date;

import com.qhy.ppmsadmin.dto.IncomeRecordParam;
import com.qhy.ppmsadmin.entity.Income;
import com.qhy.ppmsadmin.repository.IncomeRepository;
import com.qhy.ppmsadmin.service.InExService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InExServiceImpl implements InExService {

    @Autowired
    IncomeRepository incomeRepository;

    @Override
    public Boolean recordIncome(IncomeRecordParam param) {
        // TODO Auto-generated method stub
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        Income income = new Income();
        income.setTitle(param.getTitle());
        income.setUserName(account);
        income.setRecorder(param.getRecorder());
        income.setSource(param.getSource());
        income.setDescrible(param.getRemarks());
        income.setMoney(param.getMoney());
        income.setCreatedTime(new Date());
        incomeRepository.save(income);
        return true;
    }

}
