package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService
{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper=new ObjectMapper();
    public String initiateTransaction(Integer sender, Integer Receiver, Long amount, String reference) throws JsonProcessingException {

        Transaction transaction=Transaction.builder().externalTxnId(UUID.randomUUID().toString())
                .amount(amount)
                .receiverId(Receiver)
                .senderId(sender)
                .reference(reference)
                .transactionStatus(TransactionStatus.Pending)
                .build();
        this.transactionRepository.save(transaction);
        JSONObject obj=this.objectMapper.convertValue(transaction,JSONObject.class);
        this.kafkaTemplate.send("Transaction-Created",this.objectMapper.writeValueAsString(obj));
        return transaction.getExternalTxnId();
    }
}
