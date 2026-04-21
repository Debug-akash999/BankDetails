package com.example.BankDetails.Repository;

import com.example.BankDetails.Model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails,Long> {
}
