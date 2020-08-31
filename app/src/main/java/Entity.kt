import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.jar.Attributes

@Entity(tableName = "interns")
class Entity {
@PrimaryKey(autoGenerate = true)
val Id:Int?=null
    @ColumnInfo
    val FirstName:String=""
    @ColumnInfo
    val Email:String=""
    @ColumnInfo
    val School:String?=null

}