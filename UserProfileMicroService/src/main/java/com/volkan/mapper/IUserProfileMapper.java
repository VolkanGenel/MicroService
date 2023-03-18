package com.volkan.mapper;

import com.volkan.dto.request.UserProfileSaveRequestDto;
import com.volkan.rabbitmq.model.SaveAuthModel;
import com.volkan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    UserProfile toUserProfile(final UserProfileSaveRequestDto dto);
    UserProfile toUserProfile(final SaveAuthModel model);
}
