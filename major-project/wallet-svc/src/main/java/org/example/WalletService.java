package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService
{
    private Logger logger= LoggerFactory.getLogger(WalletService.class)
    ObjectMapper objectMapper=new ObjectMapper();

    private JSONParser jsonParser=new JSONParser();
    @Autowired
    WalletRepository walletRepository;

    @Value("${wallet.promotional.balance}")
    private Long balance;

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
}
