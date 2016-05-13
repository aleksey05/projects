package com.kickstarter.dao.Interfaces;

import com.kickstarter.model.PaymentAmount;

public interface PaymentAmountDao {

	PaymentAmount getPaymentAmount(int paymentAmountId);

	int getPresetPaymetnsCount();

}
