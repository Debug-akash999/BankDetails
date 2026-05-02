package com.example.BankDetails.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="userDetails")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;
@Email
private String email;
    private  String password;}
