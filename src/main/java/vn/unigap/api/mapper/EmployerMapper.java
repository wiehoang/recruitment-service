package vn.unigap.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.unigap.api.dto.out.EmployerDtoOut;
import vn.unigap.api.entity.Employer;


/**
 * The `EmployerMapper` interface provides methods for mapping
 * `Employer` entities to `EmployerDtoOut` DTOs.
 */
@Mapper(componentModel = "spring")
public interface EmployerMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "provinceId", source = "jobProvince.id")
  @Mapping(target = "provinceName", source = "jobProvince.name", ignore = true)
  EmployerDtoOut create(Employer employer);

  @Mapping(target = "email", ignore = true)
  @Mapping(target = "provinceId", source = "jobProvince.id")
  @Mapping(target = "provinceName", source = "jobProvince.name", ignore = true)
  EmployerDtoOut update(Employer employer);

  @Mapping(target = "provinceId", source = "jobProvince.id")
  @Mapping(target = "provinceName", source = "jobProvince.name")
  EmployerDtoOut get(Employer employer);

  @Mapping(target = "description", ignore = true)
  @Mapping(target = "provinceId", source = "jobProvince.id")
  @Mapping(target = "provinceName", source = "jobProvince.name")
  EmployerDtoOut getPage(Employer employer);

}
