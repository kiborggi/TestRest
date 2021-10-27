package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import ru.course.config.BeanConfig;
import ru.course.dao.lab.BankAccountDAO;
import ru.course.model.Lab.BankAccountInfo;
import ru.course.model.Lab.BankTransactionException;
import ru.course.model.Lab.SendMoneyForm;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LabController {
@Resource (name = "SessionBeanGenerator")
BeanConfig.MathBean sessionBean;

@Resource (name = "SingletonBeanGenerator")
BeanConfig.MathBean singletonBean;

    @Autowired
    private BankAccountDAO bankAccountDAO;

    @RequestMapping(value ="/lab", method = RequestMethod.GET)
    public String lab (Model model){
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
        model.addAttribute("sessionBean",sessionBean );
        model.addAttribute("singletonBean",singletonBean );

        model.addAttribute("accountInfos", list);
        return "Lab/lab";
    }

    @RequestMapping(value ="/addToSession", method = RequestMethod.POST)
    public RedirectView labs (Model model, int value){

        sessionBean.add(value);

        model.addAttribute("sessionBean",sessionBean );
        model.addAttribute("singletonBean",singletonBean );
        return new RedirectView("/lab");
    }


    @RequestMapping(value ="/addToSingleton", method = RequestMethod.POST)
    public RedirectView labsingl (Model model, int value){

        singletonBean.add(value);

        model.addAttribute("sessionBean",sessionBean );
        model.addAttribute("singletonBean",singletonBean );
        return new RedirectView("/lab");
    }


    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {

        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);

        model.addAttribute("sendMoneyForm", form);

        return "lab/sendMoneyPage";
    }


    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String  processSendMoney(Model model, SendMoneyForm sendMoneyForm) {



        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "lab/sendMoneyPage";
        }
           return "redirect:/lab";
    }

}
