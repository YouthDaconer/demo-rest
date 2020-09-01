package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

	public List<Customer> findByEnableAndEmail(String enable, String email);
}
