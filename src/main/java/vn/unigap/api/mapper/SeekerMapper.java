package vn.unigap.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.unigap.api.dto.out.SeekerDtoOut;
import vn.unigap.api.entity.Seeker;


/**
 * The `SeekerMapper` interface provides methods for mapping
 * `Seeker` entities to `SeekerDtoOut` DTOs.
 */
@Mapper(componentModel = "spring")
public interface SeekerMapper {

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
