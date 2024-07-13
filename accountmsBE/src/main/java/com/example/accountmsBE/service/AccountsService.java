package com.example.accountmsBE.service;

import com.example.accountmsBE.dto.AccountsDTO;
import com.example.accountmsBE.entity.Accounts;
import com.example.accountmsBE.repo.AccountsRepo;
import com.example.accountmsBE.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountsService {

    @Autowired
    AccountsRepo accountsRepo;

    @Autowired
    ModelMapper modelMapper;


    public String saveAccounts(AccountsDTO accountsDTO){
        if(accountsRepo.existsById(accountsDTO.getAccID())){
            return VarList.RSP_DUPLICATED;
        }else {
            accountsRepo.save(modelMapper.map(accountsDTO, Accounts.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateAccount(AccountsDTO accountsDTO){
        if(accountsRepo.existsById(accountsDTO.getAccID())){
            accountsRepo.save(modelMapper.map(accountsDTO, Accounts.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<AccountsDTO> getAllAccounts(){
        List<Accounts> accountsList = accountsRepo.findAll();

        return
                modelMapper.map(accountsList, new TypeToken<ArrayList<AccountsDTO>>(){}.getType());
    }

    public AccountsDTO searchAccount(int accID){
        if(accountsRepo.existsById(accID)){
            Accounts accounts = accountsRepo.findById(accID).orElse(null);

            return
                    modelMapper.map(accounts, AccountsDTO.class);
        }else {
            return null;
        }
    }

    public String deleteAccount(int accID){
        if(accountsRepo.existsById(accID)){
            accountsRepo.deleteById(accID);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}

















