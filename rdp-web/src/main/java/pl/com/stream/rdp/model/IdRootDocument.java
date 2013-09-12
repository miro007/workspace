package pl.com.stream.rdp.model;

import org.springframework.data.annotation.Id;

public abstract class IdRootDocument extends RootDocument {

	@Id
	String id;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof IdRootDocument)) {
			return false;
		}
		IdRootDocument document = (IdRootDocument) obj;
		return document.getId().equals(this.id);
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			return super.hashCode();
		}
		return this.id.hashCode();
	}

}
