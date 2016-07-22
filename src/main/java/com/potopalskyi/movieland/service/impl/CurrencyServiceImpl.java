package com.potopalskyi.movieland.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.potopalskyi.movieland.entity.dto.CurrencyDTO;
import com.potopalskyi.movieland.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String NBU_URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Value("#{'${allowedCurrencies}'.split(',')}")
    private List<String> allowedCurrencyTypeList;

    private volatile List<CurrencyDTO> currencyList = new ArrayList<>();

    @Override
    public double calculatePriceByCurrencyType(double price, String currencyType) {
        if (currencyType == null || !allowedCurrencyTypeList.contains(currencyType)) {
            return price;
        }
        for (CurrencyDTO currencyDTO : currencyList) {
            if (currencyType.equals(currencyDTO.getCc())) {
                return new BigDecimal(price / currencyDTO.getRate()).setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
        return price;
    }

    @PostConstruct
    @Override
    public void fillRateList() {
        try {
            URL url = new URL(NBU_URL);
            URLConnection urlConnection = url.openConnection();
            String jsonObject = "";
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));) {
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    jsonObject += line;
            }
            Type listType = new TypeToken<ArrayList<CurrencyDTO>>() {
            }.getType();
            List<CurrencyDTO> allCurrencyList = new Gson().fromJson(jsonObject, listType);
            List<CurrencyDTO> tempList = new ArrayList<>();
            for (CurrencyDTO currencyDTO : allCurrencyList) {
                if (allowedCurrencyTypeList.contains(currencyDTO.getCc())) {
                    tempList.add(currencyDTO);
                }
            }
            currencyList = tempList;
        } catch (IOException e) {
            logger.warn("Exception during of filling currency rates from NBU Bank");
        }
    }
}
