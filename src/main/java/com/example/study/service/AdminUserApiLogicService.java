package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import com.example.study.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest adminUserApiRequest = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(adminUserApiRequest.getAccount())
                .password(adminUserApiRequest.getPassword())
                .status(adminUserApiRequest.getStatus())
                .role(adminUserApiRequest.getRole())
                .lastLoginAt(LocalDateTime.now())
                .registeredAt(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        return adminUserRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest adminUserApiRequest = request.getData();

        return adminUserRepository.findById(adminUserApiRequest.getId())
                .map(adminUser -> {
                    adminUser
                            .setAccount(adminUserApiRequest.getAccount())
                            .setPassword(adminUserApiRequest.getPassword())
                            .setStatus(adminUserApiRequest.getStatus())
                            .setRole(adminUserApiRequest.getRole())
                            .setLastLoginAt(adminUserApiRequest.getLastLoginAt())
                            .setPasswordUpdatedAt(adminUserApiRequest.getPasswordUpdatedAt())
                            .setLoginFailCount(adminUserApiRequest.getLoginFailCount())
                            .setRegisteredAt(adminUserApiRequest.getRegisteredAt())
                            .setUnregisteredAt(adminUserApiRequest.getUnregisteredAt());

                    return adminUser;
                })
                .map(changeAdminUser -> adminUserRepository.save(changeAdminUser))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return adminUserRepository.findById(id)
                .map(adminUser -> {
                    adminUserRepository.delete(adminUser);

                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser){

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(adminUserApiResponse);

    }
}
