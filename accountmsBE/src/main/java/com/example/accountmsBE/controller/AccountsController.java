package com.example.accountmsBE.controller;

import com.example.accountmsBE.dto.AccountsDTO;
import com.example.accountmsBE.dto.RespondDTO;
import com.example.accountmsBE.service.AccountsService;
import com.example.accountmsBE.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/accounts")
public class AccountsController {

    @Autowired
    AccountsService accountsService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RespondDTO respondDTO;

    @PostMapping(value = "/saveAccount")
    public ResponseEntity saveAccount(@RequestBody AccountsDTO accountsDTO){
        try{
            String res = accountsService.saveAccounts(accountsDTO);
            if(res.equals("00")){
                respondDTO.setCode(VarList.RSP_SUCCESS);
                respondDTO.setMessage("Success");
                respondDTO.setContent(accountsDTO);
                return new ResponseEntity(respondDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                respondDTO.setCode(VarList.RSP_DUPLICATED);
                respondDTO.setMessage("Already saved");
                respondDTO.setContent(accountsDTO);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }else {
                respondDTO.setCode(VarList.RSP_FAIL);
                respondDTO.setMessage("Error");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            respondDTO.setCode(VarList.RSP_ERROR);
            respondDTO.setMessage(ex.getMessage());
            respondDTO.setContent(null);
            return new ResponseEntity(respondDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateAccount")
    public ResponseEntity updateAccount(@RequestBody AccountsDTO accountsDTO){
        try{
            String res = accountsService.updateAccount(accountsDTO);
            if(res.equals("00")){
                respondDTO.setCode(VarList.RSP_SUCCESS);
                respondDTO.setMessage("Updated");
                respondDTO.setContent(accountsDTO);
                return new ResponseEntity(respondDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("01")){
                respondDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                respondDTO.setMessage("Not Found");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }else{
                respondDTO.setCode(VarList.RSP_FAIL);
                respondDTO.setMessage("Error");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            respondDTO.setCode(VarList.RSP_ERROR);
            respondDTO.setMessage(ex.getMessage());
            respondDTO.setContent(null);
            return new ResponseEntity(respondDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllAccounts")
    public ResponseEntity getAllAccount(){
        try{
            List<AccountsDTO> accountsDTOList =  accountsService.getAllAccounts();
            respondDTO.setCode(VarList.RSP_SUCCESS);
            respondDTO.setMessage("Success");
            respondDTO.setContent(accountsDTOList);
            return new ResponseEntity(respondDTO, HttpStatus.ACCEPTED);
        }catch (Exception ex){
            respondDTO.setCode(VarList.RSP_ERROR);
            respondDTO.setMessage(ex.getMessage());
            respondDTO.setContent(null);
            return new ResponseEntity(respondDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchAccount/{accID}")
    public ResponseEntity searchAccount(@PathVariable int accID){
        try{
            AccountsDTO accountsDTO = accountsService.searchAccount(accID);
            if (accountsDTO != null){
                respondDTO.setCode(VarList.RSP_SUCCESS);
                respondDTO.setMessage("success");
                respondDTO.setContent(accountsDTO);
                return new ResponseEntity(respondDTO, HttpStatus.ACCEPTED);
            }else{
                respondDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                respondDTO.setMessage("Not Found");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            respondDTO.setCode(VarList.RSP_ERROR);
            respondDTO.setMessage(ex.getMessage());
            respondDTO.setContent(null);
            return new ResponseEntity(respondDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteAccount/{accID}")
    public ResponseEntity deleteAccount(@PathVariable int accID){
        try{
            String res = accountsService.deleteAccount(accID);
            if(res.equals("00")){
                respondDTO.setCode(VarList.RSP_SUCCESS);
                respondDTO.setMessage("Deleted");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                respondDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                respondDTO.setMessage("Not Found this id");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }else{
                respondDTO.setCode(VarList.RSP_FAIL);
                respondDTO.setMessage("Error");
                respondDTO.setContent(null);
                return new ResponseEntity(respondDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            respondDTO.setCode(VarList.RSP_ERROR);
            respondDTO.setMessage(ex.getMessage());
            respondDTO.setContent(null);
            return new ResponseEntity(respondDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}













