package com.kdu.jdbc.dao;

import com.kdu.jdbc.exception.EntityNotFound;
import com.kdu.jdbc.model.User;
import com.kdu.jdbc.util.TenantRowMapper;
import com.kdu.jdbc.util.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) for handling User entities.
 */
@Repository
@Slf4j
public class UsersDAO {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper = new UserRowMapper();

    /**
     * Constructs a UsersDAO with the specified JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used.
     */
    public UsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves a list of User entities by their tenant ID.
     *
     * @param id The UUID of the tenant.
     * @return List of User entities.
     */
    public List<User> getAllById(UUID id) {
        log.info(id.toString());
        return jdbcTemplate.query("select * from users where tenant_id = ?", rowMapper, id);
    }

    /**
     * Updates a User entity identified by the given ID.
     *
     * @param id   The UUID of the user.
     * @param user The User entity with updated information.
     * @return The number of rows affected by the update.
     */
    public int update(UUID id, User user) {
        return jdbcTemplate.update("update users set username = ? , loggedin = ? , time_zone = ? where id = ?", user.getUsername(), user.getLoggedin(), user.getTimezone(), id);
    }

    /**
     * Checks if a User entity with the given ID is present.
     *
     * @param id The UUID of the user.
     * @return True if the user is present, false otherwise.
     */
    private boolean isPresent(UUID id) {
        return !getAllById(id).isEmpty();
    }

    /**
     * Saves a User entity. If the entity already has an ID and does not exist, it throws an EntityNotFound exception.
     *
     * @param user The User entity to be saved.
     */
    @Transactional
    public void save(User user) {
        if (user.getId() != null && !isPresent(user.getId())) {
            throw new EntityNotFound("user with id " + user.getId() + " NOT exist");
        }
        int row = jdbcTemplate.update("insert into users(username,loggedin,time_zone,tenant_id) values (?,?,?,?)", user.getUsername(), user.getLoggedin(), user.getTimezone(), user.getTenantId());
        log.info("rows affected : " + row);
    }
}
