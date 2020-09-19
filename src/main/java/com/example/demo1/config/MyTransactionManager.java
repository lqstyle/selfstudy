package com.example.demo1.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * MyTransactionManager$
 *
 * @author shuai
 * @date 2020/4/8$
 */
public class  MyTransactionManager implements  PlatformTransactionManager {

  @Override
  public TransactionStatus getTransaction(TransactionDefinition transactionDefinition)
      throws TransactionException {
    return null;
  }

  @Override
  public void commit(TransactionStatus transactionStatus) throws TransactionException {

  }

  @Override
  public void rollback(TransactionStatus transactionStatus) throws TransactionException {

  }
}