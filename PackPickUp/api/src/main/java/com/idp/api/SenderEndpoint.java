package com.idp.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.idp.api.OfyServiceSender.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "senderApi",
        version = "v1",
        resource = "sender",
        namespace = @ApiNamespace(
                ownerDomain = "api.idp.com",
                ownerName = "api.idp.com",
                packagePath = ""
        )
)
public class SenderEndpoint {

    public SenderEndpoint(){}

    /**
     * Return a collection of quotes
     *
     * @param count The number of quotes
     * @return a list of Quotes
     */
    @ApiMethod(name = "listSenders")
    public CollectionResponse<Sender> listSenders(@Nullable @Named("cursor") String cursorString,
                                               @Nullable @Named("count") Integer count) {

        Query<Sender> query = ofy().load().type(Sender.class);
        if (count != null) query.limit(count);
        if (cursorString != null && !cursorString.equals("")) {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<Sender> records = new ArrayList<>();
        QueryResultIterator<Sender> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

//Find the next cursor
        if (cursorString != null && !cursorString.equals("")) {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<Sender>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /**
     * This inserts a new <code>Quote</code> object.
     * @param sender The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertSender")
    public Sender insertSender(Sender sender) throws ConflictException {
//If if is not null, then check if it exists. If yes, throw an Exception
//that it is already present
        if (sender.getId() != null) {
            if (findRecord(sender.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
//Since our @Id field is a Long, Objectify will generate a unique value for us
//when we use put
        ofy().save().entity(sender).now();
        return sender;
    }

    /**
     * This updates an existing <code>Quote</code> object.
     * @param sender The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateSender")
    public Sender updateSender(Sender sender)throws NotFoundException {
        if (findRecord(sender.getId()) == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().save().entity(sender).now();
        return sender;
    }

    /**
     * This deletes an existing <code>Sender</code> object.
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeSender")
    public void removeSender(@Named("id") Long id) throws NotFoundException {
        Sender record = findRecord(id);
        if(record == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    //Private method to retrieve a <code>Quote</code> record
    private Sender findRecord(Long id) {
        return ofy().load().type(Sender.class).id(id).now();
//or return ofy().load().type(Quote.class).filter("id",id).first.now();
    }
}