
package pl.com.stream.topfirma.partner.admin.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OperatorRepository {

	List<Operator> findAll(@Param("rows") int rows, @Param("skip") int skip);
}
