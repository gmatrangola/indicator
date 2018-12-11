package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Customer;
import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.data.model.Request;
import com.matrangola.indicator.data.repository.CustomerRepository;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IndicatorServiceImpl implements IndicatorService{
    @Autowired
    IndicatorRepository indicatorRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Double worldwideAverage(String code) {
        Stream<Indicator> allByIndicatorCode = indicatorRepository
                .findAllByIndicatorCode(code);
        OptionalDouble average = allByIndicatorCode
                .filter(indicator -> indicator.getYear2017() != null)
                .mapToDouble(Indicator::getYear2017).average();
        if (average.isPresent()) return average.getAsDouble();
        else return null;
    }

    public List<Indicator> aboveMin(String code, Double min) {
        return indicatorRepository
                .findAllByIndicatorCode(code)
                .filter(indicator -> indicator.getYear2017() != null
                        && indicator.getYear2017() > min)
                .collect(Collectors.toList());
    }

    public List<Indicator> aboveAverage(String code) {
        return aboveMin(code, worldwideAverage(code));
    }

    @Override
    public Indicator getIndicator(String countryCode, String indexCode, String email) throws Exception {

        Optional<Customer> cust = customerRepository.findCustomerByEmail(email);

        if (!cust.isPresent()) {
            throw new Exception("No Customer");
        }
        Customer customer = cust.get();

        Request request = new Request(countryCode, indexCode);
        Map<Date, Request> history = customer.getHistory();
        if (history == null) {
            history = new HashMap<>();
            customer.setHistory(history);
        }
        history.put(new Date(), request);
        customerRepository.save(customer);

        return indicatorRepository.findByCountryCodeAndIndicatorCode(
                countryCode, indexCode);
    }
}