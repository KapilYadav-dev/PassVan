package `in`.kay.passvan.models

data class CredentialsModel(
    val compromised:Boolean =false,
    val name:String,
    val data:String,
    val url:String,
    val email:String?="",
    val username:String?="",
    val password:String,
)
