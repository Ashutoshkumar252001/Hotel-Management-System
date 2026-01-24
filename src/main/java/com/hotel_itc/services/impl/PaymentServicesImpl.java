package com.hotel_itc.services.impl;

import com.hotel_itc.exception.PaymentNotDoneException;
import com.hotel_itc.models.PaymentModel;
import com.hotel_itc.repo.PaymentRepo;
import com.hotel_itc.services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServicesImpl implements PaymentServices {

    @Autowired
     private PaymentRepo paymentRepo;

    @Override
    public PaymentModel getPaymentById(Long id) throws PaymentNotDoneException {
        Optional<PaymentModel> opt = paymentRepo.findById(id);
        if(opt.isEmpty()){
            throw new PaymentNotDoneException("Payment not Done by this Id:"+id);

        }
        return opt.get();
    }

    @Override
    public List<PaymentModel> findAllPayment() {

        return paymentRepo.findAll();
    }

    @Override
    public void savePayment(PaymentModel payment) {
        if(payment.getId()==null){
            paymentRepo.save(payment);
        }
        else
        {
            Optional<PaymentModel> opt = paymentRepo.findById(payment.getId());
            if(opt.isEmpty()){
                throw new PaymentNotDoneException("payment not done with this Id:"+payment.getId());
            }
            PaymentModel payment1 = opt.get();
            payment1.setPayment_mode(payment.getPayment_mode());
            payment1.setPayment_status(payment.getPayment_status());
            payment1.setAmount(payment.getAmount());
            paymentRepo.save(payment1);
        }

    }

    @Override
    public void deletePayment(Long id) {
        Optional<PaymentModel>opt = paymentRepo.findById(id);
        if(opt.isEmpty()){
            throw new PaymentNotDoneException("Player not done by this id:"+id);
        }
        paymentRepo.deleteById(id);
    }
}
