package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController
{

    @Autowired
    TransactionService transactionService;
    @PostMapping("/transaction/initiate")
    //When we add authentication on top of it, we won't be taking sender details, we will fetch it from
    // the authentication pool.
    public String initiateTransaction(@RequestParam("sender") Integer sender,
                                      @RequestParam("receiver") Integer receiver,
                                      @RequestParam ("amount") Long amount,
                                      @RequestParam("string") String reference) throws JsonProcessingException {
            return this.transactionService.initiateTransaction(sender, receiver, amount, reference);


    }
}
