package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserService
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;
    public void create(CreateUserRequest createUserRequest) throws JsonProcessingException {
        User user=createUserRequest.toUser();
        this.userRepository.save(user);
        // todo: publish a message for Kafka Queue to create a wallet for the new user

        JSONObject obj=this.objectMapper.convertValue(user,JSONObject.class);
        this.kafkaTemplate.send("User-Create",objectMapper.writeValueAsString(obj));
    }
}
