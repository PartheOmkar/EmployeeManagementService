package com.erpm.employeeManagementService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.erpm.employeeManagementService.models.LeaveRequests;

@Repository
@EnableJpaRepositories
public interface LeaveRequestsRepository extends JpaRepository<LeaveRequests, Integer>{

}
