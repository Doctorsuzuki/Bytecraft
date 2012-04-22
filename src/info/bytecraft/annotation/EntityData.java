package info.bytecraft.annotation;

import java.lang.annotation.*;

import org.bukkit.entity.EntityType;

/** @author Sabersamus <rcatron10@gmail.com> */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.LOCAL_VARIABLE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityData
{
  public abstract EntityType entityType();
  
  public abstract DataType dataType(); 
}
