package com.example.fryjc.learn;

        import java.lang.reflect.Type;

        import com.example.fryjc.learn.models.ContactCard;
        import com.google.gson.JsonArray;
        import com.google.gson.JsonElement;
        import com.google.gson.JsonObject;
        import com.google.gson.JsonPrimitive;
        import com.google.gson.JsonSerializationContext;
        import com.google.gson.JsonSerializer;

public abstract class ContactSerializer implements JsonSerializer {

    public JsonElement serialize(final ContactCard contact, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        //The serialisation code is missing
        jsonObject.addProperty("name", contact.getName);
        jsonObject.addProperty("number", contact.getNumber);
        return jsonObject;
    }


}