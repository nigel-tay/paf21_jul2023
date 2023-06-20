package sg.edu.nus.iss.paf21_jul2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf21_jul2023.model.Customer;
import sg.edu.nus.iss.paf21_jul2023.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    CustomerService custSvc;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return custSvc.getAllCustomers();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomersWithLimitAndOffset(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return custSvc.getAllCustomersWithLimitOff(limit, offset);
    }

    @GetMapping("/{cust-id}")
    public Customer getCustomerById(@PathVariable("cust-id") int custId) {
        return custSvc.getCustomerById(custId);
    }
}
