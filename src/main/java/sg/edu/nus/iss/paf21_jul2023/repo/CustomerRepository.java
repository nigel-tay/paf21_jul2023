package sg.edu.nus.iss.paf21_jul2023.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf21_jul2023.model.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 1st function
    private final String findAllSql = "SELECT id, first_name, last_name FROM customer";

    // 2nd function
    private final String findByIdSql = "SELECT * FROM customer WHERE id = ?";

    // 3rd function
    private final String findAllLimitOffsetSql = "SELECT * FROM customer LIMIT ? OFFSET ?";

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<Customer>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSql);
        
        while(rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));

            customerList.add(customer);
        }
        return Collections.unmodifiableList(customerList);
    }
    
    public Customer getAllCustomersById(int id) {
        Customer customer = new Customer();
        
        customer = jdbcTemplate.queryForObject(findByIdSql, BeanPropertyRowMapper.newInstance(Customer.class), id);
        
        return customer;
    }

    public List<Customer> getAllCustomersWithLimitOff(int limit, int offset){
        List<Customer> customerList = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllLimitOffsetSql, limit, offset);

        while(rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getNString("last_name"));
            customerList.add(customer);
        }
        return Collections.unmodifiableList(customerList);
    }

}
