package com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import org.w3c.dom.Node;
import  org.w3c.dom.bootstrap.DOMImplementationRegistry;
import  org.w3c.dom.ls.DOMImplementationLS;
import  org.w3c.dom.ls.LSSerializer;

import com.fasterxml.jackson.core.*;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.SerializerBase;

public class DOMSerializer
    extends SerializerBase<Node>
{
    protected final DOMImplementationLS _domImpl;

    public DOMSerializer()
    {
        super(Node.class);
        DOMImplementationRegistry registry;
        try {
            registry = DOMImplementationRegistry.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Could not instantiate DOMImplementationRegistry: "+e.getMessage(), e);
        }
        _domImpl = (DOMImplementationLS)registry.getDOMImplementation("LS");
    }
    
    @Override
    public void serialize(Node value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException
    {
        if (_domImpl == null) throw new IllegalStateException("Could not find DOM LS");    	
        LSSerializer writer = _domImpl.createLSSerializer();
        jgen.writeString(writer.writeToString(value));
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, java.lang.reflect.Type typeHint)
    {
        // Well... it is serialized as String
        return createSchemaNode("string", true);
    }
}
