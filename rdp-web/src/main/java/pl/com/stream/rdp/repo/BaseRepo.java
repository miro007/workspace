
package pl.com.stream.rdp.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.com.stream.rdp.model.RootDocument;

public interface BaseRepo<D extends RootDocument> extends PagingAndSortingRepository<D, String> {

}
