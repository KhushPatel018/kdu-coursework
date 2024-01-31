package com.kdu.jdbc.dao;

import com.kdu.jdbc.model.Tenant;
import com.kdu.jdbc.util.TenantRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class TenantDAO {

    final DataSource dataSource;

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private TenantRowMapper rowMapper = new TenantRowMapper();

    public TenantDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate.setDataSource(dataSource);
    }

    public void save(Tenant tenant) {
        if(tenant.getId() != null && isPresent(tenant.getId())){
            log.error("User Already Exist");
            return;
        }
        String query= "insert into tenents(name,created_by) values (?,?)";
        int rows = jdbcTemplate.update(query,tenant.getName(),tenant.getCreatedBy());
        log.info("TENANT CREATED : total rows affected " + rows);
    }

    public Tenant getById(UUID id){
        List<Tenant> tenants = jdbcTemplate.query("select * from tenents where id = ?",rowMapper,id);
        if(tenants.isEmpty()) return null;
        return tenants.get(0);
    }

    public boolean isPresent(UUID id){
        return  !Objects.isNull(getById(id));
    }
}
