package com.qhy.ppmsadmin.service;

import com.qhy.ppmsadmin.dto.IncomeRecordParam;

public interface InExService {
    /**
     * 收入录入
     */
    Boolean recordIncome(IncomeRecordParam param);
}