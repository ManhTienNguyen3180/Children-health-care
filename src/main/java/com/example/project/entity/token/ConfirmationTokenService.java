package com.example.project.entity.token;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






/**
 * ConfirmationTokenService
 */ 
@Service
public class ConfirmationTokenService{

  @Autowired
  private ConfirmationTokenRepository cTokenRepository;

  public void saveConfirmToken(ConfirmationToken token){
    cTokenRepository.save(token);
  }
  
  public Optional<ConfirmationToken> confirmAcount(String token){
    return cTokenRepository.findByToken(token);
  }
  
}
  
