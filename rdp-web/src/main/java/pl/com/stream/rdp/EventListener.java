package pl.com.stream.rdp;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

@Component
public class EventListener extends AbstractMongoEventListener {
	@Override
	public void onBeforeSave(Object source, DBObject dbo) {
		if (dbo.get("_id") == null) {
			dbo.put("creationDate", new Date());
		}
	}
}
