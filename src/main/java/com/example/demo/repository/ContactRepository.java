package com.example.demo.repository;

import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    @Autowired(required=true)
    @Qualifier("spring2")
    private JdbcTemplate jdbcTemplate2;

    public List<Contact> findAll() {
        return jdbcTemplate2.query("select * from contact", (rs, rowNum) ->
                new Contact(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("message")
                ));
    }

    public Contact findById(Long id) {
        return jdbcTemplate2.queryForObject("select * from contact where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Contact(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("message")
                ));
    }

    public int save(Contact contact) {
        return jdbcTemplate2.update("insert into contact (name, email, message) values (?, ?, ?)",
                contact.getName(), contact.getEmail(), contact.getMessage());
    }

    public int update(Contact contact) {
        return jdbcTemplate2.update("update contact set name = ?, email = ?, message = ? where id = ?",
                contact.getName(), contact.getEmail(), contact.getMessage(), contact.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate2.update("delete from contact where id = ?", id);
    }
}
