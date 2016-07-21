package com.potopalskyi.movieland.service.impl;

import com.potopalskyi.movieland.entity.dto.CurrencyDTO;
import com.potopalskyi.movieland.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Value("#{'${allowedCurrencies}'.split(',')}")
    private List<String> allowedCurrencyTypeList;

    private List<CurrencyDTO> ratesList = new ArrayList<>();

    @Override
    public double calculatePriceByCurrencyType(double price, String currencyType) {
        if(currencyType == null || !allowedCurrencyTypeList.contains(currencyType)){
            return price;
        }
        for(CurrencyDTO currencyDTO: ratesList){
            if(currencyType.equals(currencyDTO.getCc())){
                return price * currencyDTO.getRate();
            }
        }
        return price;
    }

}
