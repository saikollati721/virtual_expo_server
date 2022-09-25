package com.example.v_expo.mapper;

import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.request.TenantRequest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TenantMapper {
    Tenant map(TenantRequest tenantRequest);
    TenantDTO mapDTO(Tenant tenant);

    List<TenantDTO> mapDTOs(List<Tenant> testCases);
}
