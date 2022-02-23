package br.pro.brand.cursojavaspring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.BankSlipPayment;

@Service
public class BankSlipService {
    
    public void fillBankSlipPayment(BankSlipPayment payment, Date paymentAt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(paymentAt);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpireAt(cal.getTime());
	}
}
