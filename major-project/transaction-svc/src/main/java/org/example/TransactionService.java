package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class TransactionService
{
    @Autowired
    TransactionRepository transactionRepository;

    private Logger logger = (Logger) LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    private ObjectMapper objectMapper=new ObjectMapper();
    private JSONParser jsonParser =new JSONParser();
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
    @KafkaListener(topics={"Wallet-Updates"},groupId = "E-Wallet")
    public void completeTxn(String msg) throws ParseException {
            JSONObject obj=(JSONObject) this.jsonParser.parse(msg);
            String externalTxnId=(String) obj.get("externalTxnId");
            String status=(String)obj.get("status");

            Transaction transaction=this.transactionRepository.findByExternalTxnId(externalTxnId);
            if(!transaction.getTransactionStatus().equals(TransactionStatus.Pending))
            {
                this.logger.warn("Transaction already reached terminal "+ externalTxnId);
                return;
            }
        if (status.equals("Success")) {
            transaction.setTransactionStatus(TransactionStatus.Success);
        } else {
            transaction.setTransactionStatus(TransactionStatus.Failed);
        }

        this.transactionRepository.save(transaction);
        //TODO: publish a transaction complete event which can be listened by notification service

        //transaction.setTransactionStatus();
    }
}
