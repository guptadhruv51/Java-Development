package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService
{
    private Logger logger= LoggerFactory.getLogger(WalletService.class);
    ObjectMapper objectMapper=new ObjectMapper();

    private JSONParser jsonParser=new JSONParser();
    @Autowired
    WalletRepository walletRepository;

    @Value("${wallet.promotional.balance}")
    private Long balance;


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics={"User-Create"},groupId = "E-Wallet")  // used for listening to the topics



    public void createWallet(String msg) throws ParseException
    {
        JSONObject obj=(JSONObject) this.jsonParser.parse(msg);
        Integer userID=(Integer)obj.get("id");
        Wallet wallet=this.walletRepository.findByuserId(userID);
        if(wallet!=null)
        {
                this.logger.info("Wallet for user already exists {}",userID);
                return;
        }
        wallet.builder().
                id(UUID.randomUUID().toString())
                .userId(userID)
                .balance(balance)
                .status(WalletStatus.Active)
                .build();
        this.walletRepository.save(wallet);
        //JSONObject obj=this.objectMapper.convertValue(msg, JSONObject.class);

    }

    @KafkaListener(topics={"Transaction-Created"},groupId = "E-Wallet")
    public void updateWallet(String msg) throws ParseException, JsonProcessingException {
        JSONObject obj=(JSONObject) this.jsonParser.parse(msg);
        Integer sender=(Integer)(obj.get("senderId"));
        Integer receiver=(Integer) (obj.get("receiverId"));
        Long amount= (Long) obj.get("amount");
        String externalTxnId= (String) obj.get("externalTxnId");
        Wallet  receiverWallet=this.walletRepository.findByuserId(receiver);
        Wallet senderWallet=this.walletRepository.findByuserId(sender);

       if(receiverWallet==null || senderWallet==null || amount<=0 || senderWallet.getBalance()<amount)
       {
           this.logger.error("Wallet update cannot be done");
           // TODO: publish a message on wallet-update topic with status as failed

           JSONObject jsonObject=new JSONObject();
           jsonObject.put("status","Failed");
           jsonObject.put("senderId",sender);
           jsonObject.put("receiverId",receiver);
           jsonObject.put("externalTxnId",externalTxnId);
           this.kafkaTemplate.send("Wallet-Updates", objectMapper.writeValueAsString(jsonObject));
            return;
       }

//        this.walletRepository.incrementWallet(receiverWallet.getId(), amount);
//        this.walletRepository.DecrementWallet(senderWallet.getId(), amount);

//        this.walletRepository.updatetWallet(receiverWallet.getId(), amount);
//        this.walletRepository.updatetWallet(senderWallet.getId(),-amount);

//
       receiverWallet.setBalance(receiverWallet.getBalance()+amount);
       senderWallet.setBalance(senderWallet.getBalance()-amount);
       walletRepository.saveAll(List.of(receiverWallet,senderWallet));
       //TODO: publish a message for transaction success/ wallet update

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status","Success");
        jsonObject.put("senderId",sender);
        jsonObject.put("receiverId",receiver);
        jsonObject.put("externalTxnId",externalTxnId);
        this.kafkaTemplate.send("Wallet-Updates", objectMapper.writeValueAsString(jsonObject));
        return;
        //JSONObject obj=this.objectMapper.convertValue(msg, JSONObject.class);

    }
}
