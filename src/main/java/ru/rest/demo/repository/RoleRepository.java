package ru.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rest.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
