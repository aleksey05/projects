package com.kickstarter.controller;

import static com.kickstarter.util.Constants.PAYMENT_AMOUNT;
import static com.kickstarter.util.Constants.PAYMENT_TYPE;
import static com.kickstarter.util.Constants.PAYMENT_VIEW;
import static com.kickstarter.util.Constants.PROJECT_ID_KEY;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kickstarter.beanVO.PayerVo;
import com.kickstarter.dao.Interfaces.PaymentAmountDao;
import com.kickstarter.dao.Interfaces.PaymentDao;
import com.kickstarter.dao.Interfaces.QuestionDao;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final int EMPTY_PAYMENT = 0;
    

    @Autowired
    QuestionDao questionDao;
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    PaymentAmountDao paymentAmountDao;

    @RequestMapping("/provide")
    public ModelAndView providePaymentType(@ModelAttribute("payerVo") PayerVo payerVo,
                                           @RequestParam Map<String, String> requestParams) {
        int paymentAmount = EMPTY_PAYMENT;
        String projectId = requestParams.get(PROJECT_ID_KEY);

        if (requestParams.get(PAYMENT_TYPE).isEmpty()) {
            return new ModelAndView("redirect:/project?projectId=" + projectId);
        }

        int paymentType = Integer.parseInt(requestParams.get(PAYMENT_TYPE));

        if (paymentType < paymentAmountDao.getPresetPaymetnsCount()) {
            paymentAmount = paymentAmountDao.getPaymentAmount(paymentType).getAmount();
        }
        ModelAndView modelAndView = new ModelAndView(PAYMENT_VIEW);
        modelAndView.addObject(PROJECT_ID_KEY, projectId);
        modelAndView.addObject(PAYMENT_AMOUNT, paymentAmount);
        return modelAndView;
    }

    @RequestMapping(value = "/proceed", method = RequestMethod.POST)
    public ModelAndView proceedPayment(@Valid @ModelAttribute("payerVo") PayerVo payerVo, BindingResult result,
                                       @RequestParam Map<String, String> requestParams) {
        if (result.hasErrors()) {
            return new ModelAndView(PAYMENT_VIEW).addObject(PROJECT_ID_KEY, requestParams.get(PROJECT_ID_KEY));
        }
        int projectId = Integer.parseInt(requestParams.get(PROJECT_ID_KEY));
        int paymentAmount = Integer.parseInt(requestParams.get(PAYMENT_AMOUNT));
        paymentDao.addPayment(projectId, paymentAmount);
        return new ModelAndView("redirect:/project?projectId=" + projectId);

    }

}
