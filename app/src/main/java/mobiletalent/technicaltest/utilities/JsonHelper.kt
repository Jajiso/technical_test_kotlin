package mobiletalent.technicaltest.utilities

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class JsonHelper {

    companion object {
        fun getJsonFromAssets(context: Context): String? {
            val inputStream: InputStream = context.assets.open("accountsDB.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charsets.UTF_8)
        }

        fun getJsonArrayFromJson(json:String, arrayKey:String): JSONArray {
            return JSONObject(json).getJSONArray(arrayKey)
        }

        fun getListOfJsonObjectsFromJSONArray(jsonArray:JSONArray): ArrayList<JSONObject> {
            val listJsonObj = arrayListOf<JSONObject>()
            for (i in 0 until jsonArray.length() ) {
                val obj: JSONObject = jsonArray.getJSONObject(i)
                listJsonObj.add(obj)
            }
            return  listJsonObj
        }
    }
}