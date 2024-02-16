package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired(required=true)
    @Qualifier("spring")
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employee", (rs, rowNum) ->
                new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
    }

    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("select * from employee where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Employee(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
    }

    public int save(Employee employee) {
        return jdbcTemplate.update("insert into employee (name, email, phone) values (?, ?, ?)",
                employee.getName(), employee.getEmail(), employee.getPhone());
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("update employee set name = ?, email = ?, phone = ? where id = ?",
                employee.getName(), employee.getEmail(), employee.getPhone(), employee.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from employee where id = ?", id);
    }
}