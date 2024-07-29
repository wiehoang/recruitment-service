package vn.unigap.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.entity.Seeker;


@Mapper(componentModel = "spring")
public interface SeekerMapper {
    SeekerMapper INSTANCE = Mappers.getMapper(SeekerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "provinceId", source = "jobProvince.id")
    @Mapping(target = "provinceName", source = "jobProvince.name", ignore = true)
    SeekerDtoOut create(Seeker seeker);

    @Mapping(target = "provinceId", source = "jobProvince.id")
    @Mapping(target = "provinceName", source = "jobProvince.name", ignore = true)
    SeekerDtoOut update(Seeker seeker);

    @Mapping(target = "provinceId", source = "jobProvince.id")
    @Mapping(target = "provinceName", source = "jobProvince.name")
    SeekerDtoOut get(Seeker seeker);

}
