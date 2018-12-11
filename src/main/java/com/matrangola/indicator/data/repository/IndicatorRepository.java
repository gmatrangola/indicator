package com.matrangola.indicator.data.repository;

import com.matrangola.indicator.data.model.Indicator;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.stream.Stream;

public interface IndicatorRepository extends CassandraRepository<Indicator,String> {
    Stream<Indicator> findAllByIndicatorCode(String code);
}
