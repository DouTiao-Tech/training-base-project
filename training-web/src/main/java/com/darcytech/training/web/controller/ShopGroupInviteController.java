package com.darcytech.training.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darcytech.training.core.TrainingException;

@RestController
@RequestMapping("/api/shopGroupInvite")
public class ShopGroupInviteController {

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public void accept(Long inviteId, String randomCode) {
        throw new IllegalStateException("shopGroup.inviteId.invalid");
    }

}
