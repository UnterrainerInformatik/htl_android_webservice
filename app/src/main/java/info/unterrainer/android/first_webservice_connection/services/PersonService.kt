package info.unterrainer.android.first_webservice_connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.time.LocalDate

data class Person(
    var name: String,
    var birth: LocalDate
)

const val BASE_URL = "http://172.17.213.101:8080/"
// 172.17.213.101

interface PersonService {
    @GET("persons")
    suspend fun getPersons(): List<Person>

    companion object {
        var service: PersonService? = null
        fun getInstance(): PersonService {
            if (service == null) {
                service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PersonService::class.java)
            }
            return service!!
        }
    }
}
