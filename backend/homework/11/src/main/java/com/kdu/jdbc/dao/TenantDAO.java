package com.kdu.jdbc.dao;

import com.kdu.jdbc.exception.EntityAlreadyExist;
import com.kdu.jdbc.model.Tenant;
import com.kdu.jdbc.util.TenantRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Data Access Object (DAO) for handling Tenant entities.
 */
@Repository
@Slf4j
public class TenantDAO {

    private final JdbcTemplate jdbcTemplate;
    private TenantRowMapper rowMapper = new TenantRowMapper();

    /**
     * Constructs a TenantDAO with the specified JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used.
     */
    public TenantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a Tenant entity. If the entity already has an ID and exists, it throws an EntityAlreadyExist exception.
     *
     * @param tenant The Tenant entity to be saved.
     */
    @Transactional
    public void save(Tenant tenant) {
        if (tenant.getId() != null && isPresent(tenant.getId())) {
            throw new EntityAlreadyExist("Tenant with id : " + tenant.getId() + "Already exist");
        }
        String query = "insert into tenents(name,created_by) values (?,?)";
        int rows = jdbcTemplate.update(query, tenant.getName(), tenant.getCreatedBy());
        log.info("TENANT CREATED : total rows affected " + rows);
    }

    /**
     * Retrieves a Tenant entity by its ID.
     *
     * @param id The UUID of the tenant.
     * @return The Tenant entity or null if not found.
     */
    public Tenant getById(UUID id) {
        List<Tenant> tenants = jdbcTemplate.query("select * from tenents where id = ?", rowMapper, id);
        if (tenants.isEmpty()) return null;
        return tenants.get(0);
    }

    /**
     * Checks if a Tenant entity with the given ID is present.
     *
     * @param id The UUID of the tenant.
     * @return True if the tenant is present, false otherwise.
     */
    public boolean isPresent(UUID id) {
        return !Objects.isNull(getById(id));
    }
}
