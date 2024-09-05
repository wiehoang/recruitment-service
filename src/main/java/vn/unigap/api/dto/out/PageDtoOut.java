package vn.unigap.api.dto.out;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The `PageDtoOut` class represents the Data Transfer Object (DTO)
 * for responding a pagination with data object.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDtoOut<T> {

  private Integer page;
  private Integer pageSize;
  private Long totalElements;
  private Integer totalPages;
  private List<T> data;

  public static <T> PageDtoOut<T> from(Integer page, Integer pageSize, Long totalElements,
                                       Integer totalPages, List<T> data) {
    return new PageDtoOut<>(page, pageSize, totalElements, totalPages, data);
  }

}
