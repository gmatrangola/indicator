package com.matrangola.indicator.data.model;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    @Indexed
    private String email;
    private Date birthday;
    @Indexed
    private int zipcode;
}
