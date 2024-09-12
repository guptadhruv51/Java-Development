package org.example;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet,String>
{
            Wallet findByuserId(Integer userId);

            @Modifying
            @Transactional
            @Query("update Wallet w set w.balance=w.balance+ :amount where w.id=:walletId")
            void incrementWallet(String walletId, Long amount);

    @Modifying
    @Transactional
    @Query("update Wallet w set w.balance=w.balance - :amount where w.id=:walletId")
    void DecrementWallet(String walletId, Long amount);



    @Modifying
    @Transactional
    @Query("update Wallet w set w.balance=w.balance+ :amount where w.id=:walletId")
    void updatetWallet(String walletId, Long amount);
}
