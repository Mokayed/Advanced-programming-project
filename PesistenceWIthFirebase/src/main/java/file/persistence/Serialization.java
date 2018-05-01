/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.persistence;

/**
 *
 * @author MoK
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public interface Serialization
{
    public <T> void serialize(T[] array, Serializer<T> serializer, DataOutput out) throws IOException;
    public <T> T[] deserialize(Serializer<T> serializer, DataInput in) throws IOException;
}
