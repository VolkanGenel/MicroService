package com.volkan.mapper;

import com.volkan.dto.request.AddUserRequestDto;
import com.volkan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    @Mapping(target = "userprofileid",source = "id")
    UserProfile toProfile(final AddUserRequestDto dto);
}
