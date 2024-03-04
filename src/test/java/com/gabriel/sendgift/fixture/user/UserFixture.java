package com.gabriel.sendgift.fixture.user;

import com.gabriel.sendgift.core.domain.address.Address;
import com.gabriel.sendgift.core.domain.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserFixture {
    public static User createUser(){
        Address address = new Address();
        address.setCep("13632482");

        User user = new User();
        user.setId("123xd321aA");
        user.setName("Gabriel");
        user.setEmail("gabriel.teste@gmail.com");
        user.setPassword("asd123");
        user.setAddress(address);

        return user;
    }
}
