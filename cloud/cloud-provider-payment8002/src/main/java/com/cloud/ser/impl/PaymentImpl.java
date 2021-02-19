package com.cloud.ser.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.dao.PaymentDao;
import com.cloud.entity.Payment;
import com.cloud.ser.PaymentSer;

@Service
@Transactional
public class PaymentImpl extends ServiceImpl<PaymentDao, Payment> implements PaymentSer {
}
